package com.company;

import java.util.List;
import java.util.stream.Collectors;

public class DataList<T> {
    private List<Data<T>> data;
    private Object meta;

    public List<T> getData() {
        return data.stream().map((d) -> d.getObject()).collect(Collectors.toList());
    }

    public Object getMeta() {
        return meta;
    }
}

