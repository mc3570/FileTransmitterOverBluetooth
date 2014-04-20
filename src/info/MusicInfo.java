package info;

import java.io.File;

public class MusicInfo {

	private int id;
	private String name;
	private long size;
	private String URL;
	private String type;
	private File parentFolder;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getParentFolder() {
		return parentFolder;
	}
	public void setParentFolder(File parentFolder) {
		this.parentFolder = parentFolder;
	}
	public MusicInfo(int id, String name, long size, String uRL,
			String type, File parentFolder) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		URL = uRL;
		this.type = type;
		this.parentFolder = parentFolder;
	}
	
	
	
}
