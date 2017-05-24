package fr.ibformation.myfirstrestproject.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

//package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/hello")
public class MyFirstRestService {
	
	@GET
	public Response getMsg() {
		String output = "hello sans path";

		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("/un")
	public Response getMsgUn() {
		String output = "hello";

		return Response.status(200).entity(output).build();

	}
	@GET
	@Path("/contacts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getContacts(){
		//String res="";
		//int i = 0;
		//Map<String,Contact> listeContacts = new Map<String,Contact>();
		DAOGeneric<Contact> dao = new DAOContactDatabase();
		List<Contact> listContacts = dao.getAll();
		/*for(Contact c:listContacts){
		 	listeContacts.put("Contact"+i,c);
		 	i++;
			res += "nom:"+c.getNom()+", prenom:"+c.getPrenom()+",numero:"+c.getNumero();
			res += "\n";
		}*/
		return listContacts;
	}
	
	@POST
	@Path("/creerContact")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createContact(Contact c){
		
			DAOGeneric<Contact> dao = new DAOContactDatabase();
			System.out.println(c.getNom());
			dao.create(c);	
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/nom={nom}")
	public List<Contact> getContactByName(@PathParam("nom")String nom){
		//String res = "";
		System.out.println(nom);
		DAOGeneric<Contact> dao = new DAOContactDatabase();
		List<Contact> contacts = dao.readByName(nom);
		/*for(Contact c:contacts){
			res += "nom:"+c.getNom()+", prenom:"+c.getPrenom()+",numero:"+c.getNumero();
			res += "\n";
		}
		return res;*/
		return contacts;
	}
	
	@PUT
	@Path("/{nom}{prenom}{numero}")
	public void updateContact(@PathParam("nom")String nom,@PathParam("prenom")String prenom,@PathParam("numero")String numero){
		
		DAOGeneric<Contact> dao = new DAOContactDatabase();
		List<Contact> contacts = dao.readByName(nom);
		for(Contact c:contacts){
			if((c.getNom().equals(nom) && c.getPrenom().equals(prenom))||(c.getNom().equals(nom) && c.getNumero().equals(numero)) ){
				dao.update(c);
			}
		}
	}
	
	@DELETE
	@Path("{nom}/{prenom}/{numero}")
	public void deleteContact(@PathParam("nom")String nom,@PathParam("prenom")String prenom,@PathParam("numero")String numero){
		System.out.println(nom+" "+prenom+" "+numero);
		DAOGeneric<Contact> dao = new DAOContactDatabase();
		Contact contact = new Contact(nom, prenom,numero);
		int id = dao.getIdByClasse(contact);
		dao.deleteById(id);
		/*List<Contact> contacts = dao.readByName(nom);
		for(Contact c:contacts){
			if(c.getNom().equals(nom) && c.getPrenom().equals(prenom) && c.getNumero().equals(numero)){
				dao.deleteByName(c);
			}
			
		}*/
	}
	
	

	
}