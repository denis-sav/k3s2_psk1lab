package com.example.k3s2_psk1lab.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class TextTransformer {

    public String toUpper(String input) {
        return "Default: " + input.toUpperCase();
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed at " + new Date());
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die at " + new Date());
    }
}
