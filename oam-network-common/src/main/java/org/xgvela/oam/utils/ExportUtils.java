package org.xgvela.oam.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExportUtils {
    private static final int BUFFER_SIZE = 2 * 1024;

    private static void setHttpHeader(HttpServletResponse response, String fileName) throws Exception {
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; fileName=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8"));
    }

    public static <T> void exportXlsx(HttpServletResponse response, String fileName, List<T> entitys, Class<T> t) throws Exception {
        fileName = fileName + ".xlsx";
        setHttpHeader(response, fileName);
        EasyExcelFactory.write(response.getOutputStream(), t).sheet(fileName).doWrite(cutDatas(entitys));
    }

    public static <T> void exportCsv(HttpServletResponse response, String fileName, List<T> entitys, Class<T> t) throws Exception {
        fileName = fileName + ".csv";
        setHttpHeader(response, fileName);
        CSVPrinter print = CSVFormat.DEFAULT.withHeader(getHeads(t)).print(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
        for (T entity : entitys) {
            print.printRecord(getDatas(entity));
        }
        print.flush();
        print.close();
    }

    public static <T> void exportCsv(File file, List<T> entitys, Class<T> t) throws Exception {
        CSVPrinter print = CSVFormat.DEFAULT.withHeader(getHeads(t)).print(file, StandardCharsets.UTF_8);
        for (T entity : entitys) {
            print.printRecord(getDatas(entity));
        }
        print.flush();
        print.close();
    }

    public static <T> void exportCsvZip(HttpServletResponse response, String fileName, List<T> entitys, Class<T> t) throws Exception {
        File csv = new File(System.getProperty("java.io.tmpdir") + fileName + ".csv");
        exportCsv(csv, entitys, t);
        File zip = new File(System.getProperty("java.io.tmpdir") + fileName + ".zip");
        fileToZip(csv, zip);
        fileName = fileName + ".zip";
        setHttpHeader(response, fileName);
        FileUtils.copyFile(zip, response.getOutputStream());
        try{
            FileUtils.forceDelete(csv);
            FileUtils.forceDelete(zip);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void fileToZip(File file, File zip) throws Exception {
        ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(zip);
        ZipArchiveEntry zae = new ZipArchiveEntry(file.getName());
        zaos.putArchiveEntry(zae);
        IOUtils.copy(new FileInputStream(file), zaos);
        zaos.closeArchiveEntry();
        zaos.finish();
    }

    private static <T> String[] getHeads(Class<T> cls) {
        List<Field> fields = FieldUtils.getAllFieldsList(cls).stream().filter(field -> Objects.nonNull(field.getAnnotation(ExcelProperty.class))).sorted(Comparator.comparingInt(o -> o.getAnnotation(ExcelProperty.class).index())).collect(Collectors.toList());
        List<String> heads = new ArrayList<>();
        for (Field field : fields) {
            heads.add(field.getAnnotation(ExcelProperty.class).value()[0]);
        }
        return heads.toArray(new String[0]);
    }

    private static <T> Object[] getDatas(T t) throws Exception {
        Class<?> cls = t.getClass();
        List<Field> fields = FieldUtils.getAllFieldsList(cls).stream().filter(field -> Objects.nonNull(field.getAnnotation(ExcelProperty.class))).sorted(Comparator.comparingInt(o -> o.getAnnotation(ExcelProperty.class).index())).collect(Collectors.toList());
        List<Object> datas = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            datas.add(field.get(t));
        }
        return datas.toArray(new Object[0]);
    }

    private static <T> List<T> cutDatas(List<T> list) throws Exception {
        for (T t : list) {
            cutDatas(t);
        }
        return list;
    }

    private static <T> T cutDatas(T t) throws Exception {
        Class<?> cls = t.getClass();
        List<Field> fields = FieldUtils.getAllFieldsList(cls).stream().filter(field -> Objects.nonNull(field.getAnnotation(ExcelProperty.class))).sorted(Comparator.comparingInt(o -> o.getAnnotation(ExcelProperty.class).index())).collect(Collectors.toList());
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(t);
            if (value instanceof String) {
                field.set(t, StringUtils.abbreviate((String) value, 4000));
            }
        }
        return t;
    }
}
