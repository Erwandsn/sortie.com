package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Site;

public interface SiteDAO {
	
	public Site createSite(Site unSite) throws SQLException;
	
	public ArrayList<Site> getAll() throws SQLException;
	
	public Boolean deleteOneById(Site unSite) throws SQLException;
	
	public Site updateSite(Site unSite) throws SQLException;
}
