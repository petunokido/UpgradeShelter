package com.example.backendshelter.controller.request;

import com.example.backendshelter.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CreateShelterRequest
{
    private String name;

    private Location location;

    private String address;
}
