package com.company;


public class Addressable {
    private String objectType;
    private String oid;

    public Addressable() {

    }

    public Addressable(String objectType) {
        this.objectType = objectType;
        this.oid = null;
    }

    public Addressable setOid(String oid) {
        this.oid = oid;
        return this;
    }

    public String getOid() {
        return oid;
    }

    public String getObjectType() {
        return objectType;
    }

    @Override
    public String toString() {
        return "Addressable{" +
                "objectType='" + objectType + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }
}

