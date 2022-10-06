package com.example.xmlundrekursion;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author Taha
 * 
 */

public class DOMStatistikCrawler {

	private XMLDokumentStatistik xmlDokumentStatistik;
	private int laufendeNr;

	
	/**
	 * Erzeugt eine leere Statistik, erstellt einen DOM-Baum, ermittelt das Wurzelelement, legt die
	 * zugehoerige Elementstatistik an und startet das rekursive Crawling im DOM-Baum, welches die
	 * Statistik fuellt.
	 * 
	 * @param dateiname Der Name der XML-Datei, fuer die die Statistik erstellt werden soll
	 * @return die erstellte Dokument-Statistik
	 * 
	 */
	public XMLDokumentStatistik domCrawlingStarten(String dateiname) {

		//leere Statistik erstellen
		xmlDokumentStatistik = new XMLDokumentStatistik(dateiname);

		//Parsing vorbereiten
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        try {
        	//Parsen
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(dateiname));

            //Wurzelelement ermitteln und fuer dieses die Elementstatistik erstellen
            Element wurzelelement = document.getDocumentElement();
            laufendeNr = 1;
			XMLElementStatistik xmlElementStatistik = new XMLElementStatistik(
					laufendeNr++, 
					wurzelelement.getTagName(), 
					0, //Tiefe
					wurzelelement.getAttributes().getLength()
				);

			//Elementstatistik in Dokumentstatistik eintragen
			xmlDokumentStatistik.addXMLElementStatistik(xmlElementStatistik);

			//rekursives Crawling starten
			rekursivCrawlen(wurzelelement, xmlElementStatistik);      

        } catch (
			SAXException | ParserConfigurationException | IOException e) {
            System.err.println(e.getMessage());
        }
		
		return xmlDokumentStatistik;
	}

	
	/**
	 * Durchlaeuft die Kindknoten von element, ermittelt bei Textknoten deren Laenge und fuegt sie
	 * der Elementstatistik hinzu. Bei Elementknoten wird das Crawling rekursiv fortgesetzt.
	 * 
	 * @param element Das Element, dessen Kindknoten zu verarbeiten sind
	 * @param xmlElementStatistik Die Elementstatistik von element
	 */
	private void rekursivCrawlen(Element element, XMLElementStatistik xmlElementStatistik) {
		int level = 0;
		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.TEXT_NODE){
				xmlElementStatistik.addTextlaenge(node.getNodeValue().length());
			} else if (node.getNodeType() == Node.ELEMENT_NODE){
				xmlElementStatistik  = new XMLElementStatistik(laufendeNr++, node.getLocalName(), level, node.getAttributes().getLength());
				xmlDokumentStatistik.addXMLElementStatistik(xmlElementStatistik);

				rekursivCrawlen((Element) node, xmlElementStatistik);
			}
		}
	}

}
