package com.anubhav.url_shortener_backend.strategy;

public interface ShortenStrategy {

    String shorten(String url);

    String expand(String shortCode);

    StrategyType strategyType();

    String strategyShortKey();
}
