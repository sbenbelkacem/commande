package fr.chekconsulting.commande.services;

import fr.chekconsulting.commande.dto.CommandeDto;
import fr.chekconsulting.commande.dto.ProductDto;
import fr.chekconsulting.commande.entities.Commande;
import fr.chekconsulting.commande.entities.CommandeStatus;
import fr.chekconsulting.commande.repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private RestTemplate restTemplate;

    @Value("${catalog.service.url}")
    String catalogServiceUrl;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
        this.restTemplate = new RestTemplate();
    }

    public CommandeDto getById(Integer id) {
        Commande commande = commandeRepository.getById(id);
        ProductDto product = restTemplate.getForObject(catalogServiceUrl + "/product/" + commande.getProductId(), ProductDto.class);
        return CommandeDto.builder()
                .id(commande.getId())
                .quantity(commande.getQuantity())
                .status(commande.getStatus().toString())
                .orderDate(commande.getOrderDate())
                .totalPrice(commande.getTotalPrice())
                .productName(product != null ? product.getName() : null)
                .build();
    }

    public Integer createCommande(Commande commande) {
        ProductDto product = restTemplate.getForObject(catalogServiceUrl + "/product/" + commande.getProductId(), ProductDto.class);
        commande.setTotalPrice(product != null ? product.getPrice() * commande.getQuantity() : null);
        commande.setStatus(CommandeStatus.A_VALIDER);
        commande.setOrderDate(LocalDateTime.now());
        return commandeRepository.save(commande).getId();
    }

    public List<CommandeDto> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        List<CommandeDto> commandeDtos = new ArrayList<>();
        commandes.forEach(
                commande -> {
                    ProductDto product = restTemplate.getForObject(catalogServiceUrl + "/product/" + commande.getProductId(), ProductDto.class);
                    commandeDtos.add(
                            CommandeDto.builder()
                                    .id(commande.getId())
                                    .quantity(commande.getQuantity())
                                    .status(commande.getStatus().toString())
                                    .orderDate(commande.getOrderDate())
                                    .totalPrice(commande.getTotalPrice())
                                    .productName(product != null ? product.getName() : null)
                                    .build());
                }
        );
        return commandeDtos;
    }
}
