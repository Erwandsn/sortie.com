package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Inscrit;
import bo.Participant;
import bo.Sortie;
import bo.Inscrit;
import dal.DaoFactory;
import dal.InscritDAO;

public class InscritManager {
	
	private InscritDAO InscritDao;
	
	public InscritManager() {
		this.InscritDao = DaoFactory.getInscritDao();
	}
	
	private InscritDAO getInscritDao() {
		return this.InscritDao;
	}
	public Boolean isInscrit(Inscrit uneInscription) {
		Boolean state = null;
		try {
			state = getInscritDao().isInscrit(uneInscription);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public Inscrit InscritOneUserToOneSortie(Inscrit uneInscription) {
		Inscrit inscrit = null;
		InscritDAO dao = getInscritDao();
		try {
			inscrit = dao.InscritOneUserToOneSortie(uneInscription);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inscrit;
	}
	
	public Boolean deleteInscrit(Inscrit uneInscription) {
		Boolean state = null;
		try {
			state = getInscritDao().deleteOne(uneInscription);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public int getNbInscrit(Inscrit unInscrit) {
		int nbInscription= 0;
		try {
			nbInscription = getInscritDao().getNbInscrit(unInscrit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbInscription;
		
	}
}
