package ba.unsa.etf.rpr.tutorijal7;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tutorijal {

    public static void main(String[] args) {
	// write your code here
        Scanner ulaz=null;
        ArrayList<Grad> gradovi=new ArrayList<Grad>();
        try{
            ulaz=new Scanner(new FileReader("mjerenja.txt")).useDelimiter("[\\n\\r,]");
        }
        catch(IOException err){
            System.out.println("Datoteka izlaz.txt se ne može otvoriti");
        }

            while(ulaz.hasNext()){
                String naziv=ulaz.next();
                double[] niz=new double[1000];
                int i=0;
                try{
                    while (i<1000){
                        niz[i]=Double.parseDouble(ulaz.next());
                    }
                }
                catch(NumberFormatException e){
                    ulaz.nextLine();
                }
                gradovi.add(new Grad(naziv, 0, niz));
            }


    }
}
