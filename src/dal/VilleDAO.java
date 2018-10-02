package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;

public interface VilleDAO {
	public ArrayList<Ville> getAll() throws SQLException;
}
