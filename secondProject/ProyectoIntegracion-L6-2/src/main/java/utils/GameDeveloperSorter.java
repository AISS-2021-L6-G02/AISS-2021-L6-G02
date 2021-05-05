package utils;

import java.util.Comparator;

import aiss.model.Game;

public class GameDeveloperSorter implements Comparator<Game>{

	@Override
	public int compare(Game o1, Game o2) {
		return o1.getDeveloper().getName().toLowerCase().compareTo(o2.getDeveloper().getName().toLowerCase());
	}

}
