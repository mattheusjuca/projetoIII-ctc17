package dexflix;

import java.util.ArrayList;

public class Movie {
	
	private int id;
	private String title;
	private ArrayList<String> genres;
	
	public Movie (int id, String title) {
		this.id = id;
		this.title = title;
		genres = new ArrayList<String>();
	}
	
	public void addGenre (String genre) {
		this.genres.add(genre);
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<String> getGenres() {
		return genres;
	}
	
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	
}
