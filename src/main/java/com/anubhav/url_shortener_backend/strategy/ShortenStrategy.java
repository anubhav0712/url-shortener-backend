package com.anubhav.url_shortener_backend.strategy;

public interface ShortenStrategy {

    String shorten(String url);

    StrategyType strategyType();

    String strategyShortKey();
}
