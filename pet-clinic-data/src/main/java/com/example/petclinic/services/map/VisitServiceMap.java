package com.example.petclinic.services.map;

import com.example.petclinic.model.Visit;
import com.example.petclinic.services.VisitService;

import java.util.Set;

public class VisitServiceMap extends AbstractServiceMap<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if (object == null) return null;
        if (object.getPet() == null || object.getPet().getOwner() == null || object.getPet().getId() == null
                || object.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Invalid visit");
        }

        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return findById(id);
    }
}
