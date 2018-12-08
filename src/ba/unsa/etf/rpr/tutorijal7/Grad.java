package ba.unsa.etf.rpr.tutorijal7;

import java.util.Arrays;

public class Grad {
    private String naziv;
    int brojStanovnika;
    double[] temperature;

    public Grad(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Grad{" +
                "naziv='" + naziv + '\'' +
                ", brojStanovnika=" + brojStanovnika +
                ", temperature=" + Arrays.toString(temperature) +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Grad a=(Grad)obj;
        return naziv.equals(a.naziv);
    }



    public Grad(String naziv, int brojStanovnika, double[] temperature) {
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.temperature = temperature;
    }

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

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }

    public Grad() {
        temperature=new double[1000];
    }
}
