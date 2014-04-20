package applicationStaticListView;

import info.FolderInfo;
import info.LrcInfo;
import info.MusicInfo;
import info.PicInfo;
import info.TextInfo;
import info.VedioInfo;

import java.io.File;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Application;

public class ApplicationStatic extends Application {
	
	public File currentParent;
	public File root;
	public static boolean isSDCardScaned = false;
	
	public static ArrayList<FolderInfo> folderListView;
	public static ArrayList<LrcInfo> lrcListView;
	public static ArrayList<MusicInfo> musicListView;
	public static ArrayList<PicInfo> picListView;
	public static ArrayList<TextInfo> textListView;
	public static ArrayList<VedioInfo> vedioListView;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		//Get Root dir of SDCard.
		root = new File("/mnt/sdcard/");					
		if (root.exists()){
			currentParent = root;
			root.listFiles();
		}	
		//define all these listViews.
		defineListInfo();
		buildfolders();
	}
	
	public File getCurrentParent() {
		return currentParent;
	}

	public void setCurrentParent(File currentParent) {
		this.currentParent = currentParent;
	}

	public File getRoot() {
		return root;
	}

	public void setRoot(File root) {
		this.root = root;
	}

	public static ArrayList<FolderInfo> getFolderListView() {
		return folderListView;
	}
	public static void setFolderListView(ArrayList<FolderInfo> folderListView) {
		ApplicationStatic.folderListView = folderListView;
	}
	public static ArrayList<LrcInfo> getLrcListView() {
		return lrcListView;
	}
	public static void setLrcListView(ArrayList<LrcInfo> lrcListView) {
		ApplicationStatic.lrcListView = lrcListView;
	}
	public static ArrayList<MusicInfo> getMusicListView() {
		return musicListView;
	}
	public static void setMusicListView(ArrayList<MusicInfo> musicListView) {
		ApplicationStatic.musicListView = musicListView;
	}
	public static ArrayList<PicInfo> getPicListView() {
		return picListView;
	}
	public static void setPicListView(ArrayList<PicInfo> picListView) {
		ApplicationStatic.picListView = picListView;
	}
	public static ArrayList<TextInfo> getTextListView() {
		return textListView;
	}
	public static void setTextListView(ArrayList<TextInfo> textListView) {
		ApplicationStatic.textListView = textListView;
	}
	public static ArrayList<VedioInfo> getVedioListView() {
		return vedioListView;
	}
	public static void setVedioListView(ArrayList<VedioInfo> vedioListView) {
		ApplicationStatic.vedioListView = vedioListView;
	}
	//Define ArrayList for each info
	private void defineListInfo(){
		
		folderListView = new ArrayList<FolderInfo>();
		lrcListView = new ArrayList<LrcInfo>();
		musicListView = new ArrayList<MusicInfo>();
		picListView = new ArrayList<PicInfo>();
		textListView = new ArrayList<TextInfo>();
		vedioListView = new ArrayList<VedioInfo>();
		
	}
	
	//build new folders in the list to hold music, vedio, txt and so on.
	private void buildfolders(){
		
		int folderId = 0;
		//add folders manually.
		FolderInfo info1 = new FolderInfo(folderId++, "Lrc Files", 0, "/mnt/sdcard/", "folder", root);
		FolderInfo info2 = new FolderInfo(folderId++, "Musics", 0, "/mnt/sdcard/", "folder", root);
		FolderInfo info3 = new FolderInfo(folderId++, "Pictures", 0, "/mnt/sdcard/", "folder", root);
		FolderInfo info4 = new FolderInfo(folderId++, "Textes", 0, "/mnt/sdcard/", "folder", root);
		FolderInfo info5 = new FolderInfo(folderId++, "Vedios", 0, "/mnt/sdcard/", "folder", root);
		folderListView.add(info1);
		folderListView.add(info2);
		folderListView.add(info3);
		folderListView.add(info4);
		folderListView.add(info5);
		
	}
}
