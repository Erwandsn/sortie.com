package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Sortie;
import bo.Sortie;
import dal.DaoFactory;
import dal.SortieDAO;

public class SortieManager {
	
	private SortieDAO SortieDao;
	
	public SortieManager() {
		this.SortieDao = DaoFactory.getSortieDao();
	}
	
	private SortieDAO getSortieDao() {
		return this.SortieDao;
	}
	
	public ArrayList<Sortie> getAll() {
		ArrayList<Sortie> listeSortie = new ArrayList<>();
		SortieDAO dao = getSortieDao();
		try {
			listeSortie = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeSortie;
	}
	
	public ArrayList<Sortie> getSearchByNomSortie(String nomSortie) {
		ArrayList<Sortie> listeSortie = new ArrayList<>();
		SortieDAO dao = getSortieDao();
		try {
			listeSortie = dao.searchByNomSortie(nomSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeSortie;
	}
	public Sortie createSortie(Sortie unSortie) {
		Sortie createdSortie = new Sortie();
		try {
			getSortieDao().createSortie(unSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdSortie;
	}
	
	public Sortie searchSortie(String unSortie) {
		Sortie createdSortie = new Sortie();
		try {
			createdSortie = getSortieDao().searchSortie(unSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdSortie;
	}
	
	public Sortie searchSortie(int unSortie) {
		Sortie createdSortie = new Sortie();
		try {
			createdSortie = getSortieDao().searchSortie(unSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdSortie;
	}
	
	public Boolean deleteSortie(Sortie unSortie) {
		Boolean state = null;
		try {
			 state = getSortieDao().deleteOneById(unSortie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Sortie updateSortie(Sortie unSortie) {
		try {
			unSortie.setNom(getSortieDao().updateSortie(unSortie).getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unSortie;
	}
}
