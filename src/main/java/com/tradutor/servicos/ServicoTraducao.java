package com.tradutor.servicos;

import com.tradutor.entidades.Config;
import com.tradutor.entidades.Dictionary;
import com.tradutor.uteis.EncodingUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;

@Service
public class ServicoTraducao {

    public static String doRemoverAcentos(String v) {
        String resultadoNormalizado = Normalizer.normalize(v, Normalizer.Form.NFD);
        return resultadoNormalizado.replaceAll("[^\\p{ASCII}]", "");
    }

    public Config traduzir(Config cfg) {
        for (Dictionary d : cfg.getDictionary()) {
            for (String k : d.getTranslation().keySet()) {
//                d.getTranslation().get(k).setValue(doRemoverAcentos(k));
                d.getTranslation().get(k).setValue(consumir(doRemoverAcentos(k)));
            }
        }
        return cfg;
    }

    private String consumir(String frase) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                    "key=trnsl.1.1.20190218T210754Z.8b75a7f8cead34a1.f32069939edca305ffc05206c42d38b3bebdf14d&text=" +
                    "&lang=pt-en";
            HttpEntity<?> entidade = new HttpEntity<>("text=" + frase, headers);
            ResponseEntity<String> rb = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entidade,
                    String.class);
            JSONObject resposta = new JSONObject(rb.getBody());
            String trad = "";
            JSONArray text = resposta.getJSONArray("text");
            for (int i = 0; i < text.length(); i++) {
                if (text.getString(i) != null && !text.getString(i).isEmpty()) {
                    trad = text.getString(i);
                }
            }
            return trad;
        }catch (Exception e){
            return frase;
        }

    }

}


