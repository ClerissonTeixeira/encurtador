package br.com.unifacol.encurtadorurl.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UrlShortenerService {

    private final Map<String , String> shortToOriginal = new HashMap<>();

    public String shortenUrl(String originalUrl) {
        log.info("Original URL: {}", originalUrl);
        var shortCode = UUID.randomUUID().toString().substring(0, 6);
        shortToOriginal.put(shortCode, originalUrl);
        log.info("Short URL: {}", shortCode);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        return shortToOriginal.get(shortCode);
    }
}
