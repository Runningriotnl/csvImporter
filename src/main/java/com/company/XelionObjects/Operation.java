package com.company.XelionObjects;

public class Operation {
    private String op;
    private String path;
    private String value;

    public Operation() {
        this.op = "add";
        this.path = "/extensions/1";
        this.value = null;
    }

    public Operation(String value) {
        this.op = "replace";
        this.path = "/extensions/1/address";
        this.value = value;
    }
}
