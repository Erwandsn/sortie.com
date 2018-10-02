package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;
import dal.DaoFactory;
import dal.VilleDAO;

public class VilleManager {
	
	private VilleDAO VilleDao;
	
	public VilleManager() {
		this.VilleDao = DaoFactory.getVilleDao();
	}
	
	private VilleDAO getDao() {
		return this.VilleDao;
	}
	
	public ArrayList<Ville> getAll() {
		ArrayList<Ville> listeVille = new ArrayList<>();
		VilleDAO dao = getDao();
		try {
			listeVille = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVille;
	}
	
	public void insertOne() {
		
	}
}
