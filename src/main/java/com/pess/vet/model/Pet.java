package com.pess.vet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name ;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING) // Salva o nome da espécie como texto no banco
    private Specie specie;

    @Column(name = "breed", nullable = false)
    private String breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ElementCollection
    @CollectionTable(name = "pet_vaccinations", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "vaccination")
    private List<String> vaccinations;

}
