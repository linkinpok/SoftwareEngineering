package ex05;

import java.util.ArrayList;

@Concept("Geoobjekt")
public class Geoobjekt {
	@Concept("Identifikationsnummer")
	int identifikationsNummer;
	@Concept("Eigenschaft")
	ArrayList<Eigenschaft> eigenschaften;
	@Concept("Benutzer")
	Benutzer letzterBearbeiter;
	@Concept("Bearbeitungszeitpunkt")
	String letzteBearbeitungszeitpunk;
}
