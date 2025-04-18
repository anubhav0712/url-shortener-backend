package com.anubhav.url_shortener_backend.dto;

import com.anubhav.url_shortener_backend.strategy.StrategyType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class GenerateShortUrlDTO {

    private String url;

    @JsonProperty("strategy_type")
    private StrategyType strategyType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(StrategyType strategyType) {
        this.strategyType = strategyType;
    }
}
