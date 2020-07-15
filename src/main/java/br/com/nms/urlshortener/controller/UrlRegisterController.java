package br.com.nms.urlshortener.controller;

import br.com.nms.urlshortener.domain.request.UrlRequest;
import br.com.nms.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UrlRegisterController {

    @Autowired
    private UrlService urlService;

    @PostMapping("short")
    public String convert(@RequestBody UrlRequest requestObject) {
        return urlService.convertToShort(requestObject);
    }

}
