package com.company;

import com.company.XelionObjects.PatchDocument;

import java.io.IOException;

public interface HttpRequestAssembler {

    String createPostRequest(String apiUrl, Addressable addressable) throws IOException, InterruptedException;

    String createPatchRequest(String apiUrl, PatchDocument patchDocument) throws IOException, InterruptedException;

    String createGetRequest(String apiUrl, String value) throws IOException, InterruptedException;
}
