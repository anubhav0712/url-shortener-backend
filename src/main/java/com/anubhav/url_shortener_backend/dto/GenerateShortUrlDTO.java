package com.anubhav.url_shortener_backend.dto;

import com.anubhav.url_shortener_backend.strategy.StrategyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateShortUrlDTO {

    private String url;

    private StrategyType StrategyType;
}
