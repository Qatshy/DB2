import java.util.regex.*;
import java.time.LocalDate;

public class Program {
	static DBMethods dbm = new DBMethods();
	static ConsoleMethods cm = new ConsoleMethods();

	public static void main(String[] args) {
		dbm.Reg();
		System.out.println("paraméterezett kétfázisú parancsok: ");
		Konyv[] konyv = new Konyv[2];
		konyv[0] = new Konyv(3, "Laptop táska", "2025.04.18", 1, "Magyar");
		konyv[1] = new Konyv(4, "Forma 1 hell yeah", "2024.04.16", 2, "Német");
		dbm.InsertWithPS(konyv);
		dbm.ReadAllDataKonyv();
		dbm.WriteAllDataKonyv();
		dbm.DeleteWithPS(konyv);
		dbm.ReadAllDataKonyv();
		dbm.WriteAllDataKonyv();

		while (1 != 0) {
			menu();
		}
	}

	static void menu() {
		dbm.SM("\n");
		dbm.SM("Menü");
		dbm.SM("===============");
		dbm.SM("0. Kilépés");
		dbm.SM("1. Konyv");
		dbm.SM("2. Szerzo");
		String ms = cm.ReadData("Add meg a választott menü számát:");
		int m = -1;
		if (test1(ms))
			m = StringToInt(ms);
		switch (m) {
		case 0:
			dbm.SM("A program leállt");
			System.exit(0);
			break;

		case 1:
			dbm.SM("\n");
			dbm.SM("Konyv");
			dbm.SM("===============");
			dbm.SM("0. Kilépés");
			dbm.SM("1. Adatok felvitele");
			dbm.SM("2. Adatok lekérdezése (szűrés több mező egy tábla)");
			dbm.SM("3. Adatok lekérdezése (szűrés egy mező kapcsolt táblák)");
			dbm.SM("4. Adatok módosítása");
			dbm.SM("5. Kijelölt adatok törlése");
			ms = cm.ReadData("Add meg a választott menü számát:");
			m = -1;
			if (test2(ms))
				m = StringToInt(ms);
			switch (m) {
			case 0:
				dbm.SM("A program leállt");
				System.exit(0);
				break;
			case 1:
				InsertKonyv();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataKonyv();
				break;
			case 2:
				UniList();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataKonyv();
				break;
			case 3:
				dbm.Grouped_Aggregated_Value();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataKonyv();
				break;
			case 4:
				UpdateDataKonyv();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataKonyv();
				break;
			case 5:
				DeleteDatakonyv();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataKonyv();
				break;
			}
			break;

		case 2:
			dbm.SM("\n");
			dbm.SM("Szerzo");
			dbm.SM("===============");
			dbm.SM("0. Kilépés");
			dbm.SM("1. Adatok felvitele");
			dbm.SM("2. Adatok lekérdezése (szűrés több mező egy tábla)");
			dbm.SM("3. Adatok lekérdezése (szűrés egy mező kapcsolt táblák)");
			dbm.SM("4. Adatok módosítása");
			dbm.SM("5. Kijelölt adatok törlése");
			ms = cm.ReadData("Add meg a választott menü számát:");
			m = -1;
			if (test2(ms))
				m = StringToInt(ms);
			switch (m) {
			case 0:
				dbm.SM("A program leállt");
				System.exit(0);
				break;
			case 1:
				InsertSzerzo();
				dbm.ReadAllDataSzerzo();
				dbm.WriteAllDataSzerzo();
				break;
			case 2:
				UniList();
				dbm.ReadAllDataSzerzo();
				dbm.WriteAllDataSzerzo();
				break;
			case 3:
				dbm.Grouped_Aggregated_Value();
				dbm.ReadAllDataSzerzo();
				dbm.WriteAllDataSzerzo();
				break;
			case 4:
				UpdateDataSzerzo();
				dbm.ReadAllDataSzerzo();
				dbm.WriteAllDataSzerzo();
				break;
			case 5:
				DeleteDataSzerzo();
				dbm.ReadAllDataKonyv();
				dbm.WriteAllDataSzerzo();
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

	static boolean test1(String s) {
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

	static boolean test2(String s) {
		if (s.length() == 0) {
			dbm.SM("Próbáld újra");
			return false;
		} else {
			try {
				int x = Integer.valueOf(s);
				if (x >= 0 && x < 6)
					return true;
				else {
					dbm.SM("0-5 közötti számot kérek");
					return false;
				}
			} catch (NumberFormatException nfe) {
				dbm.SM("helytelen adat");
				return false;
			}
		}
	}

	public static void DeleteDataSzerzo() {
		String id = cm.ReadData("Adja meg a szerző id-jét:");

		dbm.DeleteDataSzerzo(id);
	}

	public static void DeleteDatakonyv() {
		String id = cm.ReadData("Adja meg a könyv id-jét:");

		dbm.DeleteDataKonyv(id);
	}

	public static void UpdateDataKonyv() {
		String id = cm.ReadData("Adja meg a könyv id-jét:");
		String nyelv = cm.ReadData("Adja meg a könyv új nyelvét:");

		dbm.UpdateDataKonyv(id, nyelv);
	}

	public static void UpdateDataSzerzo() {
		String id = cm.ReadData("Adja meg a szerző id-jét:");
		String nev = cm.ReadData("Adja meg a szerző új nevét:");

		dbm.UpdateDataSzerzo(id, nev);
	}

	public static void UniList() {
		String table = cm.ReadData("Adja meg a tábla nevét:");
		String row1 = cm.ReadData("Adja meg az egyik mező nevét:");
		String row2 = cm.ReadData("Adja meg a másik mező nevét:");
		String condition = cm.ReadData("Adja meg a feltételt:");

		dbm.UniList(table, condition, row1, row2);
	}

	public static void InsertKonyv() {
		dbm.SM("Adatok felvitele:");
		String id, cim, megjelenes, szerzo, nyelv;
		boolean ok;

		// id

		do {
			ok = false;
			id = cm.ReadData("adja meg az id-t:");
			try {
				int idcheck = Integer.parseInt(id);
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format: " + id);
			}
		} while (!ok);

		// cím

		cim = cm.ReadData("adja meg a címét:");

		// megjelenes
		do {
			megjelenes = cm.ReadData("adja meg a megjelenési datumot(YYYY.MM.DD):");
			ok = isValidDate(megjelenes);
			if (!ok) {
				System.out.println("hibás formátum");
			}
		} while (!ok);

		// szerzo

		do {
			ok = false;
			szerzo = cm.ReadData("adja meg az szerzo id-jét:");
			try {
				int idcheck = Integer.parseInt(id);
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format: " + id);
			}
		} while (!ok);

		// nyelv

		nyelv = cm.ReadData("adja meg a nyelvet:");

		dbm.InsertKonyv(id, cim, megjelenes, szerzo, nyelv);
	}

	public static void InsertSzerzo() {
		dbm.SM("Adatok felvitele:");
		String id, kor, konyvek, nev, szuletes;
		boolean ok;

		// id

		do {
			ok = false;
			id = cm.ReadData("adja meg az id-t:");
			try {
				int idcheck = Integer.parseInt(id);
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format: " + id);
			}
		} while (!ok);

		// nev

		nev = cm.ReadData("adja meg a nevet:");

		// szuletes
		do {
			szuletes = cm.ReadData("adja meg a szuletesi datumot(YYYY.MM.DD):");
			ok = isValidDate(szuletes);
			if (!ok) {
				System.out.println("hibás formátum");
			}
		} while (!ok);

		// kor

		int szuletesint = Integer.parseInt(szuletes.substring(0, 4));
		int year = LocalDate.now().getYear();
		int korint = year - szuletesint;
		kor = "" + korint + "";

		// konyvek

		do {
			ok = false;
			konyvek = cm.ReadData("adja meg a konyvek számát:");
			try {
				int idcheck = Integer.parseInt(id);
				ok = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format: " + id);
			}
		} while (!ok);

		dbm.InsertSzerzo(id, nev, szuletes, kor, konyvek);
	}

	public static boolean isValidDate(String input) {
		String pattern = "^(\\d{4})\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])$";

		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(input);

		return matcher.matches();
	}

}