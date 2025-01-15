import java.time.LocalDate; // Import nécessaire pour utiliser LocalDate
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {
    private final List<Emprunt> emprunts = new ArrayList<>();

    // Enregistrer un emprunt
    public void enregistrerEmprunt(Emprunt emprunt) {
        emprunt.setIdEmprunt(emprunts.size() + 1); // Génération d'ID simple
        emprunts.add(emprunt);
    }

    // Obtenir les emprunts en retard
    public List<Emprunt> getEmpruntsEnRetard() {
        List<Emprunt> enRetard = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getDateRetourEffective() == null &&
                    LocalDate.now().isAfter(emprunt.getDateRetourPrevue())) {
                enRetard.add(emprunt);
            }
        }
        return enRetard;
    }

    public boolean enregistrerEmprunt(int membreId, int livreId) {
        return false;
    }
}
