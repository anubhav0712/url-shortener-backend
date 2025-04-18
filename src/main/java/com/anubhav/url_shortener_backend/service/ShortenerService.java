package com.anubhav.url_shortener_backend.service;

import com.anubhav.url_shortener_backend.dto.GenerateShortUrlDTO;
import com.anubhav.url_shortener_backend.strategy.ShortenStrategy;
import com.anubhav.url_shortener_backend.strategy.StrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class ShortenerService {

    private final StrategyFactory strategyFactory;

    public ShortenerService(StrategyFactory strategyFactory){
        this.strategyFactory = strategyFactory;
    }

    public String shorten(GenerateShortUrlDTO request){
        if(request == null || request.getStrategyType() == null){
            //TODO add exception
            return null;
        }

        ShortenStrategy strategy = strategyFactory.getStrategy(request.getStrategyType());

        return strategy.shorten(request.getUrl());
    }

    public String expand(String shortCode){
        if(shortCode == null){
            //TODO add more validation
            return null;
        }

        ShortenStrategy strategy = strategyFactory.getStrategy(shortCode);
        if(strategy == null){
            //TODO add exception
            return null;
        }

        return strategy.expand(shortCode);
    }
}
