package com.example.k3s2_psk1lab.persistence;

import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.entities.Competition;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Setter
@ApplicationScoped
public class CompetitionsDAO {

    @Inject
    private EntityManager em;

    public List<Competition> loadAll() {
        return em.createNamedQuery("Competition.findAll", Competition.class).getResultList();
    }

    public void persist(Competition competition){
        this.em.persist(competition);
    }

    public void remove(Competition competition){
        em.remove(competition);
    }

    public Competition update(Long id, String name) {
        Competition competition = em.find(Competition.class,id);
        competition.setName(name);
        return competition;
    }

    public Competition updateAthletes(Competition competition) {
        System.out.println(competition.toString());
        return em.merge(competition);
    }

    public Competition findOne(Long id) {
        return em.find(Competition.class, id);
    }
}

