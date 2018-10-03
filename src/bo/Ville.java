package bo;

public class Ville {
	private int id;
	private String nomVille;
	private String codePostal;
	
	
	public Ville(String nomVille, String codePostal) {
		super();
		this.nomVille = nomVille;
		this.codePostal = codePostal;
	}


	public Ville() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomVille() {
		return nomVille;
	}


	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

   
}
