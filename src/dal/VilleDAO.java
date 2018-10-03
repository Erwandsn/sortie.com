package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;

public interface VilleDAO {
	public Ville createVille(Ville unVille) throws SQLException;

	public ArrayList<Ville> getAll() throws SQLException;
	
	public ArrayList<Ville> searchByNomVille(String nomVille) throws SQLException;
	
	public Ville searchVille(String nomVille) throws SQLException;
}
