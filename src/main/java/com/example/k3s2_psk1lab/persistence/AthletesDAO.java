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
public class AthletesDAO {
    @Inject
    private EntityManager em;

    public List<Athlete> loadAll() {
        return em.createNamedQuery("Athlete.findAll", Athlete.class).getResultList();
    }

    public void persist(Athlete athlete){
        this.em.persist(athlete);
    }

    public void remove(Athlete athlete){
        this.em.remove(athlete);
    }

    public Athlete findOne(Long id) {
        return em.find(Athlete.class, id);
    }

}

