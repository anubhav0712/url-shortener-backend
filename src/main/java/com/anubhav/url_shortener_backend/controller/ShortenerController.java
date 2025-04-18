package com.anubhav.url_shortener_backend.controller;

import com.anubhav.url_shortener_backend.dto.GenerateShortUrlDTO;
import com.anubhav.url_shortener_backend.service.ShortenerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiny")
public class ShortenerController {

    private final ShortenerService shortenerService;

    public ShortenerController(ShortenerService shortenerService){
        this.shortenerService = shortenerService;
    }

    @GetMapping("/{short_url}")
    public String getUrl(@PathVariable("short_url") String shortUrl) {
        return shortenerService.expand(shortUrl);
    }

    @PostMapping
    public String generateShortUrl(@RequestBody GenerateShortUrlDTO request){
        return shortenerService.shorten(request);
    }
}
