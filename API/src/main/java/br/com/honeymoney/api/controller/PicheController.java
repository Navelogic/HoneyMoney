package br.com.honeymoney.api.controller;

import br.com.honeymoney.api.service.PicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/piches")
public class PicheController {

    @Autowired
    private PicheService picheService;

    // Test
    @RequestMapping("/test")
    public String test() {
        return "PicheController OK!";
    }
    // END Test
}
