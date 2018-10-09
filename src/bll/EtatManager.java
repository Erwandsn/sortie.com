package bll;

import java.sql.SQLException;

import bo.Etat;
import dal.DaoFactory;
import dal.EtatDAO;

public class EtatManager {
	
	private EtatDAO dao;
	
	public EtatManager() {
		this.dao = DaoFactory.getEtatDao();
	}
	
	public EtatDAO getDao() {
		return this.dao;
	}
	
	public Etat getOneById(Etat unEtat) {
		Etat etat = null;
		try {
			etat = getDao().getEtatById(unEtat);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
}
