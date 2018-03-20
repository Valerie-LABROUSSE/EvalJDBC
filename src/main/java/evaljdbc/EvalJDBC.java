package evaljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

// Menu pour saisir, afficher et supprimer 1 aliment

public class EvalJDBC {

	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String menuChoice = "";
		Class.forName("org.postgresql.Driver");
		// connection à la base sql avec le chemin de la base, le user et le mot de pass
		// de postgresql
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"Labr13v");
		Aliment aliment = new Aliment();
		
		do {
			System.out.println("Menu Base aliments");
			System.out.println("1 - Lister les aliments");
			System.out.println("2 - Créer un aliment");
			System.out.println("3 - Supprimer un aliment");
			System.out.println("0 - FIN");
			System.out.println("Veuillez sélectionner votre choix : ");

			try {

				menuChoice = input.nextLine();

				switch (menuChoice) {
				case "1":
					aliment.listFood(conn);
					break;

				case "2":
					aliment.addFood(conn);
					System.out.println("Création bien effectuée");
					break;

				case "3":
  					int result = aliment.delFood(conn);
  					if (result == 0) {
						System.out.println("Cet aliment n'existe pas");	
					} else {
						System.out.println("Suppression de " + result + " aliment(s) bien effectuée");
		    		}
					break;
					
		        case "0":
		            System.out.println("Fin du traitement");
		            break;
				default:
					System.out.println("Commande inconnue");
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!menuChoice.equals("0"));

		input.close();
	}

}
