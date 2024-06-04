package ru.netology;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

    public class Main {

    private static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(URL);
        try (CloseableHttpResponse response = httpClient.execute(request);
//             BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
             ) {
            ObjectMapper mapper = new ObjectMapper();
            List<CatFact> facts = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<CatFact>>() {});

            List<CatFact> filteredFacts = facts.stream()
                    .filter(fact -> fact.getUpvotes() != null && fact.getUpvotes() > 0)
                    .toList();

            filteredFacts.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println(e);

        }
    }
}