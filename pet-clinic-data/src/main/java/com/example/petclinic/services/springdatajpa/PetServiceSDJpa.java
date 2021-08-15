package com.example.petclinic.services.springdatajpa;

import com.example.petclinic.model.Pet;
import com.example.petclinic.repositories.PetRepository;
import com.example.petclinic.services.PetService;

import java.util.HashSet;
import java.util.Set;

public class PetServiceSDJpa implements PetService {
    private final PetRepository petRepository;

    public PetServiceSDJpa(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
