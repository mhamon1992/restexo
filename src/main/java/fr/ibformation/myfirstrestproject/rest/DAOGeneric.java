package fr.ibformation.myfirstrestproject.rest;


import java.util.List;

public interface DAOGeneric<T> {
	public void create(T c );
	
	public List<T> readByName(String name);
	
	public void update(T c);
	
	public void deleteByName(T c);
	
	public List<T> getAll();
	
	public int getIdByClasse(T c);
	
	public void deleteById(int id);
	
	
}