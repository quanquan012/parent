package com.common.base.condition;

import com.common.base.enums.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFilter implements Serializable {
    private String property;
    private Operator operator;
    private Object value;

    public static SearchFilter eq(String property, Object value) {
        return new SearchFilter(property, Operator.EQ, value);
    }

    public static SearchFilter ne(String property, Object value) {
        return new SearchFilter(property, Operator.NE, value);
    }

    public static SearchFilter gt(String property, Object value) {
        return new SearchFilter(property, Operator.GT, value);
    }

    public static SearchFilter lt(String property, Object value) {
        return new SearchFilter(property, Operator.LT, value);
    }

    public static SearchFilter ge(String property, Object value) {
        return new SearchFilter(property, Operator.GE, value);
    }

    public static SearchFilter le(String property, Object value) {
        return new SearchFilter(property, Operator.LE, value);
    }

    public static SearchFilter like(String property, Object value) {
        return new SearchFilter(property, Operator.LIKE, value);
    }

    public static SearchFilter notLike(String property, Object value) {
        return new SearchFilter(property, Operator.NOTLIKE, value);
    }

    public static SearchFilter in(String property, Object value) {
        return new SearchFilter(property, Operator.IN, value);
    }

    public static SearchFilter isNull(String property) {
        return new SearchFilter(property, Operator.ISNULL, (Object) null);
    }

    public static SearchFilter isNotNull(String property) {
        return new SearchFilter(property, Operator.ISNOTNULL, (Object) null);
    }

    public static SearchFilter notIn(String property, Object value) {
        return new SearchFilter(property, Operator.NOTIN, value);
    }

    public static SearchFilter between(String property, List<Object> value) {
        return new SearchFilter(property, Operator.BT, value);
    }

    public static SearchFilter notBetween(String property, List<Object> value) {
        return new SearchFilter(property, Operator.NOTBT, value);
    }

}
