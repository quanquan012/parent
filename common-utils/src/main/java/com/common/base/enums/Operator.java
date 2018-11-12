package com.common.base.enums;

public enum Operator {
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    LIKE("like"),
    NOTLIKE("not like"),
    IN("in"),
    NOTIN("not in"),
    ISNULL("is null"),
    ISNOTNULL("is not null"),
    BT("between"),
    NOTBT("not between"),
    ENUM("enum");

    private String name;

    private Operator() {
    }

    private Operator(String name) {
        this.name = name;
    }
}