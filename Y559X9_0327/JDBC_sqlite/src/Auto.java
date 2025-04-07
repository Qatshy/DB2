
public class Auto {
String rendszam;
String tipus;
String szin;
int kor;
int ar;
int tulaj;

public Auto(String rendszam, String tipus, String szin, int kor, int ar,  int tulaj) {
	super();
	this.rendszam = rendszam;
	this.tipus = tipus;
	this.szin = szin;
	this.kor = kor;
	this.ar = ar;
	this.tulaj = tulaj;
}

public String getRendszam() {
	return rendszam;
}

public String getTipus() {
	return tipus;
}

public String getSzin() {
	return szin;
}

public int getKor() {
	return kor;
}

public int getAr() {
	return ar;
}

public int getTulaj() {
	return tulaj;
}
}
