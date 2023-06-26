package EsingleThreadsModel;

public class ESingleThreadsDAO {
	public void startJDBC(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getConnectionUrl() {
		return "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ESingleThreads;user=javauser;password=javauser";
	}
}
