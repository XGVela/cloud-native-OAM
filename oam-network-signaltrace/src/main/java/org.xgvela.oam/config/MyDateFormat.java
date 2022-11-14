package org.xgvela.oam.config;

import java.text.*;
import java.util.Date;

public class MyDateFormat extends DateFormat {

    private DateFormat dateFormat;
    private SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    public MyDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            date = format.parse(source, pos);
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    @Override
    public Date parse(String source) throws ParseException {
        Date date = null;
        try {
            date = format.parse(source);
        } catch (Exception e) {
            date = dateFormat.parse(source);
        }
        return date;
    }

    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new MyDateFormat((DateFormat) format);
    }
}