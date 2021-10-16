package com.example.backendshelter.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToMany(mappedBy = "shelterId")
//    private List<Volunteer> volunteers;

    private String name;

    @Enumerated
    private Location location;

    private String address;

    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets;
}
