package fr.isika.cda23.projet1.models;

/**
 * La classe Utilisateur est celle qui représente l'utilisateur actuel de
 * l'application. Il peut être administrateur s'il connait le mot de passe admin
 * 
 * @author Trévor
 *
 */
public class Utilisateur {

	/**
	 * isAdmin est un boolean pour déterminer si l'utilisateur actuel est un administrateur
	 */
	public static boolean isAdmin;
	
	/**
	 * KEY est une constante qui renseigne la clé pour s'authentifier en tant qu'utilisateur
	 */
	final static String KEY = "admin";

	public Utilisateur(boolean isAdmin) {
		super();
		Utilisateur.isAdmin = false;
	}
		
	public static void checkPassword(String password) {
		if(KEY.equals(password)) {
			Utilisateur.isAdmin = true;
		}
    }
	
}