package dal;

import java.sql.SQLException;

import bo.Etat;

public interface EtatDAO {
	public Etat getEtatById(Etat unEtat) throws SQLException; 
}
