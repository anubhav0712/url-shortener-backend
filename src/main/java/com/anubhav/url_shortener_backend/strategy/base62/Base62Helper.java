package com.anubhav.url_shortener_backend.strategy.base62;

import org.springframework.stereotype.Component;

@Component
public class Base62Helper {

    private static final String CHARACTER_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char[] CHARACTER_SET = CHARACTER_STR.toCharArray();

    public String encode(long num){
        int base = CHARACTER_SET.length;
        if(num == 0){
            return "0";
        }
        String res = "";
        long rem = 0;
        while(num > 0){
            rem = num % base;
            res += CHARACTER_SET[(int)rem];
            num = num / base;
        }
        return res;
    }

    public long decode(String code){
        int base = CHARACTER_SET.length;
        long res = 0;
        for(char c : code.toCharArray()){
           res = res * base + CHARACTER_STR.indexOf(c);
        }
        return res;
    }

    public String fixed_length_encode(long num, int length){
        String res = encode(num);
        int diff = length - res.length();
        while(diff > 0){
            res = "0"+res;
            diff--;
        }
        return res;
    }
}
