package PRACTICA_4_Tecnica_Voraç;

import java.util.Scanner;

public class Main {
	/*
	 * Quins sen els candidats del problema?
	 *  Totes les gasolineres del recorregut
	 * 
	 * Quina funcie de seleccie aplicare el vostre algorisme?
	 *  El viatjant ha de repostar sempre que al diposit no li quedin mes quilemetres per arribar a la segeent gasolinera 
	 * 
	 * La vostra funcie de seleccie, garanteix trobar sempre la millor solucie?
	 *  Si
	 * 
	 * Perque?
	 *  L'enunciat garanteix que les distancies a les gasolineres son sempre mes petites que la capacitat
	 *  del dipesit i la funcie de seleccie fa que no s'aturi si pot arribar a la segeent gasolinera
	 * 
	 */
	
	public static void main(String[] args) {
		System.out.println("Main - Tecnica Vorae - viatjant");

		String ciutat_origen = "";
		String ciutat_desti = "";
		int desti = 0;
		int tamany_diposit = 0;
		int numero_gasolineres = 0;
		Gas gasolineres[] = null;
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Mene:");
		System.out.println("  1 - Aleatori");
		System.out.println("  2 - Predefinit");
		System.out.println("  3 - Manual");
		System.out.println("Tria una opcie:");
		String opcio = reader.next();
		opcio = opcio.toLowerCase();


		if(opcio.equals("1")) {
			System.out.println("1 - Aleatori");

			ciutat_origen = "Barcelona";
			ciutat_desti = "Matare";
			tamany_diposit = 100+((int)(Math.random()*100)+1);

			numero_gasolineres = 1+(int)(Math.random()*10);
			Gas gasolineresAuto[] = new Gas[numero_gasolineres+1];
			gasolineresAuto[0] = new Gas("Sortida",0);
			for(int i=1; i<=numero_gasolineres;i++)
				gasolineresAuto[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)),(int)(Math.random()*tamany_diposit)+1);

			gasolineres = gasolineresAuto;
			numero_gasolineres = gasolineres.length;
			
			for(Gas g: gasolineres)
				desti += g.km;
			
