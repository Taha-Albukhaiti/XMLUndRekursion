package com.example.xmlundrekursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.scene.control.Label;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Taha
 *
 * Erhebt beim Parsen die gewuenschten Statistik-Daten zum XML-Dokument.
 */
public class SAXStatistikContentHandler extends DefaultHandler {
    int at = 0;

    private static List<String> list = new ArrayList<>();
    //zum Nummerieren der Elemente
    private int laufendeNr = 1;

    int max, level;

    //die zu fuellende Dokumentstatistik
    private XMLDokumentStatistik xmlDokumentStatistik;

    //der Stack kann verwendet werden, um die Tiefe eines Elements sowie die summierte Textlaenge zu ermitteln
    private Stack<XMLElementStatistik> elementStack = new Stack<>();

    String charactersString;


    public SAXStatistikContentHandler(XMLDokumentStatistik xmlDokumentStatistik) {
        this.xmlDokumentStatistik = xmlDokumentStatistik;
    }


    @Override
    public void startDocument() throws SAXException {
        level=0; max=0;

    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {
        list.add(localName);
        at += attr.getLength();

        xmlDokumentStatistik.addXMLElementStatistik(
                new XMLElementStatistik(laufendeNr++, localName.toString(), level, attr.getLength()));
        level++;
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        charactersString = new String(ch, start, length);

    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (level>max) max=level; level--;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Testing" + list.size());
        System.out.println("attribute: " + at);
        System.out.println("Maximale Tiefe: " + max);
    }

    @Override
    public void fatalError(SAXParseException e) {
        System.err.println("Fatal error: " + e.getMessage() + " at line : "
                + e.getLineNumber() + " column " + e.getColumnNumber());
    }


    @Override
    public void error(SAXParseException e) {
        System.err.println("Error: " + e.getMessage() + " at line : "
                + e.getLineNumber() + " column " + e.getColumnNumber());
    }


    @Override
    public void warning(SAXParseException e) {
        System.err.println("Warning: " + e.getMessage() + " at line : "
                + e.getLineNumber() + " column " + e.getColumnNumber());
    }

}
