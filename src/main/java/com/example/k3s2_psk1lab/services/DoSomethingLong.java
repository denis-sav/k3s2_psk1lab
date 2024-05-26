package com.example.k3s2_psk1lab.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class DoSomethingLong implements Serializable {

    public Integer generateLuckyNumber() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return new Random().nextInt(100);
    }
}
