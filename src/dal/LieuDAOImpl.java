package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bo.Lieu;
import bo.Lieu;

public class LieuDAOImpl implements LieuDAO{
	private final String INSERTONE = "INSERT INTO LIEUX(nom_lieu) VALUES(?)";
	private final String INSERTVILLE = "INSERT INTO LIEUX(nom_lieu,rue,latitude,longitude,villes_no_ville) VALUES(?,?,?,?,?)";
	private final String GETALL ="SELECT no_lieu,nom_lieu,rue,latitude,longitude FROM LIEUX";
	private final String DELETEONEBYID = "DELETE FROM LIEUX WHERE no_lieu=?;";
	private final String UPDATESITE = "UPDATE LIEUX SET nom_lieu=? where no_lieu=?";


	@Override
	public Lieu createLieu(Lieu unLieu) throws SQLException {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTVILLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, unLieu.getNom());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				unLieu.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unLieu;
	}

	@Override
	public ArrayList<Lieu> getAll() throws SQLException {
		ArrayList<Lieu> listeLieus = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETALL, PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeLieus.add(mapLieu(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeLieus;
	}


	private Lieu mapLieu(ResultSet rs) {
		Lieu unLieu = new Lieu();
		try {
			unLieu.setId(rs.getInt(1));
			unLieu.setNom(rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unLieu;
	}

	@Override
	public ArrayList<Lieu> searchByNomLieu(String nomLieu) throws SQLException {
		String GETSEARCH ="SELECT no_ville,nom_ville,code_postal FROM VILLES where nom_ville like '"+nomLieu+"%';";
		ArrayList<Lieu> listeLieus = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomLieu + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				listeLieus.add(mapLieu(rs));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return listeLieus;
	}

	@Override
	public Lieu searchLieu(String nomLieu) throws SQLException {
		String GETSEARCH ="SELECT no_ville,nom_ville,code_postal FROM VILLES where nom_ville like '"+nomLieu+"%';";
		Lieu ville = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(GETSEARCH);
//			pstmt.setString(1, "'" + nomLieu + "'");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				ville= mapLieu(rs);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return ville;
	}
	
	@Override
	public Boolean deleteOneById(Lieu unLieu) throws SQLException {

		Boolean state = false;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETEONEBYID);
			pstmt.setInt(1, unLieu.getId());
			pstmt.executeUpdate();
			state = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return state;

	}

	@Override
	public Lieu updateLieu(Lieu unLieu) throws SQLException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATESITE);
			pstmt.setString(1, unLieu.getNom());
			pstmt.setInt(2, unLieu.getId());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unLieu;
	}

	@Override
	public Lieu ajoutLieu(String nomLieu, String codePostal) throws SQLException {
		// TODO Auto-generated method stub
		Lieu unLieu = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERTVILLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,nomLieu);
			pstmt.setString(2,codePostal);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				unLieu.setId(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new SQLException();
		}
		return unLieu;
	}
}
