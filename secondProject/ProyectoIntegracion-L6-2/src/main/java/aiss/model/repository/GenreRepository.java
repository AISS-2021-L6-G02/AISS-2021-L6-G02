package aiss.model.repository;

import java.util.Collection;

import aiss.model.Genre;

public interface GenreRepository {
	// Genre
		public void addGenre(Genre g);
		public Collection<Genre> getAllGenre();
		public Genre getGenre(String id);
		public void updateGenre(Genre g);
		public void deleteGenre(String id);
		
}
