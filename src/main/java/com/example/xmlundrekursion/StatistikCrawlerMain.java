package com.example.xmlundrekursion;

/** Erstellt XML-Statistiken - nacheinander per SAX und DOM - und gibt sie aus.
 * @author holger
 */
public class StatistikCrawlerMain {

	private static final String XML_DATEINAME = "/Users/tahaalbukhaiti/Downloads/Java/XMLUndRekursion/src/main/resources/com/example/xmlundrekursion/beispiel.xml";
	
	
	/**
	 * Startet nacheinander das SAX-Crawling und das DOM-Crawling und gibt jeweils die Ergebnisse aus.
	 * @param args Keine Verwendung
	 */
	public static void main(String[] args){

		//erst SAX
		SAXStatistikCrawler ssc = new SAXStatistikCrawler();
		XMLDokumentStatistik statistikSAX = ssc.saxCrawlingStarten(XML_DATEINAME);
		System.out.println("Ergebnis SAX-Crawling:");
		System.out.println(statistikSAX);
		
		//dann DOM
		DOMStatistikCrawler dsc = new DOMStatistikCrawler();
		XMLDokumentStatistik statistikDOM = dsc.domCrawlingStarten(XML_DATEINAME);
		System.out.println("Ergebnis DOM-Crawling:");
		System.out.println(statistikDOM);
		
	}
	
}