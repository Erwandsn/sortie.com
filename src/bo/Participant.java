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
    private String motDePase;
    private Ville ville;
    
	public Participant(String nom, String prenom, String telephone, String mail, Boolean admin, Boolean actif,
			String pseudo, String motDePase, Ville ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.admin = admin;
		this.actif = actif;
		this.pseudo = pseudo;
		this.motDePase = motDePase;
		this.ville = ville;
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

	public String getMotDePase() {
		return motDePase;
	}

	public void setMotDePase(String motDePase) {
		this.motDePase = motDePase;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
    
	
	
	

}
