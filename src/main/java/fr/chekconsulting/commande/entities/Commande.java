package fr.chekconsulting.commande.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Commande {
    @Id
    private Integer id;
    private Integer productId;
    @Enumerated(EnumType.STRING)
    private CommandeStatus status;
    private Integer quantity;
    private Integer totalPrice;
    private LocalDateTime orderDate;
}
