package bo;

import java.sql.Date;

public class Inscrit {
    private Participant participant;
    private Sortie sortie;
    private Date dateInscription;
    
	public Inscrit(Participant participant, Sortie sortie) {
		super();
		this.participant = participant;
		this.sortie = sortie;
	}

	public Inscrit() {
		super();
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Sortie getSortie() {
		return sortie;
	}

	public void setSortie(Sortie sortie) {
		this.sortie = sortie;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
}
