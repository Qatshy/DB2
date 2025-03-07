
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
		dbm.Insert(rendszam, tipus, szin, kor, ar);
*/
		/*dbm.SM("Rekord törlése rendszám alapján ");
		String rsz = cm.ReadData("Kérem a rendszámot: ");
		dbm.DeleteData(rsz);
		*/
		
		dbm.SM("Rekord módosítása rendszám alapján ");
		String rsz = cm.ReadData("Kérem a rendszámot. ");
		String ar = cm.ReadData("Kérem az autó új árát:");
		dbm.UpdateData(rsz, ar);
	dbm.ReadAllData();

	}

}
