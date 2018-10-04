package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;

public interface VilleDAO {
	public Ville createVille(Ville unVille) throws SQLException;

	public ArrayList<Ville> getAll() throws SQLException;
	
	public ArrayList<Ville> searchByNomVille(String nomVille) throws SQLException;
	
	public Ville searchVille(String nomVille) throws SQLException;
	
	public Boolean deleteOneById(Ville unVille) throws SQLException;
	
	public Ville updateVille(Ville unVille) throws SQLException;	
	
	public Ville ajoutVille(String nomVille, String codePostal) throws SQLException;	
}
