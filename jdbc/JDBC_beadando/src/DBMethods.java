import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.IOException;

public class DBMethods {
	

	public void WriteAllDataSzerzo() {
	    String nev = "", szuletes = "", x = "\t|\t";
	    int id = 0, kor = 0, konyvek = 0;
	    String sqlp = "SELECT id, nev, szuletes, kor, konyvek FROM Szerzo";
	    Connection conn = Connect();
	    FileWriter writer = null;
	    
	    try {
	        writer = new FileWriter("szerzo.txt");
	        
	        Statement s = conn.createStatement();
	        ResultSet rs = s.executeQuery(sqlp);
	        
	        writer.write("ID\t|\tNév\t|\tSzületés\t|\tKor\t|\tKönyvek\n\n");

	        while (rs.next()) {
	            id = rs.getInt("id");
	            nev = rs.getString("nev");
	            szuletes = rs.getString("szuletes");
	            kor = rs.getInt("kor");
	            konyvek = rs.getInt("konyvek");
	            
	            writer.write(id+x+nev+x+szuletes+x+kor+x+konyvek+"\n");
	        }
	        
	        rs.close();
	    } catch (SQLException e) {
	        SM("WriteAllDataSzerzo: " + e.getMessage());
	    } catch (IOException e) {
	        SM("Hiba az írásban: " + e.getMessage());
	    } finally {
	        try {
	            if (writer != null) {
	                writer.close();
	            }
	        } catch (IOException e) {
	            SM("Hiba  a bezárásban: " + e.getMessage());
	        }
	        Disconnect(conn);
	    }
	}

	public void WriteAllDataKonyv() {
	    String cim = "", megjelenes = "", nyelv= "", x = "\t|\t";
	    int id = 0, szerzo = 0;
	    String sqlp = "SELECT id, cim, megjelenes, szerzo, nyelv FROM Konyv";
	    Connection conn = Connect();
	    FileWriter writer = null;
	    
	    try {
	        writer = new FileWriter("konyv.txt");
	        
	        Statement s = conn.createStatement();
	        ResultSet rs = s.executeQuery(sqlp);
	        
	        writer.write("ID\t|\tCím\t|\tMegjelenés\t|\tSzerzőID\t|\tNyelv\n\n");

	        while (rs.next()) {
	            id = rs.getInt("id");
	            cim = rs.getString("cim");
	            megjelenes = rs.getString("megjelenes");
	            szerzo = rs.getInt("szerzo");
	            nyelv = rs.getString("nyelv");
	            
	            writer.write(id+x+cim+x+megjelenes+x+szerzo+x+nyelv+"\n");
	        }
	        
	        rs.close();
	    } catch (SQLException e) {
	        SM("WriteAllDataKonyv: " + e.getMessage());
	    } catch (IOException e) {
	        SM("Hiba az írásban: " + e.getMessage());
	    } finally {
	        try {
	            if (writer != null) {
	                writer.close();
	            }
	        } catch (IOException e) {
	            SM("Hiba  a bezárásban: " + e.getMessage());
	        }
	        Disconnect(conn);
	    }
	}
	
