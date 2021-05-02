package aiss.api.resources;

import java.util.Comparator;

import aiss.model.Playlist;

public class ComparatorNamePlaylistReversed implements Comparator<Playlist>{
	@Override
	public int compare(Playlist o1, Playlist o2) {
		// TODO Auto-generated method stub
		return o2.getName().toLowerCase().compareTo(o1.getName().toLowerCase());
	}

}
