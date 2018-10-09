package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Etat;
import bo.Etat;
import dal.DaoFactory;
import dal.EtatDAO;

public class EtatManager {
	
	private EtatDAO EtatDao;
	
	public EtatManager() {
		this.EtatDao = DaoFactory.getEtatDao();
	}
	
	private EtatDAO getEtatDao() {
		return this.EtatDao;
	}
	
	public ArrayList<Etat> getAll() {
		ArrayList<Etat> listeEtat = new ArrayList<>();
		EtatDAO dao = getEtatDao();
		try {
			listeEtat = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeEtat;
	}
	
	public ArrayList<Etat> getSearchByNomEtat(String nomEtat) {
		ArrayList<Etat> listeEtat = new ArrayList<>();
		EtatDAO dao = getEtatDao();
		try {
			listeEtat = dao.searchByNomEtat(nomEtat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeEtat;
	}
	public Etat createEtat(Etat unEtat) {
		Etat createdEtat = new Etat();
		try {
			getEtatDao().createEtat(unEtat);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return createdEtat;
	}
	
	public Etat searchEtat(String unEtat) {
		Etat createdEtat = new Etat();
		try {
			createdEtat = getEtatDao().searchEtat(unEtat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdEtat;
	}
	
	public Etat searchEtat(int id) {
		Etat createdEtat = new Etat();
		try {
			createdEtat = getEtatDao().searchEtat(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdEtat;
	}
	
	public Boolean deleteEtat(Etat unEtat) {
		Boolean state = null;
		try {
			 state = getEtatDao().deleteOneById(unEtat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Etat updateEtat(Etat unEtat) {
		try {
			unEtat.setLibelle(getEtatDao().updateEtat(unEtat).getLibelle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unEtat;
	}
}
