package aiss.model.repository;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import aiss.model.Genre;

public class MapGenreRepository implements GenreRepository {
	Map<String,Genre> genreMap;
	private static MapGenreRepository instance = null;
	private int index=0;
	
	public static MapGenreRepository getInstance() {

		if (instance == null) {
			instance = new MapGenreRepository();
			instance.init();
		}
		return instance;
	}

	public void init() {
		genreMap = new HashMap<String,Genre>();
			Genre rol = new Genre();
			rol.setName("Rol");
			rol.setDescription("el jugador inicia una partida en la que escoge un equipo de "
					+ "personajes, cada uno con sus propias características y habilidades, expresadas"
					+ " en forma de datos estadísticos (salud, magia, ataque, defensa, sabiduría).");
			addGenre(rol);
			
			Genre deportes = new Genre();
			deportes.setName("Deportes");
			deportes.setDescription("Los videojuegos deportivos están basados o son una simulación"
					+ " virtual de las distintas disciplinas deportivas reales. ");
			addGenre(deportes);
			
			Genre carreras = new Genre();
			carreras.setName("Carreras");
			carreras.setDescription("Los videojuegos de carreras son aquellos en donde el jugador"
					+ " controla a un personaje o vehículo que compite en una carrera contra"
					+ " otros vehículos a lo largo de una pista. ");
			addGenre(carreras);
			
			Genre estrategia = new Genre();
			estrategia.setName("Estrategia");
			estrategia.setDescription("La forma de juego requiere el uso de un pensamiento táctico "
					+ "y la planificación de acciones para alcanzar la victoria.");
			addGenre(estrategia);
			
			Genre musica = new Genre();
			musica.setName("Música");
			musica.setDescription("Los videojuegos musicales se basan en la interacción del jugador"
					+ " con una pieza musical determinada, comúnmente siguiendo el ritmo de la "
					+ "música a tiempo real.");
			addGenre(musica);
		
		
	}
	

	@Override
	public void addGenre(Genre g) {
		String id = "g" + index++;
		g.setId(id);
		genreMap.put(id, g);
	}

	@Override
	public Collection<Genre> getAllGenre() {
		return genreMap.values();
	}

	@Override
	public Genre getGenre(String id) {
		return genreMap.get(id);
	}

	@Override
	public void updateGenre(Genre g) {
		genreMap.put(g.getId(), g);
		
	}

	@Override
	public void deleteGenre(String id) {
		genreMap.remove(id);
		
	}

	
}
