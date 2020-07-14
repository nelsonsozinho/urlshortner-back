package br.com.nms.urlshortener.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UrlRequest {

    private String longUrl;

    private Date expiresDate;

}
