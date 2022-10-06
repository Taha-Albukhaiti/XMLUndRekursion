package com.example.xmlundrekursion;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * Speichert statistische Informationen zu einem XML-Dokument.
 *
 * @author holger
 * @author Taha
 */
public class XMLDokumentStatistik {

    private String dokumentName;
    private List<XMLElementStatistik> xmlElementStatistiken;


    public XMLDokumentStatistik(String dokumentName) {
        this.dokumentName = dokumentName;
        this.xmlElementStatistiken = new LinkedList<>();
    }


    public void addXMLElementStatistik(XMLElementStatistik xmlElementStatistik) {
        xmlElementStatistiken.add(xmlElementStatistik);
    }


    public String getDokumentName() {
        return dokumentName;
    }


    public int getAnzahlElemente() {
        return xmlElementStatistiken.size();
    }


    public int getAnzahlAttribute() {
        return xmlElementStatistiken.stream().mapToInt(e -> e.getAnzahlAttribute()).sum();
    }


    public OptionalInt getMaxTiefe() {
        //max() liefert OptionalInt, dieses enthaelt keinen Wert, wenn der IntStream leer war
        return xmlElementStatistiken.stream().mapToInt(e -> e.getTiefe()).max();
    }


    public OptionalDouble getAvgTextlaenge() {
        //average() liefert OptionalDouble, dieses enthaelt keinen Wert, wenn der IntStream leer war
        return xmlElementStatistiken.stream().mapToInt(e -> e.getTextlaenge()).average();
    }


    @Override
    public String toString() {
        String str = "Dokumentname: " + dokumentName + "\n";
        for (XMLElementStatistik e : xmlElementStatistiken) {
            str += e.toString();
        }
        str += "\nGESAMTAUSWERTUNG:";
        str += "\nAnzahl Elemente: " + getAnzahlElemente();
        str += "\nAnzahl Attribute: " + getAnzahlAttribute();

        //sofern die maximale Tiefe ermittelt werden konnte, diese ausgeben, sonst "-"
        OptionalInt maxTiefe = getMaxTiefe();
        str += "\nMaximale Tiefe: " + (maxTiefe.isPresent() ? maxTiefe.getAsInt() : "-");

        //sofern die durchschnittliche Textlaenge ermittelt werden konnte,
        //diese mit zwei Nachkommastellen ausgeben, sonst "-"
        OptionalDouble avgTextlaenge = getAvgTextlaenge();

        str += "\nDurchschnittliche Textlaenge pro Knoten: " +
                (avgTextlaenge.isPresent() ? String.format("%.2f", avgTextlaenge.getAsDouble()) : "-");
        str += "\n";
        return str;
    }
}
