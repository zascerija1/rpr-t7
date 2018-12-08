package ba.unsa.etf.rpr.tutorijal7;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public static ArrayList<Grad> ucitajGradove() {
        Scanner ulaz=null;
        ArrayList<Grad> gradovi=new ArrayList<Grad>();
        try{
            ulaz=new Scanner(new FileReader("mjerenja.txt")).useDelimiter("[, \\n\\r]");

        }
        catch(IOException err){
            System.out.println("Datoteka mjerenja.txt se ne može otvoriti");
        }

        while(ulaz.hasNext()){
            String naziv=ulaz.next();
            double[] niz=new double[1000];
           int i=0;
            try{

                while (i<1000 && ulaz.hasNextDouble()){
                    niz[i]=ulaz.nextDouble();
                    i++;
                }
                if(ulaz.hasNext())  ulaz.nextLine();
            }
            catch(NumberFormatException e){
            }
            gradovi.add(new Grad(naziv, 0, niz));
        }
        ulaz.close();
        return gradovi;
    }
    public static UN ucitajXml (ArrayList<Grad> gradovi){
        UN un=new UN();

        try {
            XMLDecoder ulaz = new XMLDecoder(new FileInputStream("UN.xml"));
            un = (UN) ulaz.readObject();
            ulaz.close();
        } catch(Exception e) {
            System.out.println("Greška: "+e);
        }

        for(Drzava a: un.getDrzave()){
            for(int i=0;i<gradovi.size();i++){
                if(a.getGlavniGrad().equals(gradovi.get(i))) {
                a.getGlavniGrad().setTemperature(gradovi.get(i).getTemperature());
                }
            }
        }
        return un;
    }

    public static void zapisiXml(UN un){

            try {
                XMLEncoder izlaz = new XMLEncoder(new FileOutputStream("UN.xml"));

                izlaz.writeObject(un);
                izlaz.close();
            } catch(Exception e) {
                System.out.println("Greška: "+e);
            }
        }

    public static void main(String[] args) {
	// write your code here
 ArrayList<Grad> gradovi=ucitajGradove();

        UN un=new UN();
        un.dodajDrzavu(new Drzava("Bosna i Hercegovina", 3000000,51000, "km^2",new Grad("Sarajevo")));
        un.dodajDrzavu(new Drzava("Hrvatska", 7000000,55000, "km^2",new Grad("Zagreb")));
        un.dodajDrzavu(new Drzava("Srbija", 8000000,71000, "km^2",new Grad("Beograd")));

 zapisiXml(un);
 UN izDatoteke=ucitajXml(gradovi);
        for(Drzava a: izDatoteke.getDrzave()){
            System.out.println(a);
        }


    }
}
