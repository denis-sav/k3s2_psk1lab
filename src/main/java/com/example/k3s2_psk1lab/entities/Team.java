package com.example.k3s2_psk1lab.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@NamedQueries({
        @NamedQuery(name = "Team.findAll", query = "select t from Team as t")
})
public class Team {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic(optional = false)
    private String Name;

    @Basic(optional = false)
    private String City;

    @OneToMany(mappedBy = "team")
    private List<Athlete> athletes;


}
