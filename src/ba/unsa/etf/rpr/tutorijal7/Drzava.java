package ba.unsa.etf.rpr.tutorijal7;

public class Drzava {

    String naziv;
    int brojStanovnika;
    double povrsina;
    String jedinicaZaPvrsinu;
    Grad glavniGrad;
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public String getJedinicaZaPvrsinu() {
        return jedinicaZaPvrsinu;
    }

    public void setJedinicaZaPvrsinu(String jedinicaZaPvrsinu) {
        this.jedinicaZaPvrsinu = jedinicaZaPvrsinu;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

    public Drzava() {

    }
}
