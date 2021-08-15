package com.example.petclinic.services.springdatajpa;

import com.example.petclinic.model.PetType;
import com.example.petclinic.repositories.PetTypeRepository;
import com.example.petclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

public class PetTypeServiceSDJpa implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceSDJpa(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);

    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
