package com.company.XelionObjects;


import java.util.ArrayList;
import java.util.List;

public class PatchDocument {
    private List<PatchOperation> operations;

    public PatchDocument() {
        operations = new ArrayList<>();
    }

    public PatchDocument add(PatchOperation patchOperation) {
        operations.add(patchOperation);
        return this;
    }
}
