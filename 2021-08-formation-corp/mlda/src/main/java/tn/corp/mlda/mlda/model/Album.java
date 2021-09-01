package tn.corp.mlda.mlda.model;

import java.util.ArrayList;
import java.util.List;

public class Album {
	private String title;
	private String image;
	private Singer singer;
	private int year;
	private List<Song> songs = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String getImage() {
		return image;
	}

	private void setImage(String image) {
		this.image = image;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}