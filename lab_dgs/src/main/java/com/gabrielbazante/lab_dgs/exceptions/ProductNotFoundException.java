package com.gabrielbazante.lab_dgs.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String string) {
        super(string);
    }
}
