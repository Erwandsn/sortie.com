package dal;


public class DaoFactory {
	
	public static ParticipantDAOImpl getParticipantDao() {
		return new ParticipantDAOImpl();
	}
	
	public static SiteDAOImpl getSiteDao() {
		return new SiteDAOImpl();
	}
	
	public static VilleDAOImpl getVilleDao() {
		return new VilleDAOImpl();
	}
	
	public static SortieDAOImpl getSortieDao() {
		return new SortieDAOImpl();
	}
	
	public static LieuDAOImpl getLieuDao() {
		return new LieuDAOImpl();
	}
	
	public static EtatDAOImpl getEtatDao() {
		return new EtatDAOImpl();
	}
	
	public static InscritDAOImpl getInscritDao() {
		return new InscritDAOImpl();
	}
}
