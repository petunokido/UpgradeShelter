package com.example.backendshelter.service;

import com.example.backendshelter.model.Pet;
import com.example.backendshelter.model.Shelter;
import com.example.backendshelter.repository.PetRepository;
import com.example.backendshelter.repository.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final PetRepository petRepository;

    public ShelterService(ShelterRepository shelterRepository, PetRepository petRepository) {
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
    }

    public List<Shelter> save(List<Shelter> newShelters) {
        for (Shelter shelter : newShelters) {
            shelterRepository.save(shelter);
        }
        return newShelters;
    }

    public List<Pet> addPetsToShelter(Long id, List<Pet> pets) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isPresent()) {
            for (Pet pet : pets) {
                pet.setShelter(shelter.get());
                petRepository.save(pet);
            }
            return pets;
        }
        return null;
    }

    public Shelter findByName(String name) {

        Optional<Shelter> shelter = shelterRepository.findByName(name);
        if (shelter.isPresent()) {
            return shelter.get();
        }
        return null;
    }

    public Shelter existsById(Long id) {
        if(shelterRepository.existsById(id))
        {
            Optional<Shelter> shelter = shelterRepository.findById(id);
            return shelter.get();
        }
        return null;
    }

    public void deleteById(Long id) {

        Optional<Shelter> shelter = shelterRepository.findById(id);  // Get Summoner matching id
        if (shelter.isPresent()) {
            for (Pet pet : shelter.get().getPets())      //Delete all Skill in Summoner
            {
                petRepository.deleteById(pet.getId());
            }
            shelterRepository.deleteById(id);
        }
    }
}