package com.example.backendshelter.controller;

import com.example.backendshelter.controller.request.CreatePetRQ;
import com.example.backendshelter.controller.request.CreateShelterRequest;
import com.example.backendshelter.controller.response.PetResponse;
import com.example.backendshelter.controller.response.ShelterResponse;
import com.example.backendshelter.model.Pet;
import com.example.backendshelter.model.Shelter;
import com.example.backendshelter.service.ShelterService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }


    @GetMapping(value = "/shelters/{name}")
    public ShelterResponse getShelterByName(@PathVariable (value = "name") String name)
    {
        Shelter shelter = shelterService.findByName(name);
        return new ShelterResponse(
                shelter.getId(),
                shelter.getName(),
                shelter.getLocation(),
                shelter.getAddress()
        );
    }
    @GetMapping(value = "/shelters/{id}")
    public ShelterResponse getShelterByName(@PathVariable (value = "id") Long id)
    {
        Shelter shelter = shelterService.existsById(id);
        return new ShelterResponse(
                shelter.getId(),
                shelter.getName(),
                shelter.getLocation(),
                shelter.getAddress()
        );
    }

    @PostMapping(value = "/shelters")
    public List<ShelterResponse> createShelter(@RequestBody List<CreateShelterRequest> createShelterRequests) {
        List<Shelter> shelters = new ArrayList<>();
        for (CreateShelterRequest createShelterRequest : createShelterRequests) //Transform  createShelterRequest List in a List of shelters
        {
            shelters.add(Shelter.builder()
                    .name(createShelterRequest.getName())
                    .location(createShelterRequest.getLocation())
                    .address(createShelterRequest.getAddress())
                    .build());
        }
        shelterService.save(shelters);

        List<ShelterResponse> shelterResponses = new ArrayList<>();
        for (Shelter shelter : shelters) {
            shelterResponses.add(  //Transform Shelter in ShelterResponse and add to List
                    new ShelterResponse(
                            shelter.getId(),
                            shelter.getName(),
                            shelter.getLocation(),
                            shelter.getAddress())
            );
        }
        return shelterResponses;
    }

    @PostMapping(value = "/shelters/{id}/pets")
    public List<PetResponse> addPetsToShelter(@PathVariable(value = "id") Long id, @RequestBody List<CreatePetRQ> createPetRQs)
    {
        List<Pet> pets = new ArrayList<>();
        for (CreatePetRQ createPetRQ : createPetRQs) //Transform  createShelterRequest List in a List of shelters
        {
            pets.add(Pet.builder()
                    .petType(createPetRQ.getPetType())
                    .name(createPetRQ.getName())
                    .build());
        }
        shelterService.addPetsToShelter(id, pets);
        List<PetResponse> petResponses = new ArrayList<>();
        for (Pet pet : pets) {
            petResponses.add(  //Transform Shelter in ShelterResponse and add to List
                    new PetResponse(
                            pet.getId(),
                            pet.getPetType(),
                            pet.getName()
            ));
        }
        return petResponses;
    }

    @DeleteMapping(value = "/shelters/{id}")
    public void deleteShelter (@PathVariable(value = "id") Long id) {
        shelterService.deleteById(id);
    }

}



