package com.restive.boxoffice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "age_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer minAge;

    private Integer maxAge;
}
