package com.gb.tickets.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CustomerTicket {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticketId;
}
