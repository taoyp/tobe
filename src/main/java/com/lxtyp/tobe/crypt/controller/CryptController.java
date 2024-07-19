package com.lxtyp.tobe.crypt.controller;

import com.lxtyp.tobe.common.TobeConst;
import com.lxtyp.tobe.crypt.service.CryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TobeConst.REST_V1 + "/crypt")
public class CryptController {

    @Autowired
    CryptService cryptService;

    @GetMapping("/key")
    public String generateKey() throws Exception {
        return cryptService.generateKey();
    }

    @GetMapping("/iv")
    public String generateIv() {
        return cryptService.generateIv();
    }

    // http://localhost:8080/rest/v1/crypt/en?base=123
    @GetMapping("/en")
    public String forEnCrypt(@RequestParam("base") String base) throws Exception {
        return cryptService.forEnCrypt(base);
    }

    // http://localhost:8080/rest/v1/crypt/de?base=hXgSdoLrQ0DDeI65sp2Fig==
    @GetMapping("/de")
    public String forDeCrypt(@RequestParam("base") String base) throws Exception {
        return cryptService.forDeCrypt(base);
    }

    // http://localhost:8080/rest/v1/crypt/enFile
    @GetMapping("/enFile")
    public void forEnCryptFile() {
        cryptService.doEncrypt();
    }

    // http://localhost:8080/rest/v1/crypt/deFile
    @GetMapping("/deFile")
    public void forDeCryptFile() {
        cryptService.doDecrypt();
    }

}
