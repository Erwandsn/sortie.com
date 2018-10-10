package bo;

import java.sql.Date;
import java.util.ArrayList;

public class Sortie {
    private int id;
    private String nom;
    private Date dateheureDebut;
    private int duree;
    private Date dateLimiteInscription;
    private int nbInscriptionsMax;
    private String infosSortie;
    private Etat etat;
    private Participant organisateur;
    private Lieu lieu;
    private Ville ville;
    private ArrayList<Participant> listeParticipants;

    public Sortie( String nom, Date dateheureDebut, int duree, Date dateLimiteInscription, int nbInscriptionsMax, String infosSortie, Etat etat,Participant organisateur,
    		Lieu lieu,Ville ville) {
        this.nom = nom;
        this.dateheureDebut = dateheureDebut;
        this.duree = duree;
        this.dateLimiteInscription = dateLimiteInscription;
        this.nbInscriptionsMax = nbInscriptionsMax;
        this.infosSortie = infosSortie;
        this.etat = etat;
        this.organisateur = organisateur;
        this.lieu = lieu;
        this.ville = ville;
    }

    public Sortie() {
		super();
	}

	public ArrayList<Participant> getListeParticipants() {
		return listeParticipants;
	}

	public void setListeParticipants(ArrayList<Participant> listeParticipants) {
		this.listeParticipants = listeParticipants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateheureDebut() {
		return dateheureDebut;
	}

	public void setDateheureDebut(Date dateheureDebut) {
		this.dateheureDebut = dateheureDebut;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateLimiteInscription() {
		return dateLimiteInscription;
	}

	public void setDateLimiteInscription(Date dateLimiteInscription) {
		this.dateLimiteInscription = dateLimiteInscription;
	}

	public int getNbInscriptionsMax() {
		return nbInscriptionsMax;
	}

	public void setNbInscriptionsMax(int nbInscriptionsMax) {
		this.nbInscriptionsMax = nbInscriptionsMax;
	}

	public String getInfosSortie() {
		return infosSortie;
	}

	public void setInfosSortie(String infosSortie) {
		this.infosSortie = infosSortie;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Participant getOrganisateur() {
		return organisateur;
	}

	public void setOrganisateur(Participant organisateur) {
		this.organisateur = organisateur;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Sortie [id=" + id + ", nom=" + nom + ", dateheureDebut=" + dateheureDebut + ", duree=" + duree
				+ ", dateLimiteInscription=" + dateLimiteInscription + ", nbInscriptionsMax=" + nbInscriptionsMax
				+ ", infosSortie=" + infosSortie + ", etat=" + etat + ", organisateur=" + organisateur + ", lieu="
				+ lieu + ", ville=" + ville + ", listeParticipants=" + listeParticipants + "]";
	}
}
