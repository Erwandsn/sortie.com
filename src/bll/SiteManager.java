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
		try {
			getSiteDao().createSite(unSite);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return createdSite;
	}
}
