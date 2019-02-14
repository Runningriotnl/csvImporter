package com.company.XelionObjects;

//import org.springframework.http.HttpMethod;

public class Link
{
    private String rel;
    private String href;
    //private HttpMethod method;

    public String getRel()
    {
        return rel;
    }

    public Link setRel(String rel)
    {
        this.rel = rel;
        return this;
    }

    public String getHref()
    {
        return href;
    }

    public Link setHref(String href)
    {
        this.href = href;
        return this;
    }

//    public HttpMethod getMethod()
//    {
//        return method;
//    }
//
//    public Link setMethod(HttpMethod method)
//    {
//        this.method = method;
//        return this;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "Link{" + "rel='" + rel + '\'' + ", href='" + href + '\'' + ", method=" + method + '}';
//    }
}