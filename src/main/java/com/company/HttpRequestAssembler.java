package com.company;

import java.io.IOException;

public interface HttpRequestAssembler {

    String createPostRequest(Addressable addressable) throws IOException, InterruptedException;
}
