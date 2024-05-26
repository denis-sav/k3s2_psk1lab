package com.example.k3s2_psk1lab.usecases;

import com.example.k3s2_psk1lab.interceptors.LoggedInvocation;
import com.example.k3s2_psk1lab.persistence.AthletesDAO;
import com.example.k3s2_psk1lab.persistence.CompetitionsDAO;
import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.entities.Competition;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.example.k3s2_psk1lab.services.TextTransformer;
import lombok.Getter;
import lombok.Setter;

@Model
public class CompetingAthletes {

    @Inject
    private AthletesDAO athletesDAO;

    @Getter @Setter
    private Competition competitionToUpdate = new Competition();

    @Inject
    private CompetitionsDAO competitionsDAO;

    @Getter @Setter
    private Athlete selectedAthlete = new Athlete();

    @Getter @Setter
    private Competition competition = new Competition();

    @Getter
    private List<Athlete> allParticipants;

    @Inject
    private TextTransformer textTransformer;

    @Transactional
    public void updateCompetition() {
        String name = textTransformer.toUpper(competitionToUpdate.getName());
        competition = this.competitionsDAO.update(competition.getId(), name);
    }

    @Transactional
    public void createActiveAthlete() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String competitionIdStr = requestParameters.get("competitionId");

        if (competitionIdStr == null || competitionIdStr.isEmpty()) {
            // Handle the error gracefully, e.g., log the error, display a message to the user, etc.
            throw new IllegalArgumentException("Competition ID is missing or invalid.");
        }

        try {
            Long competitionId = Long.parseLong(competitionIdStr);

            this.selectedAthlete = this.athletesDAO.findOne(selectedAthlete.getId());
            this.competition = this.competitionsDAO.findOne(competitionId);

            List<Athlete> athletes = this.competition.getAthletes();
            athletes.add(this.selectedAthlete);
            this.competition.setAthletes(athletes);
            competition = this.competitionsDAO.updateAthletes(this.competition);
        } catch (NumberFormatException e) {
            // Handle the error gracefully, e.g., log the error, display a message to the user, etc.
            throw new IllegalArgumentException("Competition ID is not a valid number.");
        }
    }



    private void loadAllParticipants(){ this.allParticipants = competition.getAthletes(); }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String competitionIdParam = requestParameters.get("competitionId");
        if (competitionIdParam != null && !competitionIdParam.isEmpty()) {
            try {
                Long competitionId = Long.parseLong(competitionIdParam);
                this.competition = competitionsDAO.findOne(competitionId);
                if (this.competition != null) {
                    loadAllParticipants();
                } else {
                    // Handle the case where the competition with the given ID does not exist
                }
            } catch (NumberFormatException e) {
                // Handle the case where the competitionIdParam cannot be parsed as a Long
                // For example, log the error or handle it gracefully
                e.printStackTrace();
            }
        } else {
            // Handle the case where the competitionIdParam is null or empty
        }
    }


}