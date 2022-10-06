package com.example.xmlundrekursion;

/**
 * Speichert statistische Informationen zu einem XML-Element.
 * @author holger
 * @author Taha
 */
public class XMLElementStatistik {

	private int laufendeNr;
	private String elementName;
	private int tiefe;
	private int anzahlAttribute;
	private int textlaenge;
	
	
	public XMLElementStatistik(int laufendeNr, String elementName, int tiefe, int anzahlAttribute) {
		this.laufendeNr = laufendeNr;
		this.elementName = elementName;
		this.tiefe = tiefe;
		this.anzahlAttribute = anzahlAttribute;
		this.textlaenge = 0;
	}




	public  void addTextlaenge(int inkrement) {
		this.textlaenge += inkrement;
	}
	
	
	public int getTiefe() {
		return tiefe;
	}
	
	
	public int getAnzahlAttribute() {
		return anzahlAttribute;
	}

	
	public int getTextlaenge() {
		return textlaenge;
	}

	
	@Override
	public String toString() {
		String str = "  " + laufendeNr + ". " + elementName + ": Tiefe " + tiefe + 
				", Attribute " + anzahlAttribute + ", Textlaenge " + textlaenge + "\n";

		return str;
	}
}
