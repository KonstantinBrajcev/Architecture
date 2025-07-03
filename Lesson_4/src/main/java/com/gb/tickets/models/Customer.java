package com.gb.tickets.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private Role role;
    private Float balance;
}
