import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
	
	public void UpdateData(String rsz, String ar) {
		Connection conn = Connect();
		String sqlp = "UPDATE Auto SET ar = "+ar+" WHERE rendszam = '"+rsz+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott rendszámú autó nem létezik, nem történt változás");
			else
			SM("A megadott rendszámú autó új ára:"+ar);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void DeleteData(String rsz) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM Auto WHERE rendszam = '"+rsz+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott rendszámú autó nem létezik, nem törölhető");
			else
			SM("Törlődött a(z) "+rsz+" rendszámú autó");
		} catch (SQLException e) {
			SM("JDBC DeleteData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void Insert(String rsz, String tip, String szin, String kor, String ar) {
		Connection conn = Connect();
		String sqlp = "INSERT INTO Auto Values('"+rsz+"', '"+tip+"', '"+szin+"', "+kor+", "+ar+")";
		try {
			Statement s =conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void ReadAllData() {
		String rend="", tip="", szin="", x="\t";
		int kor=0, ar=0;
		String sqlp= "SELECT rendszam, tipus, szin, kor, ar FROM Auto";
		Connection conn= Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("szin");
				kor = rs.getInt("kor");
				ar = rs.getInt("ar");
				SM(rend+x+tip+x+szin+x+kor+x+ar);
			}
			rs.close();
		} catch (SQLException e) {
			SM("ReadAllData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void CommandExec(String command) {
		Connection conn = Connect();
		String sqlp = command;
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			SM("Command OK");
		} catch (SQLException e) {
			SM("Command: "+sqlp);
			SM("CommandExec: "+e.getMessage());
		}
		Disconnect(conn);
	}
	public Connection Connect() {
		Connection conn = null;
		String url = "jdbc:sqlite:C:/sqlite3/autodb";
		try {
			conn = DriverManager.getConnection(url);
			SM("Sikeres kapcsolódás");
			return conn;
		} catch(Exception ex) {
			SM(ex.getMessage());
			return conn;
		}
	}
	
	public void Disconnect(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				SM("Sikeres lekapcsolódás");
			} catch(Exception ex) {
				SM(ex.getMessage());
			}
		}
	}
	
	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver regisztrálás");
		} catch(Exception ex) {
			SM(ex.getMessage());
		}
		}

	public void SM(String s) {
		System.out.println(s+"\n");
	}
}