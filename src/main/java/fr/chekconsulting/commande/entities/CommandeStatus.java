package fr.chekconsulting.commande.entities;

public enum CommandeStatus {
    A_VALIDER("A valider"),
    EN_COURS_DE_LIVRAISON("En cours de livraison"),
    LIVRE("Livrée"),
    COMFIRME("Confirmée");
    private final String label;

    CommandeStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
