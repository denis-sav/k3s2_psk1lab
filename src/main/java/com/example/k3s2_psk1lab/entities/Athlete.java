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
        @NamedQuery(name = "Athlete.findAll", query = "select a from Athlete as a")
})
public class Athlete {
    @Id
//    @Column(name = "Athlete_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(columnDefinition="varchar(255)")
    private String Name;

    @Basic(optional = false)
    private String Surname;

    @ManyToOne(optional = true)
    private Team team;

    @ManyToMany(mappedBy = "athletes")
    private List<Competition> competitions;

    @Version
    private Integer version;

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", team=" + team +
                '}';
    }

    @Basic
    private Integer jerseyNumber;

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
}
