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
import tabs_and_fragments.FragmentB.ViewHolder;
import tabs_and_fragments.FragmentB.mAdapter;
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
public class FragmentC extends ListFragment {

	private File currentParent;
	private File root;
	//Announce ArrayLists
	private ArrayList<FolderInfo> folderListView;
	private ArrayList<LrcInfo> lrcListView;
	private ArrayList<MusicInfo> musicListView;
	private ArrayList<PicInfo> picListView;
	private ArrayList<TextInfo> textListView;
	private ArrayList<VedioInfo> vedioListView;
	
	private static ArrayList<HashMap<String, Object>> historyList;
	
	private static ArrayList<String> nameFragmentC = new ArrayList<String>();
	private static ArrayList<String> typeFragmentC = new ArrayList<String>();
	private static ArrayList<String> urlFragmentC = new ArrayList<String>();
	private static ArrayList<String> idFragmentC = new ArrayList<String>();
	private static ArrayList<String> sizeFragmentC = new ArrayList<String>();
	
	private mAdapter onlySimpleAdapter;
	
	public FragmentC() {
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

				//set mAdapter and setListAdapter for folderListView first.
//				mSetListAdapter(historyList);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_fragment_c, null);
		return v;
	}
	
	public void getHistoryArray(ArrayList<HashMap<String, Object>> historyList){
		this.historyList = historyList;
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
			
		}
		
		else {
			//iterate though the list and pick out names and file types.
			for(Iterator<?> iterator = contentList.iterator(); iterator.hasNext();){
				HashMap<String, Object> info = (HashMap<String, Object>)iterator.next();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("fileName", info.get("fileName"));
				map.put("fileType", info.get("fileType"));
				map.put("fileURL", info.get("fileURL"));
				map.put("fileId", info.get("fileId"));
				map.put("fileSize", info.get("fileSize"));
				map.put("image", R.drawable.music_icon);
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
		Log.v("context", getActivity().getApplicationContext() + "");
		mAdapter mSimpleAdapter = new mAdapter(getActivity().getApplicationContext(), mlist);
		return mSimpleAdapter;
		
	}

	final class ViewHolder{
	
		public ImageButton backButton;
		public ImageView img;
		public TextView Name;
		public TextView type;
		public ImageButton OptionButton;
	}
	
	public class mAdapter extends BaseAdapter{

		private List<HashMap<String,Object>> mList;
	
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			
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
				//Holder risk;
				//holder = (ViewHolder) convertView.getTag();
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
//								Intent intent = new Intent();
//								intent.setAction(android.content.Intent.ACTION_VIEW);  
//								File file = new File(receivedHashMap.get("fileURL").toString());  
//								intent.setDataAndType(Uri.fromFile(file), "vedio/*");  
//								startActivity(intent);
								Toast.makeText(getActivity(), "No Apps available", Toast.LENGTH_LONG).show();
							}
							//it is a txt file
							else if(((String)receivedHashMap.get("fileType")).equals("txt")){
//								Intent intent = new Intent();
//								intent.setAction(android.content.Intent.ACTION_VIEW);  
//								File file = new File(receivedHashMap.get("fileURL").toString());  
//								intent.setDataAndType(Uri.fromFile(file), "vedio/*");  
//								startActivity(intent);
								Toast.makeText(getActivity(), "No Apps available", Toast.LENGTH_LONG).show();
							}
							//it is a picture file
							else if(((String)receivedHashMap.get("fileType")).equals("bmp") 
									|| ((String)receivedHashMap.get("fileType")).equals("jpg")){
//								Intent intent = new Intent();
//								intent.setAction(android.content.Intent.ACTION_VIEW);  
//								File file = new File(receivedHashMap.get("fileURL").toString());
//								intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//								intent.setDataAndType(Uri.fromFile(file), "image/*");  
//								startActivity(intent);
								Intent intent = new Intent();
								intent.setClass(getActivity(), GalleyActivity.class);
								startActivity(intent);
							}
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
		
	}
	
}
