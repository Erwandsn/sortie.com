package dal;

import java.sql.SQLException;

import bo.Site;

public interface SiteDAO {
	
	public Site createSite(Site unSite) throws SQLException;
		
}
