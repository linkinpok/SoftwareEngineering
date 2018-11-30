package ex05;

import java.util.ArrayList;

@Concept("Moderator")
public class Moderator extends Benutzer{
	@Concept("Recht")
	ArrayList<String> sonderRechte;
	@Concept("Zustaendigkeitsbereich")
	Flaeche zustaendigkeitsBereich;
}
