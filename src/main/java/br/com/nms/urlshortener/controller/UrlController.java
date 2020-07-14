package br.com.nms.urlshortener.controller;

import br.com.nms.urlshortener.domain.request.UrlRequest;
import br.com.nms.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("short")
    public String convert(@RequestBody UrlRequest requestObject) {
        return urlService.convertToShort(requestObject);
    }

    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        URI urlCreated = URI.create(url);
        return ResponseEntity.status(HttpStatus.FOUND).location(urlCreated).build();
    }

}
