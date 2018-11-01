package com.common.utils.date;

import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {
    private boolean emptyAsNull;
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public DateEditor(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
    }

    public DateEditor(boolean emptyAsNull, String dateFormat) {
        this.emptyAsNull = emptyAsNull;
        this.dateFormat = dateFormat;
    }

    public String getAsText() {
        Date value = (Date)this.getValue();
        return value != null ? (new SimpleDateFormat(this.dateFormat)).format(value) : "";
    }

    public void setAsText(String text) {
        if (text == null) {
            this.setValue((Object)null);
        } else {
            String value = text.trim();
            if (this.emptyAsNull && "".equals(value)) {
                this.setValue((Object)null);
            } else {
                try {
                    this.setValue(DateUtils.parseDate(value, DateConstant.DATE_PATTERNS));
                } catch (ParseException var4) {
                    this.setValue((Object)null);
                }
            }
        }

    }
}
