package Projekt1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Animovanefilmy {
    public static Map<String, Film> filmMap;
	

    public Animovanefilmy() {
       filmMap = new HashMap<>();
    } public boolean setFilm(String druh, String meno, String reziser, int hodnotenie,String comment, int rok, int vek, ArrayList<String> zoznamHercov) {
        if (druh.equals("Hrany film")) {
            if (filmMap.put(meno, new HranyFilm(meno, reziser, rok, hodnotenie,comment,zoznamHercov)) == null) {
                Film film = filmMap.get(meno);
                film.setHodnotenie(hodnotenie);
                film.setComment(comment);
                return true;
            }
        } else if (druh.equals("Animovaný film")) {
            if (filmMap.put(meno, new AnimovanyFilm(meno, reziser, rok, hodnotenie,comment, vek, zoznamHercov)) == null) {
                Film film = filmMap.get(meno);
                film.setHodnotenie(hodnotenie);
               film.setComment(comment);
                return true;
            }
        }

        return false;
    }
    
  
    public void addFilm() {Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.println("Vyberte druh filmu (1 pre hraný film, 2 pre animovaný film):");
        int filmType = input.nextInt();
        input.nextLine(); // odstranění zbytku řádku

        System.out.println("Zadajte názov filmu:");
        String meno = input.nextLine();

        System.out.println("Zadajte režiséra filmu:");
        String reziser = input.nextLine();

        System.out.println("Zadajte rok vydania filmu:");
      //  int rok = input.nextInt();
        int rok=play.pouzeCelaCisla(sc);
       // input.nextLine(); // odstranění zbytku řádku

        Film film ;
		// String comment;
		//String reziser = null;
		if (filmType == 1) {
			ArrayList<String> zoznamHercov = new ArrayList<String>();
			System.out.println("Zadajte koľko animátorov chcete zadať: ");
			int pocet = play.pouzeCelaCisla(sc);
			
			//input.nextLine();
			System.out.println("Zadajte jednotlivých animátorov : ");										
			for (int i = 0; i < pocet; i++) {
				String herec = input.nextLine();
				zoznamHercov.add(herec);
			} 
			
			System.out.println("Chcete zanechať komentár? (ano/nie):");
            String choice= input.nextLine();
            String comment=null;
            if (choice.equalsIgnoreCase("ano")) {
                System.out.println("Zadajte komentár:");}
                comment = input.nextLine();
               
				//film = new HranyFilm(meno, reziser, rok, hodnotenie,comment, zoznamHercov);
                //film.setComment(comment);
            
            System.out.println("Zadajte hodnotenie divákov (1-5):");
            int hodnotenie = input.nextInt();
     
            input.nextLine();
            film = new HranyFilm(meno, reziser, rok, hodnotenie,comment, zoznamHercov);
            boolean success = ((HranyFilm) film).setHodnotenie(hodnotenie);
            if (!success) {
                System.out.println("Neplatné hodnotenie divákov. Hodnotenie musí byť v rozmedzí 1-5.");
                return; // Abort adding the film if the rating is invalid
            }
         
           
            
            film = new HranyFilm(meno, reziser,  rok, hodnotenie,comment, zoznamHercov);
            
        } else if (filmType == 2) {
        	ArrayList<String> zoznamHercov = new ArrayList<String>();
        	System.out.println("Zadajte koľko hercov chcete zadať: ");
			int pocet =play.pouzeCelaCisla(sc);// input.nextInt();
			
			//input.nextLine();
			System.out.println("Zadajte jednotlivých hercov: ");										
			for (int i = 0; i < pocet; i++) {
				String herec = input.nextLine();
				zoznamHercov.add(herec);
			} System.out.println("Zadajte doporučený vek diváka:");
            int vek = input.nextInt();
            input.nextLine(); // odstranění zbytku řádku
            System.out.println("Chcete zanechať komentár? (ano/nie):");
            String choice= input.nextLine();
            String comment=null;
            if (choice.equalsIgnoreCase("ano")) {
                System.out.println("Zadajte komentár:");}
                comment = input.nextLine();
                //((HranyFilm) film).setComment(comment);
            
           System.out.println("Zadajte hodnotenie divákov (1-10):");
            int hodnotenie = input.nextInt();
            input.nextLine();
            film = new AnimovanyFilm(meno, reziser, rok, hodnotenie,comment, vek, zoznamHercov);
            boolean success = ((AnimovanyFilm) film).setHodnotenie(hodnotenie);
            if (!success) {
                System.out.println("Neplatné hodnotenie divákov. Hodnotenie musí byť v rozmedzí 1-10.");
                return; // Abort adding the film if the rating is invalid
            } 
            
           

            film = (Film) new AnimovanyFilm(meno, reziser, rok, hodnotenie,comment, vek, zoznamHercov);
        } else {
            System.out.println("Neplatný výber typu filmu.");
            return;
        }

        filmMap.put(meno, film); // přidání filmu do HashMapy
        System.out.println("Film " + meno + " bol úspešne pridaný.");
    }

		
	public String editFilm() {
	   Scanner scanner = new Scanner(System.in); 
	        System.out.print("Zadajte meno filmu, ktorý chceš zmeniť: ");
	        String meno = scanner.nextLine();
	        Film film = getFilm(meno);

	        if (film == null) {
	            return "Film sa nenašiel.";
	        }

	        System.out.println("Čo chceš zmeniť?");
	        System.out.println("1. Meno");
	        System.out.println("2. Režisér");
	        System.out.println("3. Rok");
	        System.out.println("4. Hodnotenie");
	        System.out.println("5. Koment");
	        System.out.println("6. Odporúčaný vek");
	        System.out.print("Zadaj svoj výber: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine(); // consume the newline character

	        switch (choice) {
	            case 1:
	                System.out.print("Nové meno: ");
	                String newMeno = scanner.nextLine();
	                film.setMeno(newMeno);
	                filmMap.remove(meno); // remove old entry
	                filmMap.put(newMeno, film); // add updated entry
	                return "Vytvoril si nové meno.\n" + film.toString();
	            case 2:
	                System.out.print("Zadaj režiséra: ");
	                String newReziser = scanner.nextLine();
	                film.setReziser(newReziser);
	                return "Zmenil si režiséra.\n" + film.toString();
	            case 3:
	                System.out.print("Zadaj nový rok: ");
	                int newRok = scanner.nextInt();
	                film.setRok(newRok);
	                return "Zmenil si rok.\n" + film.toString();
	            case 4:
	                System.out.print("Zadaj nové hodnotenie: ");
	                int newHodnotenie = scanner.nextInt();
	                film.setHodnotenie(newHodnotenie);
	                return "Zmenil si hodnotenie.\n" + film.toString();
	            case 5:
	                System.out.print("Zadaj koment: ");
	                String newKoment = scanner.nextLine();
	                film.setComment(newKoment);
	                return "Zmenil si koment.\n" + film.toString();
	            case 6:
	                if (!(film instanceof AnimovanyFilm)) {
	                    return "Tento film nie je animovaný";
	                }
	                System.out.print("Zadaj nový odporúčaný vek: ");
	                int newVek = scanner.nextInt();
	                ((AnimovanyFilm) film).setVek(newVek);
	                return "Zmenil si odporúčaný vek.\n" + film.toString();
	            default:
	                return "Chyba.";
	        }
	    }
	
	

    public Film getFilm(String meno) {
        return filmMap.get(meno);
        
    }

    public boolean setHodnotenie(String meno, int hodnoceni) {
        Film film = filmMap.get(meno);
        if (film == null) {
            return false;
        }
        return film.setHodnotenie(hodnoceni);
    }
    

	public boolean vymazFilm(String meno)
	{
		if (filmMap.remove(meno)!=null)
			return true;
		return false;
	}

	public void vypisFilmov() {
	    for (Film film : filmMap.values()) {
	        System.out.println("Názov filmu: " + film.getMeno());
	        System.out.println("Režisér: " + film.getReziser());
	        
	        if (film instanceof HranyFilm) {
	            HranyFilm hranyFilm = (HranyFilm) film;
	            System.out.println("Zoznam hercov: " + hranyFilm.getZoznamHercov());
	        } else if (film instanceof AnimovanyFilm) {
	            AnimovanyFilm animovanyFilm = (AnimovanyFilm) film;
	            System.out.println("Zoznam animátorov: " + animovanyFilm.getZoznamHercov());
	            System.out.println("Doporučený vek diváka: " + animovanyFilm.getVek());
	        }
	        
	        System.out.println("Hodnotenie divákov: " + film.getHodnotenie());
	        System.out.println("Hodnotenie divákov-komentár: " + film.getComment());
	        System.out.println("Rok vydania: " + film.getRok());
	        System.out.println();
	    }
	}
	
	public static void HerecvJednomFilme (String jmeno) {
        try {
			for (Film film : filmMap.values()) {					
			    if (film.getZoznamHercov().contains(jmeno)) {
			        System.out.println(film.getMeno());
			    }
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
    }
	public static void HerecVoViacejFilmochMetoda() {
		HashMap<String, ArrayList<Film>> HerecVoViacejFilmoch = new HashMap<>();


		for (Film film : filmMap.values()) {

		for (String herec : film.getZoznamHercov()) {
		String meno = herec;

		if (HerecVoViacejFilmoch.containsKey(meno)) {
		ArrayList<Film> priradenie = HerecVoViacejFilmoch.get(meno);
		priradenie.add(film);
		HerecVoViacejFilmoch.put(meno, priradenie);
		} else {

		ArrayList<Film> priradenie = new ArrayList<>();
		priradenie.add(film);
		HerecVoViacejFilmoch.put(meno, priradenie);
		}
		}
		}


		for (String meno : HerecVoViacejFilmoch.keySet()) {
		ArrayList<Film> priradenie = HerecVoViacejFilmoch.get(meno);
		if (priradenie.size() > 1) {
		System.out.println("Herec: " + meno);
		System.out.println("Filmy:");
		for (Film film : priradenie) {
		System.out.println("- " + film.getMeno() + " (" + film.getRok() + ")");
		}
		}
		}
		}

		public static void HerecVoViacejFilmochMetoda2() {
		System.out.println("Zoznam hercov,animátorov, ktorý sa podielali na viacerých filmoch:");
		HerecVoViacejFilmochMetoda();
		}
	
	 

        public void ulozDoSuboru(String meno) {
        Film film = filmMap.get(meno);
        if (film != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(meno + ".txt"))) {
                writer.write("Druh: " + film.getDruh());
                writer.newLine();
                writer.write("Meno: " + film.getMeno());
                writer.newLine();
                writer.write("Režisér: " + film.getReziser());
                writer.newLine();
                writer.write("Rok: " + film.getRok());
                writer.newLine();
                writer.write("Hodnotenie: " + film.getHodnotenie());
                writer.newLine();
                writer.write("Hodnotenie-komentár: " + film.getComment());
                writer.newLine();
                if (film instanceof AnimovanyFilm) {
                    AnimovanyFilm animovanyFilm = (AnimovanyFilm) film;
                    writer.write("Odporúčaný vek: " + animovanyFilm.getVek());
                    writer.newLine();
                }
                //writer.write("Seznam osob:");
                writer.newLine();
                for (String herec : film.getZoznamHercov()) {
                    writer.write(herec);
                    writer.newLine();
                }
                System.out.println("Film " + meno + " bol uloženy do súboru.");
            } catch (IOException e) {
                System.out.println("Chyba pri ukladaní do súboru.");
            }
        } else {
            System.out.println("Film s menom " + meno + " neexistuje.");
        }
    }
        
        
	
        

  public static Film nacitajZoSuboru(String meno) {
    try (BufferedReader reader = new BufferedReader(new FileReader(meno + ".txt"))) {
        String druh = reader.readLine().substring(6);
        String nazov = reader.readLine().substring(6);
        String reziser = reader.readLine().substring(9);
        int rok = Integer.parseInt(reader.readLine().substring(5));
        int hodnotenie = Integer.parseInt(reader.readLine().substring(12));
        String comment = reader.readLine().substring(8);
        Film film;
        if (druh.equals("Animovaný film")) {
            int vek = Integer.parseInt(reader.readLine().substring(14));
            film = new AnimovanyFilm(nazov, reziser, rok, hodnotenie,comment, vek, new ArrayList<>());
        } else {
            film = new HranyFilm(nazov, reziser, rok, hodnotenie,comment, new ArrayList<>());
        }

        String line;
        ArrayList<String> zoznamHercov = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            zoznamHercov.add(line);
        }
        film.getZoznamHercov().addAll(zoznamHercov);
        filmMap.put(meno, film);

        return film;
    } catch (IOException e) {
        System.out.println("Chyba pri čítaní zo súboru.");
    }
    return null;
}




public static void insertFilm(Connection conn)  throws SQLException {
	
	for(Film film : filmMap.values()) {
		if(film instanceof HranyFilm) {
    String sql = "INSERT INTO movies (druh, meno, reziser,rok,hodnotenie,comment) VALUES (?, ?, ?, ?, ?,?)";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, "hranyFilm");
    stmt.setString(2, film.getMeno());
    stmt.setString(3, film.getReziser());
    stmt.setInt(4, film.getRok());
    stmt.setInt(5,film.getHodnotenie());
    stmt.setString(6,film.getComment());
    stmt.executeUpdate();
   for (String herec: film.getZoznamHercov()) {
    	sql="INSERT INTO herci(meno,herec) VALUES (?,?)";
    	stmt = conn.prepareStatement(sql);
    	stmt.setString(1,film.getMeno());
    	stmt.setString(2,herec);
	    stmt.executeUpdate();

}
		}
		
		else if (film instanceof AnimovanyFilm){   String sql ="INSERT INTO movies (druh, meno, reziser,  rok,hodnotenie,comment,vek)"+" VALUES (?, ?, ?, ?, ?,?,?)";				
		PreparedStatement stmt = conn.prepareStatement(sql);
		 stmt.setString(1, "animovanyFilm");
		    stmt.setString(2, film.getMeno());
		    stmt.setString(3, film.getReziser());
		     stmt.setInt(4, film.getRok());
		    stmt.setInt(5,film.getHodnotenie());
		    stmt.setString(6,film.getComment());
		    stmt.setInt(7, ((AnimovanyFilm) film).getVek());
		    stmt.executeUpdate();
		    for (String herec: film.zoznamHercov) {
		    	sql="INSERT INTO herci(meno,herec) VALUES (?,?)";
		    	stmt = conn.prepareStatement(sql);
		    	stmt.setString(1,film.getMeno());
		    	stmt.setString(2,herec);
			    stmt.executeUpdate();
			    
		    }}}}

        


	    public static void loadMovie(Connection conn)throws SQLException{
	    	
				Statement stmt=conn.createStatement();
				String query="SELECT * FROM movies";
				ResultSet rs=stmt.executeQuery(query);
				while(rs.next()) {
				if (rs.getString("druh").equals("hranyFilm")) {
				String meno = rs.getString("meno");
				
				ArrayList <String>zoznam = uploadZoznam(meno,conn);
				
				Film film = new HranyFilm(rs.getString("meno"), rs.getString("reziser"),rs.getInt("rok"),rs.getInt("hodnotenie"),rs.getString("comment"),zoznam);
				Statement state = conn.createStatement(); 
				
		                filmMap.put(meno, film);
				
				
				

				}else {
				String meno=rs.getString("meno");
				ArrayList<String>zoznam= uploadZoznam(meno,conn);
				Film film = new AnimovanyFilm(rs.getString("meno"), rs.getString("reziser"),rs.getInt("rok"),rs.getInt("hodnotenie"),rs.getString("comment"),rs.getInt("vek"),zoznam);
				filmMap.put(meno, film);
				Statement state = conn.createStatement();
				
                filmMap.put(meno, film);
				
				}}}
	    
	    
     
      
				static  ArrayList<String> uploadZoznam(String meno,Connection conn) throws SQLException { 
				ArrayList<String> zoznam= new ArrayList<String>();
				
				Statement stmt = conn.createStatement();
				ResultSet herec = stmt.executeQuery("SELECT * FROM herci WHERE meno = '"+meno+"'");
				while (herec.next()) {
				zoznam.add(herec.getString("meno")); 
				
	                
						//filmMap.put(meno, film);
				
				}
				return zoznam;
				
			
			
		}
		    
	    }
                
    
	

	

