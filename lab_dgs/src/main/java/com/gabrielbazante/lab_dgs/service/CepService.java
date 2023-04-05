package com.gabrielbazante.lab_dgs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbazante.lab_dgs.client.ViaCepClient;
import com.gabrielbazante.lab_dgs.controller.response.CepResponse;

@Service
public class CepService {
    
    private ViaCepClient viaCepClient;

    @Autowired
    public CepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public CepResponse getByCep(String cep) {
        try {
            CepResponse cepResponse = viaCepClient.getAdress(cep).getBody();
            return cepResponse;
        } catch (Exception e) {
            throw new RuntimeException("Erro no serviço de CEP");
        }
        
    }
}
