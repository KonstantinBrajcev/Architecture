package com.gb.tickets.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Bus {
    @Id
    private Integer id;
    private String route;
    private LocalDateTime departure;
    private LocalDateTime arrival;
}
