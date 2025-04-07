
public class Program {
	static DBMethods dbm = new DBMethods();
	static ConsoleMethods cm = new ConsoleMethods();

	public static void main(String[] args) {
		dbm.Reg();

		dbm.SM("Rekord adatainak beolvasása, rekord beszúrása ");
		String table = cm.ReadData("Kérem a táblát: ");
		String condition = cm.ReadData("Kérem a feltételt: ");
		String row1 = cm.ReadData("Kérem a mező1-et: ");
		String row2 = cm.ReadData("Kérem a mező2-t: ");
		dbm.UniList(table, condition, row1, row2);
	
		Auto[] auto = new Auto[5];
		auto[0] = new Auto("DER-666", "Skoda Rapid", "fehér", 5, 4800000, 100);
		auto[1] = new Auto("AFT-284", "Joe Biden", "piros", 85, 25000001, 101);
		auto[2] = new Auto("BAT-001", "Batmobile", "fekete", 2, 800000000, 102);
		auto[3] = new Auto("NAT-284", "Subaru Lesbian", "kék", 10, 3400000, 103);
		auto[4] = new Auto("TAH-986", "Tesla Shitbox", "fehér", 1, 900000, 104);
		dbm.InsertWithPS(auto);
		dbm.DeleteWithPS(auto);
		dbm.JoinWithPS(auto);
		
	}}
		
/*		while (1 != 0) {
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

		// Autó tábla

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
			break;

			// Tulajdonos tábla

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
				dbm.SM("Rekord törlése ekód alapján ");
				String kod = cm.ReadData("Kérem az ekódot: ");
				dbm.DeleteDataTulajdonos(kod);
				break;
			}
			break;
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

}*/