package com.anubhav.url_shortener_backend.controller;

import com.anubhav.url_shortener_backend.dto.GenerateShortUrlDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tiny")
public class ShortenerController {

    @GetMapping("/{short_url}")
    public String getUrl(@PathVariable("short_url") String shortUrl) {
        return shortUrl;
    }

    @PostMapping
    public String generateShortUrl(@RequestBody GenerateShortUrlDTO request){
        return "";
    }
}
