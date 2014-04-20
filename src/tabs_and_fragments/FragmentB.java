package tabs_and_fragments;

import info.FolderInfo;
import info.LrcInfo;
import info.MusicInfo;
import info.PicInfo;
import info.TextInfo;
import info.VedioInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import openGL_CoverFlow.GalleyActivity;
import sendingFileActivity.FileSendingActivity;
import tabs_and_fragments.FragmentA.EditDialogFragment;
import tabs_and_fragments.FragmentA.ViewHolder;
import tabs_and_fragments.FragmentA.mAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
@SuppressLint("NewApi")
public class FragmentB extends ListFragment {

	private File currentParent;
	private File root;
	
	private static ArrayList<String> nameList = new ArrayList<String>();
	
	private static ArrayList<String> dirList =  new ArrayList<String>();
	
	//Announce ArrayLists
	private static ArrayList<FolderInfo> folderListView;
	private static ArrayList<LrcInfo> lrcListView;
	private static ArrayList<MusicInfo> musicListView;
	private static ArrayList<PicInfo> picListView;
	private static ArrayList<TextInfo> textListView;
	private static ArrayList<VedioInfo> vedioListView;
	
	private mAdapter onlySimpleAdapter;
	
	public FragmentB() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Get Root dir of SDCard.
		root = new File("/mnt/sdcard/");
				
