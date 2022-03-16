package br.com.guini.httpblocknoblock.server;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {

    @PostMapping
    public List<Integer> doAnything() throws InterruptedException {

        Thread.sleep(100L);

        return List.of(1, 2, 3, 4, 5, 6, 7, 8, 1, 22, 3, 123, 123, 14, 6436, 73);
    }
}
