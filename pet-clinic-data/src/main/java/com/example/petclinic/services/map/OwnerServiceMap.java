package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractServiceMap<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null) return null;

        if (object.getPets() == null) {
            object.setPets(new HashSet<Pet>());
        }

        object.getPets().forEach(pet -> {
            if (pet.getPetType() == null) {
                throw new RuntimeException("Pet Type is required");
            }
            pet.setPetType(petTypeService.save(pet.getPetType()));

            if (pet.getId() == null) {
                Pet savedPet = petService.save(pet);
                pet.setId(savedPet.getId());
            }
        });

        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll().stream()
                .filter(owner -> owner.getLastName().equals(lastName)).findFirst().orElse(null);
    }

    @Override
    public Set<Owner> findAllByLastNameLike(String lastName) {
        return super.findAll().stream()
                .filter(owner -> owner.getLastName().contains(lastName)).collect(Collectors.toSet());
    }
}
