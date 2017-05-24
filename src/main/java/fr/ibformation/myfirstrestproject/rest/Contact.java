package fr.ibformation.myfirstrestproject.rest;
//TODO: RETIRER TOUS LES ELEMENTS QUI DEVRAIENT ETRE DANS INTERFACETEXTE

public class Contact {
	private String nom;
	private String prenom;
	private String numero;
	
	public Contact(String nom, String prenom, String numero) {
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
