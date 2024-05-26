package com.example.k3s2_psk1lab.persistence;

import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.entities.Competition;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
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

    @Transactional
    public Competition update(Competition competition) {
        System.out.println(competition.toString());
        return em.merge(competition);
    }
    @Transactional
    public Competition findOne(Long id) {
        Competition competition = em.find(Competition.class, id);
        if (competition != null) {
            em.lock(competition, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        }
        return competition;
    }
}

