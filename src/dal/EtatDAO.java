package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Etat;

public interface EtatDAO {
	public Etat createEtat(Etat unEtat) throws SQLException;

	public ArrayList<Etat> getAll() throws SQLException;
	
	public ArrayList<Etat> searchByNomEtat(String nomEtat) throws SQLException;
	
	public Etat searchEtat(String nomEtat) throws SQLException;
	
	public Etat searchEtat(int id) throws SQLException;
	
	public Boolean deleteOneById(Etat unEtat) throws SQLException;
	
	public Etat updateEtat(Etat unEtat) throws SQLException;	
	
	public Etat ajoutEtat(String nomEtat) throws SQLException;	
}
