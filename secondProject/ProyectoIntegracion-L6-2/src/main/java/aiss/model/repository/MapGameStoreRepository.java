package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Game;
import aiss.model.ObjetoStore;
import aiss.model.Store;



public class MapGameStoreRepository implements GameStoreRepository{

	Map<String,Store> storeMap;
	private int index=0;
	@Override
	public void addGame(Game g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Game> getAllSongs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game getGame(String gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGame(Game g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGame(String gameId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addStore(Store s) {
		// TODO Auto-generated method stub
		String id = "shop"+index++;
		s.setId(id);
		storeMap.put(s.getId(), s);
		
	}

	@Override
	public Collection<Store> getAllStores() {
		// TODO Auto-generated method stub
		return storeMap.values();
	}

	@Override
	public Store getStore(String storeId) {
		// TODO Auto-generated method stub
		return storeMap.get(storeId);
	}

	@Override
	public void updateStore(Store s) {
		// TODO Auto-generated method stub
		storeMap.put(s.getId(), s);
	}

	@Override
	public void deleteStore(String storeId) {
		// TODO Auto-generated method stub
		storeMap.remove(storeId);
	}

	@Override
	public Collection<ObjetoStore> getAllObjects(String storeId) {
		// TODO Auto-generated method stub
		return storeMap.get(storeId).getGames();
	}

	@Override
	public void addObjeto(String storeId, ObjetoStore o) {
		// TODO Auto-generated method stub
		String id="o"+index++;
		o.setId(id);
		storeMap.get(storeId).addGame(o);
		
	}

	@Override
	public void deleteObjeto(String storeId, String objectId) {
		// TODO Auto-generated method stub
		
	}

//	Map<String, Playlist> playlistMap;
//	Map<String, Song> songMap;
//	private static MapPlaylistRepository instance=null;
//	private int index=0;			// Index to create playlists and songs' identifiers.
//	
//	
//	public static MapPlaylistRepository getInstance() {
//		
//		if (instance==null) {
//			instance = new MapPlaylistRepository();
//			instance.init();
//		}
//		
//		return instance;
//	}
//	
//	public void init() {
//		
//		playlistMap = new HashMap<String,Playlist>();
//		songMap = new HashMap<String,Song>();
//		
//		// Create songs
//		Song rollingInTheDeep=new Song();
//		rollingInTheDeep.setTitle("Rolling in the Deep");
//		rollingInTheDeep.setArtist("Adele");
//		rollingInTheDeep.setYear("2011");
//		rollingInTheDeep.setAlbum("21");
//		addSong(rollingInTheDeep);
//		
//		Song one=new Song();
//		one.setTitle("One");
//		one.setArtist("U2");
//		one.setYear("1992");
//		one.setAlbum("Achtung Baby");
//		addSong(one);
//		
//		Song losingMyReligion=new Song();
//		losingMyReligion.setTitle("Losing my Religion");
//		losingMyReligion.setArtist("REM");
//		losingMyReligion.setYear("1991");
//		losingMyReligion.setAlbum("Out of Time");
//		addSong(losingMyReligion);
//		
//		Song smellLikeTeenSpirit=new Song();
//		smellLikeTeenSpirit.setTitle("Smell Like Teen Spirit");
//		smellLikeTeenSpirit.setArtist("Nirvana");
//		smellLikeTeenSpirit.setAlbum("Nevermind");
//		smellLikeTeenSpirit.setYear("1991");
//		addSong(smellLikeTeenSpirit);
//		
//		Song gotye=new Song();
//		gotye.setTitle("Someone that I used to know");
//		gotye.setArtist("Gotye");
//		gotye.setYear("2011");
//		gotye.setAlbum("Making Mirrors");
//		addSong(gotye);
//		
//		// Create playlists
//		Playlist japlaylist=new Playlist();
//		japlaylist.setName("AISSPlayList");
//		japlaylist.setDescription("AISS PlayList");
//		addPlaylist(japlaylist);
//		
//		Playlist playlist = new Playlist();
//		playlist.setName("Favourites");
//		playlist.setDescription("A sample playlist");
//		addPlaylist(playlist);
//		
//		// Add songs to playlists
//		addSong(japlaylist.getId(), rollingInTheDeep.getId());
//		addSong(japlaylist.getId(), one.getId());
//		addSong(japlaylist.getId(), smellLikeTeenSpirit.getId());
//		addSong(japlaylist.getId(), losingMyReligion.getId());
//		
//		addSong(playlist.getId(), losingMyReligion.getId());
//		addSong(playlist.getId(), gotye.getId());
//		Playlist p1 = new Playlist("Últimos éxitos");
//		p1.setDescription("Favorita de los ultimos tiempos");
//		addPlaylist(p1);
//
//		p1 = new Playlist("Canciones infantiles");
//		p1.setDescription("Canciones para peques");
//		addPlaylist(p1);
//
//		p1 = new Playlist("Recuerdos");
//		p1.setDescription("Recuerdos de la infancia");
//		addPlaylist(p1);
//
//		p1 = new Playlist("AISSPlayList");
//		p1.setDescription("Nueva lista de prueba");
//		addPlaylist(p1);
//
//		
//	}
//	
//	// Playlist related operations
//	@Override
//	public void addPlaylist(Playlist p) {
//		String id = "p" + index++;	
//		p.setId(id);
//		playlistMap.put(id,p);
//	}
//	
//	@Override
//	public Collection<Playlist> getAllPlaylists() {
//			return playlistMap.values();
//	}
//
//	@Override
//	public Playlist getPlaylist(String id) {
//		return playlistMap.get(id);
//	}
//	
//	@Override
//	public void updatePlaylist(Playlist p) {
//		playlistMap.put(p.getId(),p);
//	}
//
//	@Override
//	public void deletePlaylist(String id) {	
//		playlistMap.remove(id);
//	}
//	
//
//	@Override
//	public void addSong(String playlistId, String songId) {
//		Playlist playlist = getPlaylist(playlistId);
//		playlist.addSong(songMap.get(songId));
//	}
//
//	@Override
//	public Collection<Song> getAll(String playlistId) {
//		return getPlaylist(playlistId).getSongs();
//	}
//
//	@Override
//	public void removeSong(String playlistId, String songId) {
//		getPlaylist(playlistId).deleteSong(songId);
//	}
//
//	
//	// Song related operations
//	
//	@Override
//	public void addSong(Song s) {
//		String id = "s" + index++;
//		s.setId(id);
//		songMap.put(id, s);
//	}
//	
//	@Override
//	public Collection<Song> getAllSongs() {
//			return songMap.values();
//	}
//
//	@Override
//	public Song getSong(String songId) {
//		return songMap.get(songId);
//	}
//
//	@Override
//	public void updateSong(Song s) {
//		Song song = songMap.get(s.getId());
//		song.setTitle(s.getTitle());
//		song.setAlbum(s.getAlbum());
//		song.setArtist(s.getArtist());
//		song.setYear(s.getYear());
//	}
//
//	@Override
//	public void deleteSong(String songId) {
//		songMap.remove(songId);
//	}
	
}
