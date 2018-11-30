package ex05;

@Concept("Anfrage")
public class Anfrage {
	@Concept("Punkt")
	Punkt startPunkt, zielPunkt;
	@Concept("Route")
	Route route;
}
