package com.example.k3s2_psk1lab.usecases;

import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.persistence.CompetitionsDAO;
import com.example.k3s2_psk1lab.entities.Competition;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Model
public class Competitions {
    @Inject
    private CompetitionsDAO competitionsDAO;

    @Getter @Setter
    private Competition selectedCompetition;

    public void select_Competition() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId = Long.parseLong(requestParameters.get("competitionId"));
        this.selectedCompetition = competitionsDAO.findOne(competitionId);
    }

    @Getter @Setter
    private Competition competitionToCreate = new Competition();


    @Getter @Setter
    private Competition competitionToRemove = new Competition();

    @Getter
    private List<Competition> allCompetitions;

    @PostConstruct
    public void init(){
        loadAllCompetition();
    }

    @Transactional
    public void createCompetition(){
        this.competitionsDAO.persist(competitionToCreate);
    }

    @Transactional
    public void removeCompetition() {
        Map<String, String> requestParameters =
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId = Long.parseLong(requestParameters.get("competitionId"));
        this.competitionToRemove = competitionsDAO.findOne(competitionId);
        this.competitionsDAO.remove(competitionToRemove);
    }
    @Transactional
    private void loadAllCompetition(){
        this.allCompetitions = competitionsDAO.loadAll();
    }

}
