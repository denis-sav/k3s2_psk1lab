package com.example.k3s2_psk1lab.services;

import com.example.k3s2_psk1lab.usecases.CompetingAthletes;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class PirmasKomponentas {

    public String calculate() {
        CompetingAthletes competingAthletes;
//        if(input <= calculated)
//        {
//            return "Is amount correct (" + calculated + ")" + toString();
//        }
//            else
//        {
//            return "Not enough" + calculated + toString();
//        }
        return "";
    }

    @PostConstruct
    public void init() {
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }

}