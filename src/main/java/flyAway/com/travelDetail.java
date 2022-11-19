package flyAway.com;

public class travelDetail {
	private static String fno;
	private static String comp;
	private static String sou;
	private static String dest;
	private static String depDate;
	private static String arrDate;
	private static String dep;
	private static String arr;
	private static int price;
	private static int npas;
	private static String cName;
	private static String uName;
	private static int j;
	private static int id;
	private static double fare;
	private static int seatsLeft;
		
	public void travelDetail(String fno, String comp, String sou, String dest, String depDate, String arrDate, String dep, String arr,
			int price, int npas) {
		this.fno = fno;
		this.comp = comp;
		this.sou = sou;
		this.dest = dest;
		this.depDate = depDate;
		this.arrDate = arrDate;
		this.dep = dep;
		this.arr = arr;
		this.price = price;
		this.npas = npas;
	}
	public String getFno() {
		return fno;
	}
	public void setFno(String fno) {
		this.fno = fno;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getSou() {
		return sou;
	}
	public void setSou(String sou) {
		this.sou = sou;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public static String getDepDate() {
		return depDate;
	}
	public static void setDepDate(String depDate) {
		travelDetail.depDate = depDate;
	}
	public static String getArrDate() {
		return arrDate;
	}
	public static void setArrDate(String arrDate) {
		travelDetail.arrDate = arrDate;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNpas() {
		return npas;
	}
	public void setNpas(int npas) {
		this.npas = npas;
	}	
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public static String getuName() {
		return uName;
	}
	public static void setuName(String uName) {
		travelDetail.uName = uName;
	}
	public static int getJ() {
		return j;
	}
	public static void setJ(int j) {
		travelDetail.j = j;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		travelDetail.id = id;
	}
	public static double getFare() {
		return fare;
	}
	public static void setFare(double fare) {
		travelDetail.fare = fare;
	}
	public static int getSeatsLeft() {
		return seatsLeft;
	}
	public static void setSeatsLeft(int seatsLeft) {
		travelDetail.seatsLeft = seatsLeft;
	}
	
}
