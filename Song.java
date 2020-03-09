//Authors: Jin Kim

package view;

public class Song {
	String name;
	String artist;
	String album;
	String description;//maybe only accept years?
	String id;
	
	public Song(String name,String artist, String album, String description) {
		this.name=name.trim();
		this.artist=artist.trim();
		this.album=album.trim();
		this.description=description.trim();
		this.id=name.trim()+artist.trim();
		this.id=this.id.toLowerCase();
	}
	public String getId() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String getArtist() {
		
		return artist;
	}
	
	public String getAlbum() {
	
		return album;
}
	public String getDescription() {
	
		return description;
}
	
}
