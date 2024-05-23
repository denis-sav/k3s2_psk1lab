package com.example.k3s2_psk1lab.services;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Alternative
@Dependent
public class SecondaryService extends TextTransformer {
    @Override
    public String toUpper(String input) {
        return "Secondary: " + input.toUpperCase();
    }
}
