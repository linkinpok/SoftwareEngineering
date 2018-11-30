package ex05;

import java.util.ArrayList;

@Concept("Weg")
public class Weg extends Geoobjekt{
	@Concept("Punkt")
	ArrayList<Punkt> punkte;
}
