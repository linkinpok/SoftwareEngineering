package ex05;

@Concept("Benutzer")
public class Benutzer {
	@Concept("Benutzername")
	String benutzerName;
	@Concept("IP")
	String ip;
	@Concept("Punkt")
	Punkt heimatOrt;
	@Concept("Gesperrt")
	boolean gesperrt;
}
