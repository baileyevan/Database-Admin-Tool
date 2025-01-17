package src;

public class DatabaseInformation {
	
	static String Driver = "com.mysql.cj.jdbc.Driver";

	private static String DBURL = "jdbc:mysql://localhost:3306/sdfinalproject";;
	private static String Username = "admin";
	private static String Password = "admin";
	
	public static String getUsername() {
		return Username;
	}
	public static void setUsername(String username) {
		Username = username;
	}
	public static String getDBURL() {
		return DBURL;
	}
	public static void setDBURL(String dBURL) {
		DBURL = dBURL;
	}
	public static String getPassword() {
		return Password;
	}
	public static void setPassword(String password) {
		Password = password;
	}
	
}
