package com.example.k3s2_psk1lab.services;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;

@Alternative
@Dependent
public class SecondaryService extends TextTransformer implements Serializable {
    @Override
    public String toUpper(String input) {
        return "Secondary: " + input.toUpperCase();
    }
}
