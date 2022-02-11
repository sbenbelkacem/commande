package fr.chekconsulting.commande.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto {
    private Integer id;
    private String productName;
    private String status;
    private Integer quantity;
    private Integer totalPrice;
    private LocalDateTime orderDate;
}
