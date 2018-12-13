package ba.unsa.etf.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public static ArrayList<Grad> ucitajGradove() {
        Scanner ulaz = null;
        ArrayList<Grad> gradovi = new ArrayList<Grad>();
        try {
            ulaz = new Scanner(new FileReader("mjerenja.txt")).useDelimiter("[, \\n\\r]");

        } catch (IOException err) {
            System.out.println("Datoteka mjerenja.txt se ne može otvoriti");
        }

        while (ulaz.hasNext()) {
            String naziv = ulaz.next();
            double[] niz = new double[1000];
            int i = 0;
            try {

                while (i < 1000 && ulaz.hasNextDouble()) {
                    niz[i] = ulaz.nextDouble();
                    i++;
                }
                if (ulaz.hasNext()) ulaz.nextLine();
            } catch (NumberFormatException e) {
            }
            gradovi.add(new Grad(naziv, 0, niz));
        }
        ulaz.close();
        return gradovi;
    }

    public static UN ucitajXmlS(ArrayList<Grad> gradovi) {
        UN un = new UN();

        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("UN.xml"));
            un = (UN) ulaz.readObject();
            ulaz.close();
        } catch (Exception e) {
            System.out.println("Greška: " + e);
        }

        for (Drzava a : un.getDrzave()) {
            for (int i = 0; i < gradovi.size(); i++) {
                if (a.getGlavniGrad().equals(gradovi.get(i))) {
                    a.getGlavniGrad().setTemperature(gradovi.get(i).getTemperature());
                }
            }
        }
        return un;
    }

    public static Grad ucitajGrad(Element dijeteDrzave) {

        Grad glavniGrad = new Grad();
        NodeList djecaGrada = dijeteDrzave.getChildNodes();

        glavniGrad.setBrojStanovnika(Integer.parseInt(dijeteDrzave.getAttribute("stanovnika")));

        for (int k = 0; k < djecaGrada.getLength(); k++) {

            Node dijete3 = djecaGrada.item(k);

            if (dijete3 instanceof Element) {

                Element dijeteGrada = (Element) dijete3;

                if (dijeteGrada.getTagName().equals("naziv")) {

                    glavniGrad.setNaziv(dijeteGrada.getTextContent());

                }

            }

        }
        return glavniGrad;
    }

    public static Drzava ucitajDrzavu(Node dijete, ArrayList<Grad> gradovi) {
        Drzava d = new Drzava();
        if (dijete instanceof Element) {
            Element drzava = (Element) dijete;

            d.setBrojStanovnika(Integer.parseInt(drzava.getAttribute("stanovnika")));

            NodeList djecaDrzave = drzava.getChildNodes();

            for (int j = 0; j < djecaDrzave.getLength(); j++) {

                Node dijete2 = djecaDrzave.item(j);

                if (dijete2 instanceof Element) {

                    Element dijeteDrzave = (Element) dijete2;

                    if (dijeteDrzave.getTagName().equals("naziv")) {

                        d.setNaziv(dijeteDrzave.getTextContent().trim());

                    } else if (dijeteDrzave.getTagName().equals("glavnigrad")) {

                        Grad glavniGrad = ucitajGrad(dijeteDrzave);
                        for (int i = 0; i < gradovi.size(); i++) {
                            if (glavniGrad.equals(gradovi.get(i))) {
                                glavniGrad.setTemperature(gradovi.get(i).getTemperature());
                            }
                        }
                        d.setGlavniGrad(glavniGrad);
                    } else if (dijeteDrzave.getTagName().equals("povrsina")) {

                        d.setPovrsina(Double.parseDouble(dijeteDrzave.getTextContent()));

                        d.setJedinicaZaPovrsinu(dijeteDrzave.getAttribute("jedinica"));

                    }

                }
            }
        }
        return d;
    }

    public static UN ucitajXML(ArrayList<Grad> gradovi) {
        Document dat = null;
        UN un = new UN();
        ArrayList<Drzava> drzava = new ArrayList<>();

        try {
            DocumentBuilder doc = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            dat = doc.parse(new File("drzave.xml"));
        } catch (Exception e) {
            System.out.println("datoteka nije uredu");
            return null;
        }
        try {
            Element root = dat.getDocumentElement();
            NodeList drzave = root.getChildNodes();
            int velicina = drzave.getLength();
            for (int i = 0; i < velicina; i++) {
                Node child = drzave.item(i);
                if (child instanceof Element) drzava.add(ucitajDrzavu(child, gradovi));

            }
        } catch (Exception e) {

            System.out.println("Nije uredu: " + e);

        }
        un.setDrzave(drzava);


        return un;
    }

    public static void zapisiXml(UN un) {

        try {
            XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("UN.xml"));

            izlaz.writeObject(un);
            izlaz.close();
        } catch (Exception e) {
            System.out.println("Greška: " + e);
        }
    }

    public static void main(String[] args) {
        // write your code here
        ArrayList<Grad> gradovi = ucitajGradove();

        UN un = new UN();
        un.dodajDrzavu(new Drzava("Bosna i Hercegovina", 3000000, 51000, "km^2", new Grad("Sarajevo")));
        un.dodajDrzavu(new Drzava("Hrvatska", 7000000, 55000, "km^2", new Grad("Zagreb")));
        un.dodajDrzavu(new Drzava("Srbija", 8000000, 71000, "km^2", new Grad("Beograd")));

        zapisiXml(un);
        UN izDatoteke = ucitajXmlS(gradovi);
        for (Drzava a : izDatoteke.getDrzave()) {
            System.out.println(a);
        }

        UN neserijalizirano = ucitajXML(gradovi);
        for (Drzava a : neserijalizirano.getDrzave()) {
            System.out.println(a);
        }


    }
}
