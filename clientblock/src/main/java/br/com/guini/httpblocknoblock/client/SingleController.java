package br.com.guini.httpblocknoblock.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/single")
public class SingleController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public List<Integer> doAnything() {
        var values = restTemplate.postForObject("http://localhost:8080/server", null, List.class);
        values.addAll(restTemplate.postForObject("http://localhost:8080/server", null, List.class));
        return values;
    }
}
