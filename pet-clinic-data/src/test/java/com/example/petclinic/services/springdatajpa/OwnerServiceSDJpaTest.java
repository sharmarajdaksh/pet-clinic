package com.example.petclinic.services.springdatajpa;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSDJpaTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceSDJpa service;

    final Long ownerId = 1L;
    final String lastName = "smith";
    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner returnedOwner = service.findById(ownerId);

        assertNotNull(returnedOwner);
        assertEquals(ownerId, returnedOwner.getId());
        verify(ownerRepository, times(1)).findById(longThat(argument -> argument == ownerId));
    }

    @Test
    void findByIdNullReturn() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner returnedOwner = service.findById(ownerId);

        assertNull(returnedOwner);
        verify(ownerRepository, times(1)).findById(longThat(argument -> argument == ownerId));
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(ownerId).lastName(lastName).build();
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        service.delete(owner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);
        verify(ownerRepository, times(1)).deleteById(longThat(argument -> argument == ownerId));
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner smith = service.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(lastName, owner.getLastName());
        assertEquals(ownerId, owner.getId());
        verify(ownerRepository, times(1)).findByLastName(matches(lastName));
    }
}