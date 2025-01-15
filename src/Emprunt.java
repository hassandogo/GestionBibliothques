import java.time.LocalDate;
import java.time.LocalDate;


public class Emprunt {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur
    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    // Getters et setters
    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public void enregistrerEmprunt(int membreId, int livreId) {
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetourPrevue = dateEmprunt.plusDays(14); // Exemple : durée d'emprunt de 14 jours

        System.out.println("Emprunt enregistré pour le membre " + membreId +
                " avec le livre " + livreId +
                ". Date retour prévue : " + dateRetourPrevue);
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthode pour afficher les détails de l'emprunt
    public void afficherDetails() {
        System.out.println("ID Emprunt: " + idEmprunt + ", Membre ID: " + membreId +
                ", Livre ID: " + livreId + ", Date d'emprunt: " + dateEmprunt +
                ", Date retour prévue: " + dateRetourPrevue +
                (dateRetourEffective != null ? ", Date retour effective: " + dateRetourEffective : ", Non retourné"));
    }
}