	public void DeleteDataSzerzo(String id) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM Szerzo WHERE id = '"+id+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott id-jű szerző nem létezik, nem törölhető");
			else
			SM("Törlődött a(z) "+id+" id-jű szerző");
		} catch (SQLException e) {
			SM("JDBC DeleteData: "+e.getMessage());
		}
		Disconnect(conn);
}
	
	public void DeleteDataKonyv(String id) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM Konyv WHERE id = '"+id+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott id-jű könyv nem létezik, nem törölhető");
			else
			SM("Törlődött a(z) "+id+" id-jű konyv");
		} catch (SQLException e) {
			SM("JDBC DeleteData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void InsertWithPS(Konyv[] konyv) {
		Connection conn = Connect();
		int db = 0;
		String sqlp = "Insert INTO Konyv Values(?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlp);
			for (int i = 0; i<konyv.length; i++) {
				ps.setInt(1, konyv[i].getId());
				ps.setString(2, konyv[i].getCim());
				ps.setString(3, konyv[i].getMegjelenes());
				ps.setInt(4, konyv[i].getSzerzo());
				ps.setString(5, konyv[i].getNyelv());
				ps.execute();
				db++;
			}
			SM(db+" darab rekord beszúrva");
		} catch (SQLException e) {SM(e.getMessage());}
		Disconnect(conn);
	}
	
	public void DeleteWithPS(Konyv[] konyv) {
		Connection conn = Connect();
		String sqlp = "DELETE FROM Konyv WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sqlp);
			for (int i = 0; i<konyv.length; i++) {
				ps.setInt(1, konyv[i].getId());
				ps.execute();
				}
			SM("rekord törölve");
		} catch (SQLException e) {SM(e.getMessage());}
		Disconnect(conn);
	}
	
	public void UpdateDataKonyv(String id, String nyelv) {
		Connection conn = Connect();
		String sqlp = "UPDATE Konyv SET nyelv = '"+nyelv+"' WHERE id = '"+id+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott id-jű könyv nem létezik, nem történt változás");
			else
			SM("A megadott id-jű könyv új nyelve:"+nyelv);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void UpdateDataSzerzo(String id, String nev) {
		Connection conn = Connect();
		String sqlp = "UPDATE Szerzo SET nev = '"+nev+"' WHERE id = '"+id+"'";
		try {
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if (db==0) SM("A megadott id-jű szerző nem létezik, nem történt változás");
			else
			SM("A megadott id-jű szerző új neve:"+nev);
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void Grouped_Aggregated_Value() {
		String nev="", x="\t";
		int db=0;
		Connection conn = Connect();
		String sqlp = "SELECT Szerzo.nev, COUNT(Konyv.id) AS konyv_darabszam "
				+ "FROM Konyv "
				+ "LEFT JOIN Szerzo ON Konyv.szerzo = Szerzo.id "
				+ "GROUP BY Szerzo.nev";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				nev = rs.getString("nev");
				db = rs.getInt("konyv_darabszam");
				SM(nev+x+db);
			}
			rs.close();
		} catch (SQLException e) {
			SM("JDBC UpdateData: "+e.getMessage());
		}
		Disconnect(conn);
	}	
	
	public void UniList(String table, String condition, String row1, String row2) {
		String sqlp = "Select "+row1+", "+row2+" FROM "+table+" WHERE "+condition;
		Connection conn = Connect();
		SM(sqlp);
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				String data1 = rs.getString(row1);
				String data2 = rs.getString(row2);
				SM(data1 + " | " +data2);
			}
			rs.close();
		} catch (SQLException e) {
			SM(e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void InsertKonyv(String id, String cim, String megjelenes, String szerzo, String nyelv) {
		Connection conn = Connect();
		String sqlp = "INSERT INTO Konyv Values('"+id+"', '"+cim+"', '"+megjelenes+"', '"+szerzo+"', '"+nyelv+"')";
		try {
			Statement s =conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void InsertSzerzo(String id, String nev, String szuletes, String kor, String konyvek) {
		Connection conn = Connect();
		String sqlp = "INSERT INTO Szerzo Values('"+id+"', '"+nev+"', '"+szuletes+"', '"+kor+"', '"+konyvek+"')";
		try {
			Statement s =conn.createStatement();
			s.execute(sqlp);
			SM("Insert OK");
		} catch (SQLException e) {
			SM("JDBC insert: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void ReadAllDataKonyv () {
		String cim="", megjelenes="", nyelv="", x="\t";
		int id=0, szerzo=0;
		String sqlp= "SELECT id, cim, megjelenes, szerzo, nyelv FROM Konyv";
		Connection conn= Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				id = rs.getInt("id");
				cim = rs.getString("cim");
				megjelenes = rs.getString("megjelenes");
				szerzo = rs.getInt("szerzo");
				nyelv = rs.getString("nyelv");
				SM(id+x+cim+x+megjelenes+x+szerzo+x+nyelv);
			}
			rs.close();
		} catch (SQLException e) {
			SM("ReadAllData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void ReadAllDataSzerzo() {
		String nev="", szuletes="", x="\t";
		int id=0, kor=0, konyvek=0;
		String sqlp= "SELECT id, nev, szuletes, kor, konyvek FROM Szerzo";
		Connection conn= Connect();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			while(rs.next()) {
				id = rs.getInt("id");
				nev = rs.getString("nev");
				szuletes = rs.getString("szuletes");
				kor = rs.getInt("kor");
				konyvek = rs.getInt("konyvek");
				SM(id+x+nev+x+szuletes+x+kor+x+konyvek);
			}
			rs.close();
		} catch (SQLException e) {
			SM("ReadAllData: "+e.getMessage());
		}
		Disconnect(conn);
	}
	
	public void CommandExec(String command) {
		Connection conn = Connect();
		String sqlpP = "PRAGMA foreign_keys=on;";
		String sqlp = command;
		try {
			Statement s = conn.createStatement();
			s.execute(sqlpP);
			s.execute(sqlp);
			SM("Command OK");
		} catch (SQLException e) {
			String msg = e.getMessage();
			if (msg.contains("FOREIGN KEY constraint failed")) {
				msg = "Hibás Idegenkulcs érték!";
			}
			SM("CommandExec: " +msg);
		}
		Disconnect(conn);
	}
	
	public Connection Connect() {
		Connection conn = null;
		String url = "jdbc:sqlite:C:/sqlite3/beadandoDB";
		try {
			conn = DriverManager.getConnection(url);
			//SM("Sikeres kapcsolódás");
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
				//SM("Sikeres lekapcsolódás");
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
