import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
	
	public void Grouped_Aggregated_Value() {
		String nev="", x="\t";
		int db=0;
		Connection conn = Connect();
		String sqlp = "SELECT Tulajdonos.nev, COUNT(Auto.rendszam) AS auto_darabszam "
				+ "FROM Auto "
				+ "LEFT JOIN Tulajdonos ON Auto.tulaj = Tulajdonos.ekod "
				+ "GROUP BY Tulajdonos.nev";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				nev = rs.getString("nev");
				db = rs.getInt("auto_darabszam");
				SM(nev+x+db);
			}
			rs.close();
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}	
	
	public void Listby(String table, String row, String order) {
		String rend="", tip="", szin="", x="\t";
		int kor=0, ar=0, tulaj=0;
		Connection conn = Connect();
		String sqlp = "SELECT * FROM "+table+" ORDER BY "+row+" "+order;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("szin");
				kor = rs.getInt("kor");
				ar = rs.getInt("ar");
				tulaj = rs.getInt("tulaj");
				SM(rend+x+tip+x+szin+x+kor+x+ar);
			}
			rs.close();
			
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}	
	
	public void Sum_and_Avg(String table, String row) {
		Connection conn = Connect();
		String sqlp = "SELECT SUM("+row+") FROM "+table;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			rs.next();
			int sum = rs.getInt(1);
			SM("A tábla "+row+" oszlopának rekordjainak összege:"+sum);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		sqlp = "SELECT AVG("+row+") FROM "+table;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			rs.next();
			int avg = rs.getInt(1);
			SM("A tábla "+row+" oszlopának rekordjainak átlaga:"+avg);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}	
	
	public void CountTable(String table) {
		Connection conn = Connect();
		String sqlp = "SELECT COUNT(*) FROM "+table;
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			rs.next();
			int count = rs.getInt(1);
			SM("A tábla rekordjainak száma:"+count);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}	
	
	public void UpdateDataAuto(String rsz, String ar) {
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
	
	public void UpdateDataTulajdonos(String ekod, String nev) {
		Connection conn = Connect();
		String sqlp = "UPDATE Tulajdonos SET nev = "+nev+" WHERE ekod = '"+ekod+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott ekod nem létezik, nem történt változás");
			else
			SM("A megadott ekódú személy új neve:"+nev);
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
	
	public void InsertAuto(String rsz, String tip, String szin, String kor, String ar, String tulaj) {
		Connection conn = Connect();
		String sqlp = "INSERT INTO Tulajdo Values('"+rsz+"', '"+tip+"', '"+szin+"', "+kor+", "+ar+", '"+tulaj+"')";
		try {
			Statement s =conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void InsertTulajdonos(String ekod, String nev, String varos, String telefon) {
		Connection conn = Connect();
		String sqlp = "INSERT INTO Tulajdonos Values('"+ekod+"', '"+nev+"', '"+varos+"', "+telefon+")";
		try {
			Statement s =conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void ReadAllDataAuto() {
		String rend="", tip="", szin="", x="\t";
		int kor=0, ar=0, tulaj=0;
		String sqlp= "SELECT rendszam, tipus, szin, kor, ar, tulaj FROM Auto";
		Connection conn= Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				rend = rs.getString("rendszam");
				tip = rs.getString("tipus");
				szin = rs.getString("szin");
				kor = rs.getInt("Kor");
				ar = rs.getInt("ar");
				tulaj = rs.getInt("tulaj");
				SM(rend+x+tip+x+szin+x+kor+x+ar+x+tulaj);
			}
			rs.close();
		} catch (SQLException e) {
			SM("ReadAllData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void ReadAllDataTulajdonos() {
		String nev="", varos="", x="\t";
		int telefon=0, ekod=0;
		String sqlp= "SELECT ekod, nev, varos, telefon FROM Tulajdonos";
		Connection conn= Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				ekod = rs.getInt("ekod");
				nev = rs.getString("nev");
				varos = rs.getString("varos");
				telefon = rs.getInt("telefon");
				SM(ekod+x+nev+x+varos+x+telefon);
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