package com.gabrielbazante.lab_dgs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gabrielbazante.lab_dgs.controller.response.CepResponse;

@FeignClient(name = "viacep-api", url = "viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("/{cep}/json")
    ResponseEntity<CepResponse> getAdress(@PathVariable String cep);


}
