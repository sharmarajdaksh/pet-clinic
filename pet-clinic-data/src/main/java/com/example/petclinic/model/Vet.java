package com.example.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {
    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    private Set<Speciality> specialities = new HashSet<>();
}
