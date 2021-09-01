package tn.corp.mlda.mlda.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import tn.corp.mlda.mlda.model.Album;
import tn.corp.mlda.mlda.model.Song;

public class AlbumService {
	private List<Album> allAlbums = new ArrayList<>();

	public List<Album> findAlbumsBetter(Integer year, String category, String singer) {
		return allAlbums.stream()
		.filter(album->
			(year==null || album.getYear()==year)
			&&(singer==null || singer.equals(album.getSinger().getName()))
			&& ( category==null ||
							album.getSongs().stream().anyMatch(
									s->category.equals(s.getCategory().getName())
							)
			)
		)
		.collect(Collectors.toList());
	}
	
	public List<Album> findAlbums(Integer year, String category, String singer) {
		List<Album> all = new ArrayList<Album>();
		for (Album album : all) {
			if (albumMatches(album, year, category, singer)) {
				all.add(album);
			}
		}
		return all;
	}

	private boolean albumMatches(Album album, Integer year, String category, String singer) {
		if(year!=null) {
			if(album.getYear()!=year) {
				return false;
			}
		}
		if(singer!=null) {
			if(album.getSinger()==null || !album.getSinger().getImage().equals(singer)) {
				return false;
			}
		}
		if(category!=null) {
			boolean catOk=false;
			for (Song s : album.getSongs()) {
				if(s.getCategory()!=null && s.getCategory().getName().equals(category)) {
					catOk=true;
					break;
				}
			}
			if(!catOk) {
				return false;
			}
		}
		return true;
	}
}
