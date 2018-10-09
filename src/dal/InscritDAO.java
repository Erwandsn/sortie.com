package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Inscrit;
import bo.Participant;
import bo.Sortie;

public interface InscritDAO {
	public Inscrit createInscrit(Inscrit unInscrit) throws SQLException;

	public ArrayList<Inscrit> getAll() throws SQLException;
	
	public ArrayList<Inscrit> searchByNomInscrit(String nomInscrit) throws SQLException;
	
	public Inscrit searchInscrit(String nomInscrit) throws SQLException;
	
	public Inscrit searchInscrit(int id) throws SQLException;
	
	public Boolean deleteOne(Sortie sortie,Participant participant) throws SQLException;
	
	public Inscrit updateInscrit(Inscrit unInscrit) throws SQLException;	
	
	public Inscrit ajoutInscrit(String nomInscrit, String codePostal) throws SQLException;	
}
