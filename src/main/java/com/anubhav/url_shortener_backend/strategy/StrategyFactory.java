package com.anubhav.url_shortener_backend.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class StrategyFactory {

    private Map<StrategyType, ShortenStrategy> strategyMap;
    private Map<String, ShortenStrategy> strategyShortKeyMap;

    public StrategyFactory(List<ShortenStrategy> strategies) {
        strategyMap = new HashMap<>();
        strategyShortKeyMap = new HashMap<>();
        for (ShortenStrategy strategy : strategies) {
            strategyMap.put(strategy.strategyType(), strategy);
            strategyShortKeyMap.put(strategy.strategyShortKey(), strategy);
        }
    }

    public ShortenStrategy getStrategy(StrategyType type) {
        return strategyMap.get(type);
    }

    public ShortenStrategy getStrategy(String shortKey) {
        Optional<Map.Entry<String, ShortenStrategy>> res = strategyShortKeyMap.entrySet().stream().filter(e -> shortKey.startsWith(e.getKey())).findFirst();
        return res.isPresent() ? res.get().getValue() : null;
    }
}