		if (root.exists()){
			currentParent = root;
			root.listFiles();
		}	
		//Define listInfos
		defineListInfo();
		// find out all the files we want in sdcard.
		getWantedFiles(currentParent);
		//build folders contain music, lrc, txt, vedio, pictures.
		buildfolders();
		//set mAdapter and setListAdapter for folderListView first.
		mSetListAdapter(folderListView);
	}
	
	public void mSetListAdapter(ArrayList<?> mListView){
		
		//build a simpleAdaptor to connect list and contents.
		onlySimpleAdapter = buildSimpleAdapter(mListView);
		//setup the listview.
		setListAdapter(onlySimpleAdapter);
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

	//When the item in the list is clicked  doing this part.
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		
		switch(position){
		case 0:{
			if(lrcListView.isEmpty()){
				Log.v("Position", position + "");
			}
			else{
				
				//build lrc listView
				mAdapter mSimpleAdapter = buildSimpleAdapter(lrcListView);
				setListAdapter(mSimpleAdapter);
				//pass the clicked item and position to Activity.
			}
			break;
		}
		case 1:{
			if(musicListView.isEmpty()){
				Log.v("Position", position + "");
			}
			else{
				//build music listView
				mAdapter mSimpleAdapter = buildSimpleAdapter(musicListView);
				setListAdapter(mSimpleAdapter);
				//pass the clicked item and position to Activity.
			}
			break;
		}
		case 2:{
			if(picListView.isEmpty()){
				Log.v("Position", position + "");
			}
			else{
				//build music listView
				mAdapter mSimpleAdapter = buildSimpleAdapter(picListView);
				setListAdapter(mSimpleAdapter);
				//pass the clicked item and position to Activity.
			}
			break;
		}
		case 3:{
			if(textListView.isEmpty()){
				Log.v("Position", position + "");
			}
			else{
				//build music listView
				mAdapter mSimpleAdapter = buildSimpleAdapter(textListView);
				setListAdapter(mSimpleAdapter);
				//pass the clicked item and position to Activity.
			}
			break;
		}
		case 4:{
			if(vedioListView.isEmpty()){
				Log.v("Position", position + "");
			}
			else{
				//build music listView
				mAdapter mSimpleAdapter = buildSimpleAdapter(vedioListView);
				setListAdapter(mSimpleAdapter);
				//pass the clicked item and position to Activity.
			}
			break;
		}
		
		}
	}

	//Return lists to SimpleAdaptor to build the listView.
	private List<HashMap<String, Object>> addListView(ArrayList<?> contentList){
		
		//Build a list of HashMap to contain the info inside the arraylist.
		List<HashMap<String, Object>> mList = new ArrayList<HashMap<String,Object>>();
		//If there are no contents in the list then give a note back
		if (contentList.isEmpty()){
			Log.v("Contnet", contentList.get(0).getClass().getSimpleName() + "is Empty.");
		}
		//Use contentList.get(0).getClass().getSimpleName().equals("MusicInfo") to decide 
		//what type it is.
		//This is the Music type
		else if(contentList.get(0).getClass().getSimpleName().equals("MusicInfo")){
			
			//iterate though the list and pick out names and file types.
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				MusicInfo info = (MusicInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.music_icon);
				mList.add(map);
			}
		}
		//This is the Pic type
		else if(contentList.get(0).getClass().getSimpleName().equals("PicInfo")){
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				PicInfo info = (PicInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.pic_icon);
				mList.add(map);
			}
		}
		//This is the Text type
		else if(contentList.get(0).getClass().getSimpleName().equals("TextInfo")){
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				TextInfo info = (TextInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.txt_icon);
				mList.add(map);
			}
		}
		//This is the lrc type
		else if(contentList.get(0).getClass().getSimpleName().equals("LrcInfo")){
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				LrcInfo info = (LrcInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.lrc_icon);
				mList.add(map);
			}
		}
		//This is the vedio type.
		else if(contentList.get(0).getClass().getSimpleName().equals("VedioInfo")){
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				VedioInfo info = (VedioInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.vedio_icon);
				mList.add(map);
			}
		}
		else if(contentList.get(0).getClass().getSimpleName().equals("FolderInfo")){
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				FolderInfo info = (FolderInfo)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.getName());
				map.put("fileType", info.getType());
				map.put("fileURL", info.getURL());
				map.put("fileId", info.getId());
				map.put("fileSize", info.getSize());
				map.put("image", R.drawable.floder_icon);
				mList.add(map);
			}
		}
		//return the list back to simpleAdaptor.
		return mList;
	}
	
	//build new folders in the list to hold music, vedio, txt and so on.
	private void buildfolders(){
		
		int folderId = 0;
		//add folders manually.
		FolderInfo info1 = new FolderInfo(folderId++, "Lrc Files", 0, null, "folder", root);
		FolderInfo info2 = new FolderInfo(folderId++, "Musics", 0, null, "folder", root);
		FolderInfo info3 = new FolderInfo(folderId++, "Pictures", 0, null, "folder", root);
		FolderInfo info4 = new FolderInfo(folderId++, "Textes", 0, null, "folder", root);
		FolderInfo info5 = new FolderInfo(folderId++, "Vedios", 0, null, "folder", root);
		folderListView.add(info1);
		folderListView.add(info2);
		folderListView.add(info3);
		folderListView.add(info4);
		folderListView.add(info5);
		
	}
	
	
	private mAdapter buildSimpleAdapter(ArrayList<?> fileList){
		
		List<HashMap<String,Object>> mlist = addListView(fileList);
		//String[] s = {"fileName"};//, "fileType"};
		//int[] i = {R.id.TextView_fileName};//, R.id.TextView_typeName};
		//mAdapter mSimpleAdapter = new SimpleAdapter(getActivity(), mlist, R.layout.file_info, s, i);
		mAdapter mSimpleAdapter = new mAdapter(getActivity().getApplicationContext(), mlist);
		return mSimpleAdapter;
		
	}
	
	//get all the folders in sdcard
	@SuppressLint("NewApi")
	private void getAllFolders(File folder){
	
		File files[] = folder.listFiles();
		if(files != null){
			for(File f:files){
				int i_folder = 0;
				if(f.isDirectory()){
					FolderInfo info = new FolderInfo(i_folder++, f.getName(), f.getTotalSpace(), f.getAbsolutePath(), "folder", f.getParentFile());
					folderListView.add(info);
				}
			}
		}
	}
	
	//get all the files we need in sdcard
	@SuppressLint("NewApi")
	private void getWantedFiles(File root){
		
		File[] files = root.listFiles();
		//count number for each info
		int i_pic = 0;
		int i_music = 0;
		int i_lrc = 0;
		int i_text = 0;
		int i_vedioInfo = 0;
		
		if(files != null){
			for(File f:files){
				//If f is a folder not the file we do the iterator using getWantedFiles(f).
				if(f.isDirectory()){
					getWantedFiles(f);
				}
				else{
					//if the file is a .jpg file which means it is a picture.
					if(f.getAbsolutePath().endsWith(".jpg")){
						PicInfo info = new PicInfo(i_pic++,f.getName(),f.getAbsolutePath(),f.getTotalSpace(),"jpg",f.getParentFile());
						picListView.add(info);
						nameList.add(f.getName());
						dirList.add(f.getAbsolutePath());
					}
					//if the file is a .bmp file which means it is a picture.
					else if(f.getAbsolutePath().endsWith(".bmp")){
						PicInfo info = new PicInfo(i_pic++,f.getName(),f.getAbsolutePath(),f.getTotalSpace(),"bmp",f.getParentFile());
						picListView.add(info);
						nameList.add(f.getName());
						dirList.add(f.getAbsolutePath());
					}
					//if the file is a .mp3 file which means it is a music.
					else if(f.getAbsolutePath().endsWith(".mp3")){
						MusicInfo info = new MusicInfo(i_music++, f.getName(), f.getTotalSpace(), f.getAbsolutePath(), "mp3", f.getParentFile());
						musicListView.add(info);
						Log.v("MusicInfo", info.getName());
					}
					//if the file is a .lrc file which means it is a lyric.
					else if(f.getAbsolutePath().endsWith(".lrc")){
						LrcInfo info = new LrcInfo(i_lrc++, f.getName(), f.getTotalSpace(), f.getAbsolutePath(), "lrc", f.getParentFile());
						lrcListView.add(info);
					}
					//if the file is a .txt file which means it is a txt file.
					else if(f.getAbsolutePath().endsWith(".txt")){
						TextInfo info = new TextInfo(i_text++, f.getName(), f.getTotalSpace(), f.getAbsolutePath(), "txt", f.getParentFile());
						textListView.add(info);
					}
					//if the file is a .mp4 file which means it is a vedio.
					else if(f.getAbsolutePath().endsWith(".mp4")){
						VedioInfo info = new VedioInfo(i_vedioInfo++, f.getName(), f.getTotalSpace(), f.getAbsolutePath(), "mp4", f.getParentFile());
						vedioListView.add(info);
					}
					
				}
			}
		}
	}
	


	final class ViewHolder{
	
		public ImageButton backButton;
		public ImageView img;
		public TextView Name;
		public TextView type;
		public ImageButton OptionButton;
	}

	
	public class mAdapter extends BaseAdapter{

		private int position;
		private List<HashMap<String,Object>> mList;
		private ArrayList<?> arrayList;
		
		public List<HashMap<String, Object>> getmList() {
			return mList;
		}
		
		public void getCurrentArrayList(ArrayList<?> arrayList){
			this.arrayList = arrayList;
		}
	
		public mAdapter(Context context, List<HashMap<String,Object>> mList) {
			super();
			this.mList = mList;
		}

		@Override
		public int getCount() {
			if(mList.isEmpty()){
				return 0;
			}
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		//return the position that is clicked right now.
		public int getCurrentPosition(){
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			//prepared it for the more Option Click.
			this.position = position;
			final HashMap<String, Object> ChoosedMap = mList.get(position);
			
			final ViewHolder holder = new ViewHolder();
			if(convertView == null){
				LayoutInflater mInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
				convertView = mInflater.inflate(R.layout.file_info, null);
				holder.img = (ImageView)convertView.findViewById(R.id.ImageView_file);
				holder.Name = (TextView)convertView.findViewById(R.id.TextView_fileName);
				
				//holder.type = (TextView)convertView.findViewById(R.id.TextView_typeName);
				holder.OptionButton = (ImageButton)convertView.findViewById(R.id.ImageButton_MoreOptions);
				convertView.setTag(holder);
				if(!mList.get(0).get("fileType").equals("folder")){
					holder.backButton = (ImageButton)convertView.findViewById(R.id.ImageButton_back);
					holder.backButton.setBackgroundResource(R.drawable.back_icon_normal);
				}
			}
			else {
				holder.img = (ImageView)convertView.findViewById(R.id.ImageView_file);
				holder.Name = (TextView)convertView.findViewById(R.id.TextView_fileName);
				holder.OptionButton = (ImageButton)convertView.findViewById(R.id.ImageButton_MoreOptions);
				if(!mList.get(0).get("fileType").equals("folder")){
					holder.backButton = (ImageButton)convertView.findViewById(R.id.ImageButton_back);
					holder.backButton.setBackgroundResource(R.drawable.back_icon_normal);
				}
			}
			
			holder.img.setBackgroundResource((Integer)mList.get(position).get("image"));
			holder.Name.setText((String)mList.get(position).get("fileName"));
			//holder.type.setText((String)mList.get(position).get("fileType"));
			
			//Do not add backButton when it is a folder list.
			if(!mList.get(0).get("fileType").equals("folder")){
				holder.backButton.setBackgroundResource(R.drawable.back_icon_normal);
				//backButton's onclick listener
				holder.backButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.v("Butto-click", "BackButtonCalled");
						mSetListAdapter(folderListView);
					}
				});
				//backButton's on touch listener
				holder.backButton.setOnTouchListener(new View.OnTouchListener() {
					
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						//when button is pressed, use different image from the original one.
						if(event.getAction() == MotionEvent.ACTION_DOWN){
							holder.backButton.setBackgroundResource(R.drawable.back_icon_down);
						}
						//when button is released, use another image
						else if(event.getAction() == MotionEvent.ACTION_UP){
							holder.backButton.setBackgroundResource(R.drawable.back_icon_normal);
						}
						return false;
					}
				});
				
			}
			holder.OptionButton.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
					Log.v("Butto-click", "OptionButtonCalled");
					moreOptionAlertDialog dialog = new moreOptionAlertDialog();
					dialog.show(getFragmentManager(), getTag());
					//tell the dialog what Map it is.
					dialog.getlistViewInfo(ChoosedMap);
					dialog.getCurrentArrayList(arrayList);
				}
			});
			holder.OptionButton.setOnTouchListener(new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					//when button is pressed, use different image from the original one.
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						holder.OptionButton.setImageDrawable(getResources().getDrawable(R.drawable.more_option_icon_down));
					}
					//when button is released, use another image
					else if(event.getAction() == MotionEvent.ACTION_UP){
						holder.OptionButton.setImageDrawable(getResources().getDrawable(R.drawable.more_option_icon_normal));
					}
					return false;
				}
			});
			
			//return the View of fragment.
			return convertView;
		}
	
	}
	
	@SuppressLint("ValidFragment")
	public class moreOptionAlertDialog extends DialogFragment{
		
		private HashMap<String, Object> receivedHashMap;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle((String)receivedHashMap.get("fileName"));
			builder.setItems(R.array.String_array_for_all, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Log.v("Options_Dialog", which + "");
					
					switch(which){
					//Open
					case 0:{
						//decide whether it is a foler
						if(((String)receivedHashMap.get("fileType")).equals("folder")){
							
							//Decide which folder it is
							if(((String)receivedHashMap.get("fileName")).equals("Musics")){
								if(!musicListView.isEmpty()){
									mAdapter mSimpleAdapter = buildSimpleAdapter(musicListView);
									setListAdapter(mSimpleAdapter);
								}
								else {
									Toast.makeText(getActivity(), "No Data Available", Toast.LENGTH_LONG).show();
								}
							}
							else if(((String)receivedHashMap.get("fileName")).equals("Lrc Files")){
								if(!lrcListView.isEmpty()){
									mAdapter mSimpleAdapter = buildSimpleAdapter(lrcListView);
									setListAdapter(mSimpleAdapter);
								}
								else{
									Toast.makeText(getActivity(), "No Data Available", Toast.LENGTH_LONG).show();
								}
							}
							else if(((String)receivedHashMap.get("fileName")).equals("Pictures")){
								if(!picListView.isEmpty()){
									mAdapter mSimpleAdapter = buildSimpleAdapter(picListView);
									setListAdapter(mSimpleAdapter);
								}
								else{
									Toast.makeText(getActivity(), "No Data Available", Toast.LENGTH_LONG).show();
								}
							}
							else if(((String)receivedHashMap.get("fileName")).equals("Textes")){
								if(!textListView.isEmpty()){
									mAdapter mSimpleAdapter = buildSimpleAdapter(textListView);
									setListAdapter(mSimpleAdapter);
								}
								else {
									Toast.makeText(getActivity(), "No Data Available", Toast.LENGTH_LONG).show();
								}
							}
							else if(((String)receivedHashMap.get("fileName")).equals("Vedios")){
								if(!vedioListView.isEmpty()){
									mAdapter mSimpleAdapter = buildSimpleAdapter(vedioListView);
									setListAdapter(mSimpleAdapter);
								}
								else{
									Toast.makeText(getActivity(), "No Data Available", Toast.LENGTH_LONG).show();
								}
							}
						}
						//It is a file
						else {
							//it is a music file
							if(((String)receivedHashMap.get("fileType")).equals("mp3")){
								Intent intent = new Intent();
								ComponentName comp = new ComponentName("com.android.music", "com.android.music.MediaPlaybackActivity");
								intent.setComponent(comp);
								intent.setAction(android.content.Intent.ACTION_VIEW);  
								File file = new File(receivedHashMap.get("fileURL").toString());  
								intent.setDataAndType(Uri.fromFile(file), "audio/*");  
								startActivity(intent);
							}
							//it is a vedio file
							else if(((String)receivedHashMap.get("fileType")).equals("mp4")){
								Intent intent = new Intent();
								intent.setAction(android.content.Intent.ACTION_VIEW);  
								File file = new File(receivedHashMap.get("fileURL").toString());  
								intent.setDataAndType(Uri.fromFile(file), "vedio/*");  
								startActivity(intent);
							}
							//it is a lyric file
							else if(((String)receivedHashMap.get("fileType")).equals("lrc")){

								Toast.makeText(getActivity(), "No Apps available", Toast.LENGTH_LONG).show();
							}
							//it is a txt file
							else if(((String)receivedHashMap.get("fileType")).equals("txt")){

								Toast.makeText(getActivity(), "No Apps available", Toast.LENGTH_LONG).show();
							}
							//it is a picture file
							else if(((String)receivedHashMap.get("fileType")).equals("bmp") ||
									((String)receivedHashMap.get("fileType")).equals("jpg")){
								Intent intent = new Intent();
								Bundle extras = new Bundle();
								extras.putStringArrayList("nameList", nameList);
								extras.putStringArrayList("dirList", dirList);
								intent.putExtras(extras);
								intent.setClass(getActivity(), GalleyActivity.class);
								startActivity(intent);
							}
//							else if(((String)receivedHashMap.get("fileType")).equals("jpg")){
//								Intent intent = new Intent();
//								intent.setAction(android.content.Intent.ACTION_VIEW);  
//								File file = new File(receivedHashMap.get("fileURL").toString());
//								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//								intent.setDataAndType(Uri.fromFile(file), "image/*");  
//								startActivity(intent);
//							}
						}
						break;
					}
					//Send
					case 1:{
						//change to the sending Activity.
						Intent changeToSendingActivity = new Intent();
						changeToSendingActivity.setClass(getActivity(), FileSendingActivity.class);
						changeToSendingActivity.putExtra("fileName", (String)receivedHashMap.get("fileName"));
						startActivity(changeToSendingActivity);
						break;
					}
					//Edit
					case 2:{
						
						//Use the 
						EditDialogFragment EditDialog = new EditDialogFragment();
						EditDialog.show(getFragmentManager(), getTag());
						break;
					}
					//Property
					case 3:{
						new AlertDialog.Builder(getActivity())
						
						//give out the property
						.setTitle("Properties")
						.setMessage("Id: \t" + String.valueOf(receivedHashMap.get("fileId")) + "\n"
								+ "Name: \t " + (String)receivedHashMap.get("fileName") + "\n"
								+ "Path: \t" + (String)receivedHashMap.get("fileURL") + "\n"
								+ "Type: \t" + (String)receivedHashMap.get("fileType") + "\n"
								+ "Size: \t" + String.valueOf(receivedHashMap.get("fileSize")) + "\n")
						.create()
						.show();
						break;
					}
					//delete
					case 4:{
						
						break;
					}
					}
					
				}
			});
			
			return builder.create();
		}
		
		private void getlistViewInfo(HashMap<String, Object> list){
			//get the using list from the listView.
			receivedHashMap = list;
		}
		
		private void getCurrentArrayList(ArrayList<?> arrayList){
		}
	}
	
	//EditDialog in Edit.
	public static class EditDialogFragment extends DialogFragment{

		private String ChangedName;
		private String ChangedId;
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			
			LayoutInflater inflator = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			AlertDialog.Builder Editbuilder = new AlertDialog.Builder(getActivity());
			View v = inflator.inflate(R.layout.edit_dialogs, null);
			final EditText EditName = (EditText)v.findViewById(R.id.dialogs_name);
			final EditText EditId = (EditText)v.findViewById(R.id.dialogs_id);
			
			Editbuilder
			.setView(v)
			.setTitle("Edit")
			.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Log.v("EditButton", "positive");
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					
				}
			})
			.setPositiveButton("Confirm:", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					Log.v("EditButton", "Positive");
					
					ChangedName = EditName.getText().toString();
					ChangedId = EditId.getText().toString();
					Log.v("Edit", "I am here");
					//JudgeWhichListAndChange(currentlistView);
				}
			});
			
			return Editbuilder.create();
		}
		
		private void JudgeWhichListAndChange(ArrayList<?> list){
		
			//list is empty and it is the music info
			if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("MusicInfo")){
			ReplaceMusicData(musicListView);
			Log.v("Edit", "music listView");
			}
			else if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("PicInfo")){
				ReplacePicData(picListView);
			}
			else if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("TextInfo")){
				ReplaceTxtData(textListView);
			}
			else if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("VedioInfo")){
				ReplaceVedioData(vedioListView);
			}
			else if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("LrcInfo")){
				ReplaceLrcData(lrcListView);
			}
			else if((!list.isEmpty()) && list.get(0).getClass().getSimpleName().equals("FolderInfo")){
				ReplaceFolderData(folderListView);
			}
		}
	
		private void ReplaceMusicData(ArrayList<MusicInfo> list){
		
			for(Iterator<MusicInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}
	
		private void ReplacePicData(ArrayList<PicInfo> list){
		
			for(Iterator<PicInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}
	
		private void ReplaceTxtData(ArrayList<TextInfo> list){
		
			for(Iterator<TextInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}

		private void ReplaceLrcData(ArrayList<LrcInfo> list){

			for(Iterator<LrcInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}
		
		private void ReplaceVedioData(ArrayList<VedioInfo> list){
			
			for(Iterator<VedioInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}

		private void ReplaceFolderData(ArrayList<FolderInfo> list){

			for(Iterator<FolderInfo> iterator = list.iterator();iterator.hasNext();){
				iterator.next().setId(Integer.valueOf(ChangedId));
				iterator.next().setName(ChangedName);
			}
		}
	}

}
