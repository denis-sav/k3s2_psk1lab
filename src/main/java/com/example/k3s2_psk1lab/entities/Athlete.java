package com.example.k3s2_psk1lab.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
    private String Name;

    @Basic(optional = false)
    private String Surname;

    @ManyToOne(optional = true)
    private Team team;

    @ManyToMany(mappedBy = "athletes")
    private List<Competition> competitions;

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", team=" + team +
                '}';
    }
}
