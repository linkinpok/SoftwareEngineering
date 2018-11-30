package ex05;

import java.util.ArrayList;

@Concept("Route")
public class Route {
	@Concept("Weg")
	ArrayList<Weg> wege;
	@Concept("Laenge")
	double laenge;
	@Concept("Fahrzeit")
	double fahrZeit;
}
