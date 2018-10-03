package bo;

public class Participant {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private Boolean admin;
    private Boolean actif;
    private String pseudo;
    private String motDePasse;
    private Ville ville;
    private Site site;
    
	public Participant(String nom, String prenom, String telephone, String mail, Boolean admin, Boolean actif,
			String pseudo, String motDePasse, Ville ville,Site site) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.admin = admin;
		this.actif = actif;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.ville = ville;
		this.site = site;
	}
	
	

	public Participant() {
		super();
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}



	public Site getSite() {
		return site;
	}



	public void setSite(Site site) {
		this.site = site;
	}



	
    
	
	
	

}
