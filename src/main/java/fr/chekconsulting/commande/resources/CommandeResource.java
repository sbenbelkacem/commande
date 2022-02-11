package fr.chekconsulting.commande.resources;

import fr.chekconsulting.commande.dto.CommandeDto;
import fr.chekconsulting.commande.entities.Commande;
import fr.chekconsulting.commande.services.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1")
public class CommandeResource {

    private final CommandeService commandeService;

    public CommandeResource(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("commande/{id}")
    public ResponseEntity<CommandeDto> getCommande(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(commandeService.getById(id));
    }

    @PostMapping("commande/add")
    public ResponseEntity createCommande(@RequestBody Commande commande) {
        Integer id = commandeService.createCommande(commande);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1/commande/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
