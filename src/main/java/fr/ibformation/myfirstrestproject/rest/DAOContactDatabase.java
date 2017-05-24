package fr.ibformation.myfirstrestproject.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.ibformation.myfirstrestproject.rest.ConnectionDatabase;
import fr.ibformation.myfirstrestproject.rest.DAOGeneric;
import fr.ibformation.myfirstrestproject.rest.Contact;

public class DAOContactDatabase implements DAOGeneric<Contact> {

	private Connection createConnection() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/carnetadresses", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConnectionDatabase.getConnectionDatabase().getConnection();
		

	}

	@Override
	public void create(Contact c) {
		// TODO Auto-generated method stub		
		String request = "INSERT INTO carnetadresses.contact(nomcontact, prenomcontact, numero) values(?,?,?);";
		try {
			PreparedStatement ps = createConnection().prepareStatement(request);
			ps.setString(1, c.getNom());
			ps.setString(2, c.getPrenom());
			ps.setString(3, c.getNumero());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public List<Contact> readByName(String name) {
		List<Contact> contacts = null;
		Contact contact = null;
		try {
			
			String request = "SELECT * FROM carnetadresses.contact where nomcontact = ?";
			PreparedStatement preparedStatement = createConnection().prepareStatement(request);
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			contacts = new ArrayList<Contact>();
			while(rs.next()){
				contact = new Contact(rs.getString("nomcontact"),rs.getString("prenomcontact"), rs.getString("numero"));
				contacts.add(contact);	
			}
			
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
		}
		return contacts;
		
	}

	@Override
	public void update(Contact c) {
		// TODO Auto-generated method stub
		List<Integer> ids = getId(c);
		for(int id: ids){
			try {
				String request = "UPDATE carnetadresses.contact SET nomcontact=(?), prenomcontact=(?), numero=(?) where idcontact = (?);";
				PreparedStatement preparedStatement = createConnection().prepareStatement(request);
				preparedStatement.setString(1, c.getNom());
				preparedStatement.setString(2, c.getPrenom());
				preparedStatement.setString(3, c.getNumero());
				preparedStatement.setInt(4, id);
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Contact> getAll() {
		List<Contact> contacts = null;
		try {
			Statement stmt = createConnection().createStatement();
			String request = "SELECT * FROM carnetadresses.contact";
			ResultSet rs = stmt.executeQuery(request);

			contacts = new ArrayList<Contact>();

			while (rs.next()) {
				Contact v = new Contact(rs.getString("nomcontact"),rs.getString("prenomcontact"), rs.getString("numero"));

				contacts.add(v);
			}
		} catch (SQLException e) {

		}
		
		return contacts;
	}

	@Override
	public void deleteByName(Contact c) {
		// TODO Auto-generated method stub
		List<Integer> ids = getId(c);
		for(int id: ids){
			try {
				String request = "DELETE FROM carnetadresses.contact where idcontact = (?);";
				PreparedStatement preparedStatement = createConnection().prepareStatement(request);
				preparedStatement.setInt(4, id);
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteById(int id) {
		// TODO Auto-generated method stub	
			try {
				String request = "DELETE FROM carnetadresses.contact where idcontact = (?);";
				PreparedStatement preparedStatement = createConnection().prepareStatement(request);
				preparedStatement.setInt(4, id);
				preparedStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public List<Integer> getId(Contact c){
		List<Integer> ids = new ArrayList<>();;
	try {
				String request = "SELECT * FROM carnetadresses.contact where nomcontact = (?) and prenomcontact = (?) and numero = (?)";
				PreparedStatement preparedStatement = createConnection().prepareStatement(request);
				preparedStatement.setString(1, c.getNom());
				preparedStatement.setString(2, c.getPrenom());
				preparedStatement.setString(3, c.getNumero());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt("idcontact"));
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
	return ids;
	}
	
	public int getIdByClasse(Contact c){
		int id = 0;
		String request = "SELECT * FROM carnetadresses.contact where nomcontact = (?) and prenomcontact = (?) and numero = (?)";
		try {
			PreparedStatement preparedStatement = createConnection().prepareStatement(request);
			preparedStatement.setString(1, c.getNom());
			preparedStatement.setString(2, c.getPrenom());
			preparedStatement.setString(3, c.getNumero());
			ResultSet rs = preparedStatement.executeQuery();
			id = rs.getInt("idcontact");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}
}
