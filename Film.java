package Projekt1;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Film {
    protected String druh;
    protected String meno;
    protected String reziser;
    protected int rok;
    protected int hodnotenie;
    protected String comment;
    protected ArrayList<String> zoznamHercov;

    public Film(String druh, String meno, String reziser, int rok,String comment, ArrayList<String> zoznamHercov) {
        this.druh = druh;
        this.meno = meno;
        this.reziser = reziser;
        this.rok = rok;
        this.comment=comment;
        this.zoznamHercov = zoznamHercov;
    }

    public String getDruh() {
        return druh;
    }

    public String getMeno() {
        return meno;
    }

    public ArrayList<String> getZoznamHercov() {
        return zoznamHercov;
    }

    public String getReziser() {
        return reziser;
    }

    public String getComment() {
    	return comment;
    }
    public int getRok() {
        return rok;
    }

    public int getHodnotenie() {
        return hodnotenie;
    }

    public boolean setHodnotenie(int hodnotenie) {
        if (hodnotenie < 1 || hodnotenie > 10) {
            System.out.println("Chybny priemer");
            return false;
        }
        this.hodnotenie = hodnotenie;
        return true;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public void setReziser(String reziser) {
        this.reziser = reziser;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }
    public void setComment(String comment) {
    	this.comment=comment;
    }

    public void promptForComment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Chcete zanechat komentár? (ano/nie):");
        String choice = input.nextLine();

        if (choice.equalsIgnoreCase("ano")) {
            System.out.println("Zadajte komentár:");
            String comment = input.nextLine();
            setComment(comment);
        }
    }
    @Override
    public String toString() {
        return "Druh: " + druh + "\nMeno: " + meno + "\nReziser: " + reziser + "\nRok: " + rok+ "\nKoment: " + comment ;
    }

	
	
}







