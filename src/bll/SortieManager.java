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
	
	public Sortie getOneById(Sortie uneSortie) {
		Sortie laSortie = null;
		try {
			laSortie =getSortieDao().getOneById(uneSortie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sortie : "+laSortie);
		return laSortie;
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
	
	public ArrayList<Sortie> getSearch(String site,String recherche,String organisateur,String inscrit,String pasInscrit,
			String sortiePassee,String debut,String fin) {
		ArrayList<Sortie> listeSortie = new ArrayList<>();
		SortieDAO dao = getSortieDao();
		try {
			listeSortie = dao.search(site,recherche,organisateur,inscrit,pasInscrit,sortiePassee,debut,fin);
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
	
	public Boolean cancelSortie(Sortie uneSortie) {
		Boolean state = null;
		try {
			state = getSortieDao().annulerSortie(uneSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
	public Boolean modifierSortie(Sortie uneSortie) {
		Boolean state = null;
		try {
			state = getSortieDao().modifierSortie(uneSortie);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return state;
	}
}
