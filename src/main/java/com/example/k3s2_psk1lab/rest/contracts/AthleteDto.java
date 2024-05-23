package com.example.k3s2_psk1lab.rest.contracts;

import com.example.k3s2_psk1lab.entities.Competition;
import com.example.k3s2_psk1lab.entities.Team;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
public class AthleteDto {

    private String Name;

    private String Surname;

    private Integer JerseyNumber;

    private Team team;

    private List<Competition> competitions;

}