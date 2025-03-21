
public class Program {
	static DBMethods dbm = new DBMethods();
	static ConsoleMethods cm = new ConsoleMethods();

	public static void main(String[] args) {
		dbm.Reg();

		/*
		 * dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
		 * String ekod = cm.ReadData("Kérem az ekodot: ");
		 * String nev = cm.ReadData("Kérem a nevet: ");
		 * String varos = cm.ReadData("Kérem a varost: ");
		 * String telefon = cm.ReadData("Kérem a telefont: ");
		 * dbm.Insert(ekod, nev, varos, telefon);
		 */

		/*
		 * dbm.SM("Rekord módosítása rendszám alapján ");
		 * String ekod = cm.ReadData("Kérem a rendszámot. ");
		 * String nev = cm.ReadData("Kérem az autó új árát:");
		 * dbm.UpdateDataTulajdonos(ekod, nev);
		 */

		/*
		 * dbm.SM("Tábla elemeinek száma: ");
		 * String table = cm.ReadData("Kérem a tábla nevét. ");
		 * dbm.CountTable(table);
		 */

		/*
		 * dbm.SM("Tábla oszlopának összege és átlaga: ");
		 * String table = cm.ReadData("Kérem a tábla nevét. ");
		 * String row = cm.ReadData("Kérem az oszlop nevét. ");
		 * dbm.Sum_and_Avg(table, row);
		 */

		/*
		 * dbm.SM("Rendezett lista készítés");
		 * String table = cm.ReadData("Kérem a tábla nevét. ");
		 * String row = cm.ReadData("Kérem az oszlop nevét. ");
		 * String order = cm.ReadData("Kérem a sorrendet: ");
		 * dbm.Listby(table, row, order);
		 */

		/* dbm.Grouped_Aggregated_Value(); */

		/*
		 * dbm.SM("Tulajdonos darabszám ekod alapján");
		 * String ekod = cm.ReadData("Kérem a ekod értékét: ");
		 * int db = dbm.SelectCount("Tulajdonos", "ekod = "+ekod);
		 * dbm.SM("Eredmény: "+db);
		 */
		while (1 != 0) {
			menu();
		}

	}

	static void menu() {
		dbm.SM("\n");
		dbm.SM("Menü");
		dbm.SM("===============");
		dbm.SM("0. Kilépés");
		dbm.SM("1. Auto");
		dbm.SM("2. Tulajdonos");
		String ms = cm.ReadData("Add meg a választott menü számát:");
		int m = -1;
		if (test(ms))
			m = StringToInt(ms);
		switch (m) {
			case 0:
				dbm.SM("A program leállt");
				System.exit(0);
				break;
			case 1:
				dbm.SM("\n");
				dbm.SM("Auto");
				dbm.SM("===============");
				dbm.SM("0. Kilépés");
				dbm.SM("1. Listázás");
				dbm.SM("2. Beszúrás");
				dbm.SM("3. Törlés");
				ms = cm.ReadData("Add meg a választott menü számát:");
				m = -1;
				if (test(ms))
					m = StringToInt(ms);
				switch (m) {
					case 0:
						dbm.SM("A program leállt");
						System.exit(0);
						break;
					case 1:
						dbm.ReadAllDataAuto();
						break;
					case 2:
						dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
						String rendszam = cm.ReadData("Kérem a rendszámot: ");
						String tipus = cm.ReadData("Kérem a típust: ");
						String szin = cm.ReadData("Kérem a színt: ");
						String kor = cm.ReadData("Kérem az autó korát: ");
						String ar = cm.ReadData("Kérem az autó árát: ");
						String tulaj = cm.ReadData("Kérem az autó tulaját: ");
						dbm.InsertAuto(rendszam, tipus, szin, kor, ar, tulaj);
						break;
					case 3:
						dbm.SM("Rekord törlése rendszám alapján ");
						String rsz = cm.ReadData("Kérem a rendszámot: ");
						dbm.DeleteDataAuto(rsz);
						break;
				}
			case 2:
				dbm.SM("\n");
				dbm.SM("Tulajdonos");
				dbm.SM("===============");
				dbm.SM("0. Kilépés");
				dbm.SM("1. Listázás");
				dbm.SM("2. Beszúrás");
				dbm.SM("3. Törlés");
				ms = cm.ReadData("Add meg a választott menü számát:");
				m = -1;
				if (test(ms))
					m = StringToInt(ms);
				switch (m) {
					case 0:
						dbm.SM("A program leállt");
						System.exit(0);
						break;
					case 1:
						dbm.ReadAllDataTulajdonos();
						break;
					case 2:
						dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
						String ekod = cm.ReadData("Kérem az ekódot: ");
						String nev = cm.ReadData("Kérem a nevet: ");
						String varos = cm.ReadData("Kérem a várost: ");
						String telefon = cm.ReadData("Kérem a telefonszámot: ");
						dbm.InsertTulajdonos(ekod, nev, varos, telefon);
						break;
					case 3:
						dbm.SM("Rekord törlése rendszám alapján ");
						String kod = cm.ReadData("Kérem a rendszámot: ");
						dbm.DeleteDataTulajdonos(kod);
						break;
				}
		}
	}





	



	static int StringToInt(String s) {
		int x = 0;
		try {
			x = Integer.valueOf(s);
		} catch (NumberFormatException nfe) {
		}
		return x;
	}

	static boolean test(String s) {
		if (s.length() == 0) {
			dbm.SM("Próbáld újra");
			return false;
		} else {
			try {
				int x = Integer.valueOf(s);
				if (x >= 0 && x < 4)
					return true;
				else {
					dbm.SM("0-3 közötti számot kérek");
					return false;
				}
			} catch (NumberFormatException nfe) {
				dbm.SM("helytelen adat");
				return false;
			}
		}
	}

}