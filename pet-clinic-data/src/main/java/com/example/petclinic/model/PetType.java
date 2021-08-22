package com.example.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return this.getName();
    }

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
