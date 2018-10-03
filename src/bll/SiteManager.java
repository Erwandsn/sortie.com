package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Site;
import dal.DaoFactory;
import dal.SiteDAO;

public class SiteManager {
	
	private SiteDAO siteDao;
	
	public SiteManager() {
		this.siteDao = DaoFactory.getSiteDao();
	}
	
	public SiteDAO getSiteDao() {
		return this.siteDao;
	}
	
	public ArrayList<Site> getAll(){
		ArrayList<Site> listSite = new ArrayList<>();
		try {
			listSite = getSiteDao().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listSite;
	}
	
	public Site createSite(Site unSite) {
		Site createdSite = new Site();
		createdSite.setNom(unSite.getNom());
		try {
			createdSite.setId(getSiteDao().createSite(unSite).getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return createdSite;
	}
	
	public Boolean deleteSite(Site unSite) {
		Boolean state = null;
		try {
			 state = getSiteDao().deleteOneById(unSite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
	}
	
	public Site updateSite(Site unSite) {
		try {
			unSite.setNom(getSiteDao().updateSite(unSite).getNom());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unSite;
	}
}
