package com.example.xmlundrekursion;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 *
 * @author Taha
 *
 */
public class SAXStatistikCrawler {

	/**
	 * Erzeugt eine leere Statistik sowie einen ContentHandler, uebergibt diesen an den SAX-Parser
	 * und startet das Parsing, welches die Statistik fuellt.
	 * 
	 * @param dateiname Der Name der XML-Datei, fuer die die Statistik erstellt werden soll
	 * @return die erstellte Dokument-Statistik
	 * 
	 */
	public XMLDokumentStatistik saxCrawlingStarten(String dateiname) {

		//leere Statistik erstellen
		XMLDokumentStatistik xmlDokumentStatistik = new XMLDokumentStatistik(dateiname);

		//Parsing vorbereiten
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setNamespaceAware(true);

		try {
			//Parsen mittels SAXStatistikCrawler
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse(new FileInputStream(dateiname), new SAXStatistikContentHandler(xmlDokumentStatistik));

        } catch (
			SAXException | ParserConfigurationException | IOException e) {
            System.err.println(e.getMessage());
        }
		
		return xmlDokumentStatistik;
	}

}