			desti += (int)(Math.random()*tamany_diposit)+1;
		}
		
		if(opcio.equals("2")) {
			System.out.println("2 - Predefinit");

			ciutat_origen = "Barcelona";
			ciutat_desti = "Matare";
			tamany_diposit = 50;

			Gas gasolineresPredefinit[] = {
					new Gas("Sortida",0),
					new Gas("Gas A", 40),
					new Gas("Gas B", 20),
					new Gas("Gas C", 10),
					new Gas("Gas D", 20)
					};				
			gasolineres = gasolineresPredefinit;
			numero_gasolineres = gasolineres.length;
			
			for(Gas g: gasolineres)
				desti += g.km;
			
			desti += 10;
		}		

		if(opcio.equals("3")) {
			System.out.println("3 - Manual");
		}

		while(ciutat_origen == "") {
			System.out.println("Entra la ciutat origen:");
			ciutat_origen = reader.next();
		} ;

		while(ciutat_desti == "") {
			System.out.println("Entra la ciutat deste:");
			ciutat_desti = reader.next();
		} ;
		
		while(tamany_diposit == 0) {
			System.out.println("Entra el tamany del dipesit:");
			String value = reader.next();
			try{
				tamany_diposit = Integer.parseInt(value);
			} catch(NumberFormatException e) {
				System.out.println("Error: El tamany del dipesit ha de ser numeric");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		};

		while(numero_gasolineres == 0) {
			System.out.println("Entra la quantitat de gasolineres al recorregut:");
			String value = reader.next();
			try{
				numero_gasolineres = Integer.parseInt(value);
			} catch(NumberFormatException e) {
				System.out.println("Error: La quantitat de gasolineres ha de ser numerica");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} ;

		if(gasolineres == null) {
			gasolineres = new Gas[numero_gasolineres+1];
			gasolineres[0] = new Gas("Sortida",0);
			for(int i=1; i<numero_gasolineres+1;i++){
				do {
					System.out.println(String.format("Entra la distancia a la gasolinera %s:", i));
					String value = reader.next();
					try{
						int km = Integer.parseInt(value);
						if(km>tamany_diposit)
							throw new Exception(String.format("La distancia no pot ser superior a tamany diposit (%d)", tamany_diposit));
						if(km<1)
							throw new Exception("Error: La distancia no pot ser inferior a 1");
						gasolineres[i] = new Gas("Gas "+ Character.toString ((char) ('A'+i-1)), km);
					} catch(NumberFormatException e) {
						System.out.println("Error: La distancia ha de ser numerica");
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} while(gasolineres[i] == null);
			}

			int acum = 0;
			for(int i=0; i<gasolineres.length; i++) {
				acum += gasolineres[i].km;
			}
			
			System.out.println("Entra la distancia al deste:");
			String value = reader.next();
			try{
				int km = Integer.parseInt(value);
				if(km>tamany_diposit)
					throw new Exception(String.format("La distancia no pot ser superior a tamany diposit (%d)", tamany_diposit));
				if(km<1)
					throw new Exception("Error: La distancia no pot ser inferior a 1");
				desti = acum + km;
			} catch(NumberFormatException e) {
				System.out.println("Error: La distancia ha de ser numerica");
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

		}
		
		//Parades posibles
		Gas parades[] = new Gas[gasolineres.length];

		System.out.println("\n---Dades-------------");
		System.out.println("Ciutat origen: "+ ciutat_origen);
		System.out.println("Ciutat deste: "+ ciutat_desti);
		System.out.println("Distancia al deste: "+ desti +" km");
		
		int resultat;
		int distanciaDeposit = tamany_diposit;
		System.out.println("Tamany depesit: "+ distanciaDeposit +" km");

		int acum = 0;
		int delta = 0;
		for(int i=0; i<gasolineres.length; i++) {
			delta= gasolineres[i].km;
			acum += delta;
			System.out.println(i+" - "+ gasolineres[i].nom 
					+"\t acum:"+ acum +" km"
					+"\t a "+ gasolineres[i].km +" km"
					);
		}
		System.out.println(gasolineres.length +" - Desti"
				+"\t acum:"+ desti +" km"
				+"\ta "+ (desti-acum) +" km"
				);
		
		System.out.println("\n---Celcul amb tecnica vorae -------------");
		resultat=calcula_aturades(distanciaDeposit, gasolineres, parades, desti);
		
		System.out.println("\n---Resultat-------------");
		if (resultat!=0){
			System.out.println("Aturades: "+ (resultat-1) +" de "+ gasolineres.length);
			for(int i=1; i<resultat; i++)
				System.out.println(i+" - "+ parades[i].nom);
		} else 
			System.out.println("No existeix solucio");
	}
	
	
	private static int calcula_aturades(int kmDiposit, Gas Candidats[], Gas Solucio[], int desti){
			int resultat=0;
			int kmAcumulats = 0;
			// Els candidats venen en seqeencia del recorregut
			int index = 0; // endex del candidat a considerar
			
			// Omplir dipesit
			int diposit = kmDiposit;
			
			System.out.println(index+" - "+ Candidats[index].nom 
					+"\tacum:"+ kmAcumulats +" km"
					+"\tdiposit: queden "+ diposit +" km"
					+"\tsegeent a "+ Candidats[index+1].km +" km" );			

			Solucio[resultat++] = Candidats[index++];	// afegir la gasolinera de sortida a la solucie

			// Estructura vorae
			while (index < Candidats.length) { // mentre hi hagi candidats
				
				int distancia_actual = Candidats[index].km;
				int distancia_seguent = 0;

				// descomptem la distancia actual al dipesit
				diposit -= distancia_actual;

				// Acumulem la distencia actual
				kmAcumulats += distancia_actual;

				if(index == Candidats.length-1) // Cas especial ultima gasolinera
					distancia_seguent = desti-kmAcumulats;
				else
					distancia_seguent = Candidats[index+1].km;
				
				System.out.print(index+" - "+ Candidats[index].nom 
						+"\tacum:"+ kmAcumulats +" km"
						+"\tdiposit: queden "+ diposit +" km"
						+"\tsegeent a "+ distancia_seguent +" km" );

				// funcie de seleccie: 
				//	 Si el dipesit no te prou gasolina per arribar a la segeent gasolinera haig d'aturar i omplir el dipesit
				if (diposit < distancia_seguent ){
					System.out.print(" - ATURADA");
					// Omplir dipesit
					diposit = kmDiposit;
					// Afegir a la solucie
					Solucio[resultat++] = Candidats[index];
				} else {
					System.out.print(" - sense aturada");
				}
				
				index++;
				System.out.println("");
			} 

			// eltim tram fins al deste
			int distancia_actual = desti-kmAcumulats;
			
			// descomptem la distancia actual al dipesit
			diposit -= distancia_actual;

			// Acumulem la distencia actual
			kmAcumulats += distancia_actual;
			
			System.out.print(index+" - Deste" 
					+"\tacum:"+ kmAcumulats +" km"
					+"\tdiposit: queden "+ diposit +" km"
					);
			
			return resultat;
	} 


}


