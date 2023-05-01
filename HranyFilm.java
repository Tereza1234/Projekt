package Projekt1;


import java.util.ArrayList;
import java.util.Scanner;

public class  HranyFilm extends Film {
    private int hodnotenie;


    public HranyFilm(String meno, String reziser, int rok, int hodnotenie,String comment,ArrayList<String> zoznamHercov) {
        super("Hrany film", meno, reziser, rok,comment, zoznamHercov);
        
        this.hodnotenie = hodnotenie;
       //this.comment=comment;
    }

   
    public int getHodnotenie() {
        return hodnotenie;
    }
    @Override
    public boolean setHodnotenie(int hodnotenie) {
        if (hodnotenie >= 1 && hodnotenie <= 5) {
            this.hodnotenie = (int) hodnotenie;
            return true;
        }
        return false;
    }

    
  
    @Override
    public String toString() {
        return super.toString() + "\nHerci: " + zoznamHercov+ "\nHodnotenie: "+hodnotenie;
    }
    }


	

