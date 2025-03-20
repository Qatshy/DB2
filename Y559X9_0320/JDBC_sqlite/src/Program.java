
public class Program {
	static DBMethods dbm = new DBMethods();
	static ConsoleMethods cm = new ConsoleMethods();

	public static void main(String[] args) {
		dbm.Reg();
		
		/*dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
		String rendszam = cm.ReadData("Kérem a rendszámot: ");
		String tipus = cm.ReadData("Kérem a típust: ");
		String szin = cm.ReadData("Kérem a színt: ");
		String kor = cm.ReadData("Kérem az autó korát: ");
		String ar = cm.ReadData("Kérem az autó árát: ");
		String tulaj = cm.ReadData("Kérem az autó tulaját: ");
		dbm.Insert(rendszam, tipus, szin, kor, ar, tulaj);*/

		/*dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
		String ekod = cm.ReadData("Kérem az ekodot: ");
		String nev = cm.ReadData("Kérem a nevet: ");
		String varos = cm.ReadData("Kérem a varost: ");
		String telefon = cm.ReadData("Kérem a telefont: ");
		dbm.Insert(ekod, nev, varos, telefon);*/
		
		/*dbm.SM("Rekord törlése rendszám alapján ");
		String rsz = cm.ReadData("Kérem a rendszámot: ");
		dbm.DeleteData(rsz);
		*/
		
		/*dbm.SM("Rekord módosítása rendszám alapján ");
		String ekod = cm.ReadData("Kérem a rendszámot. ");
		String nev = cm.ReadData("Kérem az autó új árát:");
		dbm.UpdateDataTulajdonos(ekod, nev);*/
		
		/*dbm.SM("Tábla elemeinek száma: ");
		String table = cm.ReadData("Kérem a tábla nevét. ");
		dbm.CountTable(table);*/
		
		/*dbm.SM("Tábla oszlopának összege és átlaga: ");
		String table = cm.ReadData("Kérem a tábla nevét. ");
		String row = cm.ReadData("Kérem az oszlop nevét. ");
		dbm.Sum_and_Avg(table, row);*/
		
		/*dbm.SM("Rendezett lista készítés");
		String table = cm.ReadData("Kérem a tábla nevét. ");
		String row = cm.ReadData("Kérem az oszlop nevét. ");
		String order = cm.ReadData("Kérem a sorrendet: ");
		dbm.Listby(table, row, order);*/
		
		/*dbm.Grouped_Aggregated_Value();*/

		dbm.SM("Tulajdonos darabszám ekod alapján");
		String tkod = cm.ReadData("Kérem a ekod értékét: ");
		int db = dbm.SelectCount("Tulajdonos", "Tkod = "+tkod);
		dbm.SM("Eredmény: "+db);
		
		
	dbm.ReadAllDataTulajdonos();

	}

}
