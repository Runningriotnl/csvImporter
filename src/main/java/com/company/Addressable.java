package com.company;


public class Addressable {
    private String objectType;
    private String commonName;
    private String oid;

    public Addressable() {

    }

    public Addressable(String objectType) {
        //this.commonName = commonName;
        this.objectType = objectType;
        this.oid = null;
    }


    public String getCommonName() {
        return commonName;
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
                ", commonName='" + commonName + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }

    public String toJsonObject() {
        return "";
    }

}

