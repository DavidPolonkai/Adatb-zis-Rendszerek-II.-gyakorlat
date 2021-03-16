package elso;

public class Car {
	private String rsz;
	private String tipus;
	private String szin;
	private int evjarat;
	private int ar;
	
	
	
	public Car() {
		super();
	}
	public Car(String rsz, String tipus, String szin, int evjarat, int ar) {
		super();
		this.rsz = rsz;
		this.tipus = tipus;
		this.szin = szin;
		this.evjarat = evjarat;
		this.ar = ar;
	}
	
	public Car(String rsz,String tipus,String szin) {
		super();
		this.rsz=rsz;
		this.tipus=tipus;
		this.szin=szin;
		this.evjarat = 0;
		this.ar = 0;
	}
	
	@Override
	public String toString() {
		if (ar==0 && evjarat==0) {
			return "rsz=" + rsz + " tipus=" + tipus + " szin=" + szin + "\n";
		}
		return "rsz=" + rsz + " tipus=" + tipus + " szin=" + szin + " evjarat=" + evjarat + " ar=" + ar + "\n";
	}
	
	
	
	public String getRsz() {
		return rsz;
	}
	public void setRsz(String rsz) {
		this.rsz = rsz;
	}
	public String getTipus() {
		return tipus;
	}
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	public String getSzin() {
		return szin;
	}
	public void setSzin(String szin) {
		this.szin = szin;
	}
	public int getEvjarat() {
		return evjarat;
	}
	public void setEvjarat(int evjarat) {
		this.evjarat = evjarat;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
}
