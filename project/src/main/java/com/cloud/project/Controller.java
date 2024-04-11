package com.cloud.project;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/greeting")
    public String welcome(){
        return "Welcome to the simple project!!!";
    }

    @GetMapping
    public String apicall(){
        RestClient restClient=RestClient.create();
        String body = restClient.get()
                .uri("https://www.google.com")
                .retrieve()
                .onStatus(new ResponseErrorHandler() {
                    @Override
                    public boolean hasError(ClientHttpResponse response) throws IOException {
                        return response.getStatusCode().is4xxClientError()||response.getStatusCode().is5xxServerError();
                    }

                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {

                        System.out.println("response = " + response);

                    }
                })
                .body(String.class);
        System.out.println(body);



        return "success";
    }

}
