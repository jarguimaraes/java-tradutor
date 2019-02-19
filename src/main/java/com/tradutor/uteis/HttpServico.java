package com.tradutor.uteis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class HttpServico {

    private static final Logger LOG = LoggerFactory.getLogger(HttpServico.class);

    private static final String ACCEPT_HEADER = "Accept";

    private final RestTemplate restTemplate;

    public HttpServico() {
        this.restTemplate = new RestTemplate();
    }


    public ResponseEntity<String> doPostJson(String url, String corpo) {
        return doJson(url, corpo, HttpMethod.POST);
    }

    public ResponseEntity<String> doJson(String url, String corpo, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<?> entidade = new HttpEntity<>(corpo, headers);

        String uri = builder.toUriString();
        LOG.info("Requisitando URL {} com payload {}", uri, corpo);

        return restTemplate.exchange(
                uri,
                httpMethod,
                entidade,
                String.class);
    }


    public static void main(String ... args) throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20190218T210754Z.8b75a7f8cead34a1.f32069939edca305ffc05206c42d38b3bebdf14d&text=" +
                "&lang=pt-en";
        HttpEntity<?> entidade = new HttpEntity<>("text=Estou em casa!", headers);
        ResponseEntity<String> rb = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entidade,
                String.class);
        System.out.println(rb.getBody());
    }

}
