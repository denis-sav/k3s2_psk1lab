package com.example.k3s2_psk1lab.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import com.example.k3s2_psk1lab.interceptors.LoggedInvocation;
import com.example.k3s2_psk1lab.persistence.AthletesDAO;
import com.example.k3s2_psk1lab.persistence.TeamsDAO;
import com.example.k3s2_psk1lab.entities.Athlete;
import com.example.k3s2_psk1lab.entities.Team;

@Model
public class AthletesForTeam implements Serializable {

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private AthletesDAO athletesDAO;

    @Getter @Setter
    private Team team;

    @Getter @Setter
    private Athlete athleteToCreate = new Athlete();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long teamId = Long.parseLong(requestParameters.get("teamId"));
        this.team = teamsDAO.findOne(teamId);
    }

    @Transactional
    @LoggedInvocation
    public void createAthlete() {
        athleteToCreate.setTeam(this.team);
        athletesDAO.persist(athleteToCreate);
    }
}
