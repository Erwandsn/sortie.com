package bll;

import java.sql.SQLException;

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
