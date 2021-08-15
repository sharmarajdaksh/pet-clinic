package com.example.petclinic.services.map;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.services.OwnerService;
import com.example.petclinic.services.PetService;
import com.example.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
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
            throw new RuntimeException("Pet is required");
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
        return null;
    }
}
