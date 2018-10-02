package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Site;

public class SiteDAOImpl implements SiteDAO{
	
	private final String INSERTONE = "INSERT INTO site(nom_site) VALUES(?);";

	@Override
	public Site createSite(Site unSite) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTONE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unSite.getNom());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				unSite.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unSite;
	}
	
}
