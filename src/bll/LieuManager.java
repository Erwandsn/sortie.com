package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Lieu;
import dal.DaoFactory;
import dal.LieuDAO;

public class LieuManager {
	
	private LieuDAO LieuDao;
	
	public LieuManager() {
		this.LieuDao = DaoFactory.getLieuDao();
	}
	
	private LieuDAO getLieuDao() {
		return this.LieuDao;
	}
	
	public ArrayList<Lieu> getAll() {
		ArrayList<Lieu> listeLieu = new ArrayList<>();
		LieuDAO dao = getLieuDao();
		try {
			listeLieu = dao.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeLieu;
	}
	
	public ArrayList<Lieu> getSearchByNomLieu(String nomLieu) {
		ArrayList<Lieu> listeLieu = new ArrayList<>();
		LieuDAO dao = getLieuDao();
		try {
			listeLieu = dao.searchByNomLieu(nomLieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeLieu;
	}
	public Lieu createLieu(Lieu unLieu) {
		Lieu createdLieu = new Lieu();
		try {
			getLieuDao().createLieu(unLieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdLieu;
	}
	
	public Lieu searchLieu(String unLieu) {
		Lieu createdLieu = new Lieu();
		try {
			createdLieu = getLieuDao().searchLieu(unLieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdLieu;
	}
	
	public Lieu searchLieu(int id) {
		Lieu createdLieu = new Lieu();
		try {
			createdLieu = getLieuDao().searchLieu(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdLieu;
	}
	
	public Boolean deleteLieu(Lieu unLieu) {
		Boolean state = null;
		try {
			 state = getLieuDao().deleteOneById(unLieu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Lieu updateLieu(Lieu unLieu) {
		try {
			unLieu.setNom(getLieuDao().updateLieu(unLieu).getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unLieu;
	}
	
	public Lieu getOneById(Lieu unLieu) {
		Lieu leLieu = null;
		try {
			leLieu = getLieuDao().getOneById(unLieu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leLieu;
	}
}
