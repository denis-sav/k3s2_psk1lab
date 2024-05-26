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
        @NamedQuery(name = "Competition.findAll", query = "select c from Competition as c")
})
public class Competition {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    @Basic(optional = false)
    private String Name;

    @ManyToMany
    private List<Athlete> athletes;

    @Version
    private Integer version;

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", athletes=" + athletes +
                '}';
    }
}
