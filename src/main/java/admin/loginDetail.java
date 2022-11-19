package admin;

public class loginDetail {
	private static String user;
	private static String adName;
	private static String adPass;
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		loginDetail.user = user;
	}
	public static String getAdName() {
		return adName;
	}
	public static void setAdName(String adName) {
		loginDetail.adName = adName;
	}
	public static String getAdPass() {
		return adPass;
	}
	public static void setAdPass(String adPass) {
		loginDetail.adPass = adPass;
	}
}
