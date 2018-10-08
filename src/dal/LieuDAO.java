package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Lieu;

public interface LieuDAO {
	public Lieu createLieu(Lieu unLieu) throws SQLException;

	public ArrayList<Lieu> getAll() throws SQLException;
	
	public ArrayList<Lieu> searchByNomLieu(String nomLieu) throws SQLException;
	
	public Lieu searchLieu(String nomLieu) throws SQLException;
	
	public Boolean deleteOneById(Lieu unLieu) throws SQLException;
	
	public Lieu updateLieu(Lieu unLieu) throws SQLException;	
	
	public Lieu ajoutLieu(String nomLieu, String codePostal) throws SQLException;	
}
