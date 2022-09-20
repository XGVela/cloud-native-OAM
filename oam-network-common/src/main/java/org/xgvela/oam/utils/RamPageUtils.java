package org.xgvela.oam.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

public class RamPageUtils {
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private RamPageUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> List<T> getList(List<T> all, int pageNum, int pageSize) {
        if (all == null || all.isEmpty()) return new ArrayList<>();
        if (pageNum <= 0) pageNum = DEFAULT_PAGE_NUM;
        if (pageSize == 0 || pageSize < -1) pageSize = DEFAULT_PAGE_SIZE;
        if (pageSize == -1) pageSize = Integer.MAX_VALUE;
        int from = (pageNum - 1) * pageSize;
        int to = Math.min(pageNum * pageSize, all.size());
        if (from >= to) return new ArrayList<>();
        return all.subList(from, to);
    }

    public static <T> List<T> getList(List<T> all, long pageNum, long pageSize) {
        return getList(all, (int) pageNum, (int) pageSize);
    }

    public static <T> IPage<T> getPage(List<T> all, IPage<T> page) {
        return page.setRecords(getList(all, (int) page.getCurrent(), (int) page.getSize())).setTotal(all.size());
    }

    public static <T> IPage<T> getPage(List<T> all, long pageNum, long pageSize) {
        return getPage(all, new Page<T>().setCurrent(pageNum).setSize(pageSize));
    }
}
