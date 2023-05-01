package Projekt1;


import java.util.ArrayList;
import java.util.Scanner;

public class AnimovanyFilm extends Film { 

private int hodnotenie;
private int vek;

public AnimovanyFilm(String meno, String reziser,int rok , int hodnotenie,String comment, int vek, ArrayList<String> zoznamHercov) {
    super("Animovaný film", meno, reziser, rok,comment, zoznamHercov);
    this.zoznamHercov=zoznamHercov;
    this.hodnotenie = hodnotenie;
    this.vek = vek;
}


public int getHodnotenie() {
    return hodnotenie;
}

@Override
public boolean setHodnotenie(int hodnotenie) {
    if (hodnotenie >= 1 && hodnotenie <= 10) {
        this.hodnotenie = (int) hodnotenie;
        return true;
    }
    return false;
}

public void setVek(int vek) {
    this.vek = vek;
}

public int getVek() {
    return vek;
}


@Override
public String toString() {
    return super.toString() + "\nAnimatori: " + zoznamHercov + "\nHodnotenie: " + hodnotenie +  "\nOdporuceny vek: " + vek;
}}