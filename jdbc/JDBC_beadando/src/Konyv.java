
public class Konyv {
int id;
String cim;
String megjelenes;
int szerzo;
String nyelv;

public Konyv(int id, String cim, String megjelenes, int szerzo, String nyelv) {
	super();
	this.id = id;
	this.cim = cim;
	this.megjelenes = megjelenes;
	this.szerzo = szerzo;
	this.nyelv = nyelv;
}

public int getId() {
	return id;
}

public String getCim() {
	return cim;
}

public String getMegjelenes() {
	return megjelenes;
}

public int getSzerzo() {
	return szerzo;
}

public String getNyelv() {
	return nyelv;
}
}
