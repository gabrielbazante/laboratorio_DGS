package com.gabrielbazante.lab_dgs.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbazante.lab_dgs.controller.response.CepResponse;
import com.gabrielbazante.lab_dgs.service.CepService;

@RestController
@RequestMapping("/consulta-cep")
public class CepController {
    
    private CepService cepService;
    
    @Autowired
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public CepResponse getByCep(@PathVariable String cep) {
        return cepService.getByCep(cep);
    }

}
