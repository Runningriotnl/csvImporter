package com.company.XelionObjects;

public class PatchOperation {
    private String op;
    private String path;
    private Object value;

//    public PatchOperation() {
//        this.op = "add";
//        this.path = "/extensions/1";
//        this.value = null;
//    }
//
//    public PatchOperation(String value) {
//        this.op = "replace";
//        this.path = "/extensions/1/address";
//        this.value = value;
//    }

    public static PatchOperation add(String path) {
        return new PatchOperation("add", path, null);
    }

    public static PatchOperation replace(String path, Object value) {
        return new PatchOperation("replace", path, value);
    }

    public static PatchOperation remove(String path) {
        return new PatchOperation("remove", path, null);
    }

    private PatchOperation(String op, String path, Object value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }
}
