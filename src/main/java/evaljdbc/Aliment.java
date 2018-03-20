package evaljdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Aliment {

	private static Scanner input = new Scanner(System.in);
	public String nom = "";
	public int calories = 0 ;
	public int categorie = 0;
	public int regime = 0;
	
	// ***********************************************************************************************
	// saisie de l'aliment sur la console
	// ***********************************************************************************************

	public void addFood(Connection conn) throws ClassNotFoundException, SQLException {
		
		System.out.println("Veuillez saisir le nom d'un aliment : ");
		String nom = input.nextLine();
		System.out.println("Veuillez saisir le nombre de calories : ");
		String calories = input.nextLine();
		System.out.println(
				"Veuillez saisir la cat�gorie (1= viande, 2=fruits/l�gumes, 3= produits laitiers, 4= f�culents) : ");
		String categorie = input.nextLine();
		System.out.println("Veuillez saisir le r�gime (1 = omnivore, 2 = v�g�tarien, 3 = v�gan) : ");
		String regime = input.nextLine();

		String query = ("INSERT INTO public.aliment (nom, calories, idcategorie, idregime) VALUES ('" + nom + "' , "
				+ calories + " , " + categorie + " , " + regime + ")");

		Statement state = conn.createStatement();

		state.execute(query);

	}

	// ***********************************************************************************************
	// selection aliments avec leur categorie et regime : list� sur la console
	// ***********************************************************************************************

	public void listFood(Connection conn) throws SQLException {

		String query = "SELECT a.nom, a.calories, b.libelle as libcat, c.libelle as libreg FROM "
				+ "public.aliment a, public.categorie b, public.regime c WHERE a.idregime = c.idregime "
				+ "and a.idcategorie = b.idcategorie ;";

		Statement state = conn.createStatement();
		// L'objet ResultSet contient le r�sultat de la requ�te SQL
		ResultSet result = state.executeQuery(query);

		while (result.next()) {
			String nom = result.getString("nom");
			int calorie = result.getInt("calorieS");
			String libCategorie = result.getString("libcat");
			String libRegime = result.getString("libreg");
			System.out
					.println(nom + " nbr cal = " + calorie + " categorie = " + libCategorie + " r�gime = " + libRegime);
		}
	}

	// ***********************************************************************************************
	// delete de l'aliment saisi sur la console
	// ***********************************************************************************************

	public int delFood(Connection conn) throws ClassNotFoundException, SQLException {

		System.out.println("Veuillez saisir le nom de l'aliment � supprimer : ");
		String nom = input.nextLine();

		String query = ("DELETE FROM public.aliment WHERE nom = '" + nom + "' ;");

		Statement state = conn.createStatement();

		int result = state.executeUpdate(query);
		return result;
		
	}
}

