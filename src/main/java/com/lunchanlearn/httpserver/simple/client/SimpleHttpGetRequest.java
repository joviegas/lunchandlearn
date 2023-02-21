package com.lunchanlearn.httpserver.simple.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SimpleHttpGetRequest {

    public static void main(String[] args) throws Exception {
        // Create an instance of CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create an HttpGet request with the desired URL
        HttpGet httpGet = new HttpGet("http://localhost:8001/test?=new");

        // Execute the request and obtain the response
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());

        // Print the response body to the console
        System.out.println(responseBody);

        // Release the resources associated with the response and the HttpClient
        response.close();
        httpClient.close();
    }
}

