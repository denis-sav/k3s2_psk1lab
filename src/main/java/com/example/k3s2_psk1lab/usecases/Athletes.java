package com.example.k3s2_psk1lab.usecases;

import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.interceptors.LoggedInvocation;
import com.example.k3s2_psk1lab.persistence.AthletesDAO;
import com.example.k3s2_psk1lab.persistence.CompetitionsDAO;
import com.example.k3s2_psk1lab.entities.Competition;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Model
public class Athletes {

    @Inject
    private AthletesDAO athletesDAO;


    @Getter @Setter
    private Athlete athleteToCreate = new Athlete();

    @Getter
    private List<Athlete> allAthletes;

    @PostConstruct
    public void init(){
        loadAllAthletes();
    }

    @Transactional
    public void createAthlete(){
        this.athletesDAO.persist(athleteToCreate);
    }

    public void removeAthlete(Athlete athlete){
        this.athletesDAO.remove(athlete);
    }

    private void loadAllAthletes(){
        this.allAthletes = athletesDAO.loadAll();
    }

    @Transactional
    public void updateAthlete(Athlete athlete) {
        try {
            athletesDAO.update(athlete);
        } catch (OptimisticLockException e) {
            // Handle the exception (e.g., notify the user, retry the transaction, etc.)
            System.out.println("Optimistic lock exception occurred: " + e.getMessage());
            throw e;  // handle accordingly
        }
    }
}