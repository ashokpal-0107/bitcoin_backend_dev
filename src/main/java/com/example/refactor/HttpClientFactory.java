package com.example.refactor;

import java.net.http.HttpClient;

public class HttpClientFactory {
    public static HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
}

