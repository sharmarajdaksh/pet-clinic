package com.example.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public boolean isNew() {
        return this.getId() == null;
    }

    @Builder
    public Pet(Long id, PetType petType, String name, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.petType = petType;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }
}
