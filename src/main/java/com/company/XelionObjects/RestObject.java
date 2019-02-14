package com.company.XelionObjects;

import java.util.List;

public class RestObject<T>
{
    private T object;
    private List<Link> links; // TODO

    public T getObject()
    {
        return object;
    }

    public RestObject setObject(T object)
    {
        this.object = object;
        return this;
    }

    public List<Link> getLinks()
    {
        return links;
    }

    public RestObject setLinks(List<Link> links)
    {
        this.links = links;
        return this;
    }

    @Override
    public String toString()
    {
        return "RestObject{" + "object=" + object + ", links=" + links + '}';
    }
}