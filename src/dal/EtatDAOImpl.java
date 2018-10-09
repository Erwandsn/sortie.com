package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Etat;

public class EtatDAOImpl implements EtatDAO {
	
	private final String GETONEBYID ="SELECT * FROM Etats WHERE no_etat=?";
	
	@Override
	public Etat getEtatById(Etat unEtat) throws SQLException {
		Etat etat = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETONEBYID, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, unEtat.getId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				etat = map(rs);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return etat;
	}
		
		private Etat map(ResultSet rs) throws SQLException {
			Etat etat = new Etat();
			etat.setId(rs.getInt(1));
			etat.setLibelle(rs.getString(2));
			return etat;
		}
	
}
