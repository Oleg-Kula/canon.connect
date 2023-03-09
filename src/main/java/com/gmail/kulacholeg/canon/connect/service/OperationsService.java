package com.gmail.kulacholeg.canon.connect.service;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class OperationsService {

    private final RestTemplate template;

    public OperationsService(){
        template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    public String getAllOperations(){
        String cookie = this.login();
        String url = "http://192.168.1.205/m_departmentid.html";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookie); // установить заголовок Cookie
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());

        return response.getBody();
    }

    private String login(){
        String result = "";
        String url = "http://192.168.1.205/tryLogin.cgi";
        String body = "loginM=&0005=7654321&0006=1357246";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> req = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, req, String.class);

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                result = cookie;
            }
        }
        List<String> temp = List.of(result.split(";"));
        result = temp.get(0);
        System.out.println(result);
        return result;
    }
}
