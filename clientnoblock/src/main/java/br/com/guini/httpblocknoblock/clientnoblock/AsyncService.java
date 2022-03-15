package br.com.guini.httpblocknoblock.clientnoblock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<List<Integer>> postForObject() {
        return CompletableFuture.supplyAsync(() ->
            restTemplate.postForObject("http://localhost:8080/server", null, List.class)
        );
    }
}
