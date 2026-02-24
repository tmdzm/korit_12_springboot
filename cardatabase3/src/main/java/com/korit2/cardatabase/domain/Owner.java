package com.korit2.cardatabase.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long ownerId;

    @Getter @Setter @NonNull
    private String firstName;
    @Getter @Setter @NonNull
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @Getter @Setter
    private List<Car> cars;
}