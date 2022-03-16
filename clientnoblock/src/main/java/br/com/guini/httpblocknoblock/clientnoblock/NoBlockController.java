package br.com.guini.httpblocknoblock.clientnoblock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/noblock")
public class NoBlockController {

    @Autowired
    private AsyncService asyncService;

    @PostMapping("/asyncGet")
    public List<Integer> asyncGet() throws ExecutionException, InterruptedException {
        var values = asyncService.postForObject().get();
        values.addAll(asyncService.postForObject().get());

        return values;
    }

    @PostMapping("/async")
    public CompletableFuture<ArrayList<Integer>> async() throws ExecutionException, InterruptedException {
        var response = new ArrayList<Integer>();

        return asyncService.postForObject().thenApply(result -> {
            response.addAll(result);

            asyncService.postForObject().thenApply(response::addAll);
            return response;
        });
    }

    @PostMapping("/webclient")
    public Mono<Tuple2<List, List>> webclient() {

        var webClient = WebClient.create("http://localhost:8080/server");

        Mono<List> values = webClient.post()
                .retrieve()
                .bodyToMono(List.class);

        Mono<List> values2 = webClient.post()
                .retrieve()
                .bodyToMono(List.class);

        return Mono.zip(values, values2);
    }

    @PostMapping("/webclientblock")
    public List webclientblock() {

        var webClient = WebClient.create("http://localhost:8080/server");

        List block = webClient.post()
                .retrieve()
                .bodyToMono(List.class)
                .block();

        block.addAll(webClient.post()
                .retrieve()
                .bodyToMono(List.class).block());

        return block;
    }
}
