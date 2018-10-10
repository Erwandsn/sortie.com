package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Inscrit;
import bo.Participant;
import bo.Sortie;

public interface InscritDAO {

	public ArrayList<Inscrit> getAll() throws SQLException;
	
	public Boolean deleteOne(Inscrit unInscrit) throws SQLException;
	
	public Inscrit InscritOneUserToOneSortie(Inscrit unInscrit) throws SQLException;	
	
	public Boolean isInscrit(Inscrit unInscrit) throws SQLException;
	
	public int getNbInscrit(Inscrit unInscrit) throws SQLException;
}
