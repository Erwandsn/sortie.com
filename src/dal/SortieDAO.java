package dal;

import java.sql.SQLException;
import java.util.ArrayList;

import bo.Participant;
import bo.Sortie;

public interface SortieDAO {
	public Sortie createSortie(Sortie unSortie) throws SQLException;

	public ArrayList<Sortie> getAll() throws SQLException;
	
	public ArrayList<Sortie> searchByNomSortie(String nomSortie) throws SQLException;
	
	public Sortie searchSortie(String nomSortie) throws SQLException;
	
	public Sortie searchSortie(int nomSortie) throws SQLException;
	
	public Boolean deleteOneById(Sortie unSortie) throws SQLException;
	
	public Sortie updateSortie(Sortie unSortie) throws SQLException;	
	
	public Sortie ajoutSortie(String nomSortie, String codePostal) throws SQLException;	
	
	public Sortie getOneById(Sortie uneSortie) throws SQLException;
	
	public ArrayList<Participant> getParticipantOfOneSortie(Sortie uneSortie) throws SQLException;
}
