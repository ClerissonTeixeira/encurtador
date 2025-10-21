package br.com.unifacol.encurtadorurl.controller;

import br.com.unifacol.encurtadorurl.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/shorten")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl){
        var code = urlShortenerService.shortenUrl(originalUrl);
        return ResponseEntity.ok("http://localhost:8080/shorten/"+code);
    }

    @GetMapping("/{code}")
    public RedirectView redirectUrl(@PathVariable String code){
        var originalUrl = urlShortenerService.getOriginalUrl(code);
        return new RedirectView(originalUrl != null ? originalUrl : "/notfound.html");
    }
}
