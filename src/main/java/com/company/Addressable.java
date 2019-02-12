package com.company;

public class Addressable<T> {

    private String name;
    private String commonName;
    private String oid;
    private String objectType;

    public Addressable() {

    }

    public Addressable(String commonName, String objectType) {
        this.commonName = commonName;
        this.objectType = objectType;
        this.name = null;
        this.oid = null;
    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", commonName='" + commonName + '\'' +
                ", oid='" + oid + '\'' +
                ", objectType='" + objectType + '\'' +
                '}';
    }

    public String toJsonObject() {
        if(typeO)
        String jsonObjectString = "{\"objectType\":\"Organisation\", \"commonName\":\"" + this.name +"\"}";
        return jsonObjectString;
    }

}

