import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author daniellerch
 *
 */
class WorthaeufigkeitsanalyseTest {

	/**
	 * @throws java.lang.Exception
	 */
	
	private String[] testTexte;
	private Worthaeufigkeitsanalyse worthaeufigkeitsanalyse;
    private Random zufallsgenerator = new Random(); 
	
	
	@BeforeEach
	void setUp()  {
		testTexte = new String[]{
				//initial
				"Test Wort Dings!!! Test",      
				//Gültige Äquivalenzklassen
				"Fritz sagt\" die", 			//Äquivalenzklasse 1
				"Hans ?mEInt ,ER",  			//Äquivalenzklasse 1
				"JOHN ?,fuegt hinzu",  			//Äquivalenzklasse 2
				"peter findet\". DaSs", 		//Äquivalenzklasse 2
				"sUsI :schaut! dort",			//Äquivalenzklasse 3
				"Fritz \"HeBt;: das",			//Äquivalenzklasse 3
				"NAJiS .F!I?N;D:E,(T) , \" DEN",//Äquivalenzklasse 4
				"frITz ;,z?!e\",g;?t:! dort",	//Äquivalenzklasse 4
				".,?!\":;",						//Äquivalenzklasse 5
				//Ungültige Äquivalenzklassen
				"",								//Äquivalenzklasse 6
				"sagen",						//Äquivalenzklasse 6
				"0",							//Äquivalenzklasse 6
				"Maja hört zu",					//Äquivalenzklasse 6
				"Sunita sagt% dazu",			//Äquivalenzklasse 7
				"Lorem %ipsum oder",			//Äquivalenzklasse 7
				"John i%st hier",				//Äquivalenzklasse 8
				"Jul ge?ht davon",				//Äquivalenzklasse 8
				};
		worthaeufigkeitsanalyse = new Worthaeufigkeitsanalyse();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Worthaeufigkeitsanalyse#verarbeiteText(java.lang.String)}.
	 */
	@Test
	void testVerarbeiteText() {
		int i = 1;
		for (String einText : testTexte) {
			worthaeufigkeitsanalyse.verarbeiteText(einText);
			// Holt die HashMap woerterHaeufigkeit von der worthaeufigkeitsanalyse
			HashMap<String, Integer> woerterHaeufigkeit = worthaeufigkeitsanalyse.gibWoerterHaeufigkeit();
			// Gibt die bisherigen Resultate pro Text aus
			System.out.println("\n" + "Verarbeitung von Text Nr. " + i + ":");
			i++;
			// Testet toLowerCase
			for (Entry<String, Integer> eintrag: woerterHaeufigkeit.entrySet()) {
				String wort = eintrag.getKey();
				// Testet toLowerCase
				assertTrue(wort.equals(wort.toLowerCase()));
				// Testet, dass woerterHaeufigkeit keine leere Strings enthält
				assertTrue(!wort.isEmpty()); //== assertFalse(wort.isEmpty());
				// Testet, dass kein wort den Wert 0 oder tiefer hat
				assertTrue((eintrag.getValue()>=1) );
				//Prints jeden Eintrag

				System.out.println(eintrag);
				
			}
			
			// Testet, dass die Häufigkeit von jedem Wort genau erfasst wird
			for (Entry<String, Integer> eintrag: woerterHaeufigkeit.entrySet()) {
				if (eintrag.getKey().equals("test")) {
					assertTrue(eintrag.getValue() == 2);
				}
			}
		}
		
	}
	
	/**
	 * Test method for {@link Worthaeufigkeitsanalyse#entferneSatzzeichen(java.lang.String)}.
	 */
	@Test
	void testEntferneSatzzeichenAmAnfangUndAmEnde() {
		int j = 1;
		for (String einText : testTexte) {
			System.out.println("\n\n" + "Zeichenentfernung aller Wörter in Text Nr. " + j + ":");
			j++;
			String[] woerter = einText.split(" ");
			for (int i = 0; i < woerter.length; i++) {
				String testZeichen = woerter[i].trim();
				testZeichen = testZeichen.toLowerCase();
				String testOhneZeichen = worthaeufigkeitsanalyse.entferneSatzzeichen(testZeichen);
				assertEquals(testOhneZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testZeichen));
				System.out.println();
				System.out.printf("\n%-20.20s %10s %10s", testZeichen, "->", testOhneZeichen);
				System.out.println();
				// Fügt zufällige gültige Satzzeichen hinzu
				for	(int p = 0; p < worthaeufigkeitsanalyse.gibAlleSatzZeichen().length; p++) {
					// Testet, dass die gewählten Satzzeichen korrekt am Anfang und am Ende eines Wortes entfernt werden.
					int k = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					int u = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					String testMitZeichen = worthaeufigkeitsanalyse.gibAlleSatzZeichen()[k] + testOhneZeichen  + worthaeufigkeitsanalyse.gibAlleSatzZeichen()[u];
					assertEquals(testOhneZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
					System.out.printf("\n%-20.20s %10s %10s", testMitZeichen, "->", testOhneZeichen);
					// Testet, dass auch mehrere Satzzeichen am Anfang und am Ende eines Wortes entfernt werde
					k = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					u = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					int r = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					int s = zufallsgenerator.nextInt(worthaeufigkeitsanalyse.gibAlleSatzZeichen().length);
					testMitZeichen = worthaeufigkeitsanalyse.gibAlleSatzZeichen()[k] + worthaeufigkeitsanalyse.gibAlleSatzZeichen()[u] + testOhneZeichen + worthaeufigkeitsanalyse.gibAlleSatzZeichen()[r] + worthaeufigkeitsanalyse.gibAlleSatzZeichen()[s];
					assertEquals(testOhneZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
					System.out.printf("\n%-20.20s %10s %10s", testMitZeichen,  "->", testOhneZeichen);
				}
			}
		} System.out.println();
	}
	
	/**
	 * Test method for {@link Worthaeufigkeitsanalyse#entferneSatzzeichen(java.lang.String)}.
	 */
	@Test
	void testEntferneSatzzeichenImWort() {
		System.out.println();
		System.out.println("Test für Satzzeichen innerhalb eines Wortes: ");
		System.out.println();
		for	(String zeichen : Worthaeufigkeitsanalyse.satzzeichen) {
			// Testet, dass Zeichen innerhalb eines Wortes nicht entfernt werden
			String testMitZeichen = "test" + zeichen + "wort";
			assertEquals(testMitZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
			System.out.println(zeichen + "\t -> \t" + testMitZeichen);
			// Testet, dass auch mehrere Zeichen innerhalb eines Wortes nicht entfernt werden
			testMitZeichen = "test" + zeichen + zeichen + "wort";
			assertEquals(testMitZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
			System.out.println(zeichen + "\t -> \t" + testMitZeichen);
		}
	}
}