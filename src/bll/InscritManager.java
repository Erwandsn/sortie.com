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
	
	public ArrayList<Inscrit> getAll() {
		ArrayList<Inscrit> listeInscrit = new ArrayList<>();
		InscritDAO dao = getInscritDao();
		try {
			listeInscrit = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeInscrit;
	}
	
	public ArrayList<Inscrit> getSearchByNomInscrit(String nomInscrit) {
		ArrayList<Inscrit> listeInscrit = new ArrayList<>();
		InscritDAO dao = getInscritDao();
		try {
			listeInscrit = dao.searchByNomInscrit(nomInscrit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeInscrit;
	}
	public Inscrit createInscrit(Inscrit unInscrit) {
		Inscrit createdInscrit = new Inscrit();
		try {
			getInscritDao().createInscrit(unInscrit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdInscrit;
	}
	
	public Inscrit searchInscrit(String unInscrit) {
		Inscrit createdInscrit = new Inscrit();
		try {
			createdInscrit = getInscritDao().searchInscrit(unInscrit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdInscrit;
	}
	
	public Inscrit searchInscrit(int id) {
		Inscrit createdInscrit = new Inscrit();
		try {
			createdInscrit = getInscritDao().searchInscrit(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdInscrit;
	}
	
	public Boolean deleteInscrit(Sortie sortie,Participant participant) {
		Boolean state = null;
		try {
			 state = getInscritDao().deleteOne(sortie,participant);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Inscrit updateInscrit(Inscrit unInscrit) {
		try {
			unInscrit.setParticipant(getInscritDao().updateInscrit(unInscrit).getParticipant());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unInscrit;
	}
}
