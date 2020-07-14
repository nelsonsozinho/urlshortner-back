package br.com.nms.urlshortener.service;

import br.com.nms.urlshortener.domain.Url;
import br.com.nms.urlshortener.domain.User;
import br.com.nms.urlshortener.domain.request.UrlRequest;
import br.com.nms.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private BaseConversion baseConversion;

    @Autowired
    private UserService userService;

    public String convertToShort(UrlRequest urlRequest) {
        User user = userService.loadCurrentUser();
        Url url = new Url();
        url.setOriginalUrl(urlRequest.getLongUrl());
        url.setCreationDate(new Date());
        url.setExpirationDate(urlRequest.getExpiresDate());
        url.setUser(user);

        Url entity = urlRepository.save(url);

        return baseConversion.encode(entity.getIdUrl());
    }

    public String getOriginalUrl(String shortUrl) {
        long id = baseConversion.decode(shortUrl);
        final Url entity = urlRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if(entity.getExpirationDate() != null && entity.getExpirationDate().before(new Date())) {
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired");
        }

        return entity.getOriginalUrl();
    }

}
