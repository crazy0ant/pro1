package com.example.mobilplay.domain;

/*
 * ����һ����Ƶ����Ƶ
 */
public class MediaItem {
	
	private String name; 
	
	private Long duration;
	
	private Long size;
	
	private String data;
	
	private String artist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "MediaItem [name=" + name + ", duration=" + duration + ", size="
				+ size + ", data=" + data + ", artist=" + artist + "]";
	}
	
	
	
	
}
