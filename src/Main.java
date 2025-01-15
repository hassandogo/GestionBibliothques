import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialisation des DAO
        LivreDAO livreDAO = new LivreDAO();
        MembreDAO membreDAO = new MembreDAO();
        EmpruntDAO empruntDAO = new EmpruntDAO();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            afficherMenu();

            System.out.print("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterLivre(livreDAO, scanner);
                    break;
                case 2:
                    rechercherLivre(livreDAO, scanner);
                    break;
                case 3:
                    inscrireMembre(membreDAO, scanner);
                    break;
                case 4:
                    enregistrerEmprunt(empruntDAO, scanner);
                    break;
                case 5:
                    afficherEmpruntsEnRetard(empruntDAO);
                    break;
                case 6:
                    System.out.println("Fermeture du programme...");
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        // Fermeture de la connexion à la base de données
        DBConnection.closeConnection();
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n===== MENU GESTION BIBLIOTHÈQUE =====");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Rechercher un livre");
        System.out.println("3. Inscrire un membre");
        System.out.println("4. Enregistrer un emprunt");
        System.out.println("5. Afficher les emprunts en retard");
        System.out.println("6. Quitter");
    }

    private static void ajouterLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Entrez le titre du livre : ");
        String titre = scanner.nextLine();
        System.out.print("Entrez l'auteur du livre : ");
        String auteur = scanner.nextLine();
        System.out.print("Entrez la catégorie du livre : ");
        String categorie = scanner.nextLine();
        System.out.print("Entrez le nombre d'exemplaires : ");
        int nombreExemplaires = scanner.nextInt();

        Livre livre = new Livre(0, titre, auteur, categorie, nombreExemplaires);
        if (livreDAO.ajouterLivre(livre)) {
            System.out.println("Livre ajouté avec succès !");
        } else {
            System.out.println("Erreur lors de l'ajout du livre.");
        }
    }

    private static void rechercherLivre(LivreDAO livreDAO, Scanner scanner) {
        System.out.print("Rechercher par (1: Titre, 2: Catégorie) : ");
        int critere = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        if (critere == 1) {
            System.out.print("Entrez le titre du livre : ");
            String titre = scanner.nextLine();
            Livre livre = livreDAO.rechercherParTitre(titre);
            if (livre != null) {
                System.out.println(livre);
            } else {
                System.out.println("Aucun livre trouvé avec ce titre.");
            }
        } else if (critere == 2) {
            System.out.print("Entrez la catégorie du livre : ");
            String categorie = scanner.nextLine();
            livreDAO.rechercherParCategorie(categorie).forEach(System.out::println);
        } else {
            System.out.println("Critère invalide.");
        }
    }


    private static void inscrireMembre(MembreDAO membreDAO, Scanner scanner) {
        System.out.print("Entrez le nom du membre : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom du membre : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez l'email du membre : ");
        String email = scanner.nextLine();

        LocalDate adhesionDate = LocalDate.now();

        Membre membre = new Membre(0, nom, prenom, email, adhesionDate); // La date d'adhésion peut être gérée ici

        if (membreDAO.inscrireMembre(membre)) {
            System.out.println("Membre inscrit avec succès !");
        } else {
            System.out.println("Erreur lors de l'inscription du membre.");
        }
    }


    private static void enregistrerEmprunt(EmpruntDAO empruntDAO, Scanner scanner) {
        System.out.print("Entrez l'ID du membre : ");
        int membreId = scanner.nextInt();
        System.out.print("Entrez l'ID du livre : ");
        int livreId = scanner.nextInt();

        if (empruntDAO.enregistrerEmprunt(membreId, livreId)) {
            System.out.println("Emprunt enregistré avec succès !");
        } else {
            System.out.println("Erreur lors de l'enregistrement de l'emprunt.");
        }
    }

    private static void afficherEmpruntsEnRetard(EmpruntDAO empruntDAO) {
        System.out.println("Liste des emprunts en retard :");
        empruntDAO.getEmpruntsEnRetard().forEach(System.out::println);
    }
}
