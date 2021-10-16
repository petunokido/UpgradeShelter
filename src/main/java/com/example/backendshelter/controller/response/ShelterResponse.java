package com.example.backendshelter.controller.response;

import com.example.backendshelter.model.Location;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShelterResponse {
    private Long id;

    private String name;

    private Location location;

    private String address;
}
