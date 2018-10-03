package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Ville;
import bo.Ville;
import dal.DaoFactory;
import dal.VilleDAO;

public class VilleManager {
	
	private VilleDAO VilleDao;
	
	public VilleManager() {
		this.VilleDao = DaoFactory.getVilleDao();
	}
	
	private VilleDAO getVilleDao() {
		return this.VilleDao;
	}
	
	public ArrayList<Ville> getAll() {
		ArrayList<Ville> listeVille = new ArrayList<>();
		VilleDAO dao = getVilleDao();
		try {
			listeVille = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVille;
	}
	
	public ArrayList<Ville> getSearchByNomVille(String nomVille) {
		ArrayList<Ville> listeVille = new ArrayList<>();
		VilleDAO dao = getVilleDao();
		try {
			listeVille = dao.searchByNomVille(nomVille);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVille;
	}
	public Ville createVille(Ville unVille) {
		Ville createdVille = new Ville();
		try {
			getVilleDao().createVille(unVille);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdVille;
	}
	
	public Ville searchVille(String unVille) {
		Ville createdVille = new Ville();
		try {
			createdVille = getVilleDao().searchVille(unVille);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdVille;
	}
}
