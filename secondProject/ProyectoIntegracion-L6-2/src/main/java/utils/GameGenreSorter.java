package utils;

import java.util.Comparator;

import aiss.model.Game;
import aiss.model.Genre;

public class GameGenreSorter implements Comparator<Game> {

	@Override
	public int compare(Game o1, Game o2) {
		Genre g1 = new Genre();
		Genre g2 = new Genre();
		g1 = o1.getGenres().stream().sorted(Comparator.comparing(Genre::getName)).findFirst().get();
		g2 = o2.getGenres().stream().sorted(Comparator.comparing(Genre::getName)).findFirst().get();
		return g1.getName().toLowerCase().compareTo(g2.getName().toLowerCase());
	}

}
