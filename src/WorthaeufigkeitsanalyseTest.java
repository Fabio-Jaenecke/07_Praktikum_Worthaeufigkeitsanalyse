import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	private String testwort;
	private Worthaeufigkeitsanalyse worthaeufigkeitsanalyse;
	
	
	@BeforeEach
	void setUp()  {
		testwort = "Test Wort Dings!!! Test";
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
		worthaeufigkeitsanalyse.verarbeiteText(testwort);
		// Holt die HashMap woerterHaeufigkeit von der worthaeufigkeitsanalyse
		HashMap<String, Integer> woerterHaeufigkeit = worthaeufigkeitsanalyse.gibWoerterHaeufigkeit();
		// Testet toLowerCase
		for (Entry<String, Integer> eintrag: woerterHaeufigkeit.entrySet()) {
			String wort = eintrag.getKey();
			// Testet toLowerCase
			assertTrue(wort.equals(wort.toLowerCase()));
			// Testet, dass woerterHaeufigkeit keine leere Strings enthält
			assertTrue(!wort.isEmpty());
			// Testet, dass kein wort den Wert 0 oder tiefer hat
			assertTrue(!(eintrag.getValue()<1) );
		
		}
		// Testet, dass die Häufigkeit von jedem Wort genau erfasst wird
		for (Entry<String, Integer> eintrag: woerterHaeufigkeit.entrySet()) {
			if (eintrag.getKey().equals("test")) {
				assertTrue(eintrag.getValue() == 2);
			}
		}
		
	}
	
	/**
	 * Test method for {@link Worthaeufigkeitsanalyse#entferneSatzzeichen(java.lang.String)}.
	 */
	@Test
	void testEntferneSatzzeichenAmAnfangUndAmEnde() {
		String testZeichen = "testwort";
		for	(String zeichen : Worthaeufigkeitsanalyse.satzzeichen) {
			// Testet, dass die gewählten Satzzeichen korrekt am Anfang und am Ende eines Wortes entfernt werden
			String testMitZeichen = zeichen + testZeichen + zeichen;
			assertEquals(testZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
			// Testet, dass auch mehrere Satzzeichen am Anfang und am Ende eines Wortes entfernt werden
			testMitZeichen = zeichen + zeichen + testZeichen + zeichen + zeichen ;
			assertEquals(testZeichen, worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
		}
	}
	
	/**
	 * Test method for {@link Worthaeufigkeitsanalyse#entferneSatzzeichen(java.lang.String)}.
	 */
	@Test
	void testEntferneSatzzeichenImWort() {
		for	(String zeichen : Worthaeufigkeitsanalyse.satzzeichen) {
			// Testet, dass Zeichen innerhalb eines Wortes nicht entfernt werden
			String testMitZeichen = "test" + zeichen + "wort";
			assertEquals("test" + zeichen + "wort", worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
			// Testet, dass auch mehrere Zeichen innerhalb eines Wortes nicht entfernt werden
			testMitZeichen = "test" + zeichen + zeichen + "wort";
			assertEquals("test" + zeichen + zeichen + "wort", worthaeufigkeitsanalyse.entferneSatzzeichen(testMitZeichen));
		}
	}
}