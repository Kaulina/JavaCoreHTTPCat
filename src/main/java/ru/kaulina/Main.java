package ru.kaulina;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Main {
    public static final String CAT_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create()
                .setUserAgent("Assessed Cats")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000) // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000) // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(CAT_URL); // объект запроса
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = client.execute(request);// вызов удаленного сервиса

        InputStream content = response.getEntity().getContent();


        List<Cat> cats = mapper.readValue(content, new TypeReference<>() {
        }); // преобразование json  в список

        client.close();
        content.close();


        cats.stream() // фильтр списка методами API
                .filter(cat -> cat.getUpvotes() != null)
                .forEach(System.out::println);
    }
}