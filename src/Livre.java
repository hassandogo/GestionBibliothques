public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaires;

    // Constructeur
    public Livre(int id, String titre, String auteur, String categorie, int nombreExemplaires) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getCategorie() { return categorie; }
    public int getNombreExemplaires() { return nombreExemplaires; }

    @Override
    public String toString() {
        return "Livre{id=" + id + ", titre='" + titre + "', auteur='" + auteur + "', categorie='" + categorie + "', nombreExemplaires=" + nombreExemplaires + '}';
    }
}
