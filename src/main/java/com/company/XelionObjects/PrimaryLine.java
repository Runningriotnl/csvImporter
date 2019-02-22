package com.company.XelionObjects;

public class PrimaryLine {
    private String commonName;
    private String oid;
    private String objectType;

    public PrimaryLine(String commonName, String oid) {
        this.commonName = commonName;
        this.oid = oid;
        objectType = "XCCPhoneline";
    }

    public String getOid() {
        return oid;
    }

    @Override
    public String toString() {
        return "PrimaryLine{" +
                "commonName='" + commonName + '\'' +
                ", oid='" + oid + '\'' +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}
