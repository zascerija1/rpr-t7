package ba.unsa.etf.rpr.tutorijal7;

import java.util.ArrayList;

public class UN {
    public ArrayList<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(ArrayList<Drzava> drzave) {
        this.drzave = drzave;
    }

    private ArrayList<Drzava> drzave;
    public UN(){
        drzave=new ArrayList<>();
    }
    public void dodajDrzavu(Drzava d){drzave.add(d);};
}
