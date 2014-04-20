package sendingFileActivity;

import tabs_and_fragments.TabActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import bluetooth_relevence.BluetoothActivity;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;

public class FileSendingActivity extends Activity {

	private Button CancelButton;
	private TextView fileName;
	private ProgressBar bar;
	private ImageView phoneView;
	private ImageView computerView;
	private ImageView arrowsView;
	private BluetoothActivity mBluetoothActivity;
	
	private Intent intentFromTabActivity;
	private Intent intentGoBackToFolders;
	
	private static FileSendingActivity parent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sending_files);
		//Get Intent
		intentFromTabActivity = getIntent();
		intentGoBackToFolders = new Intent();
		//Define Button, textView and ProgressBar
		CancelButton = (Button)this.findViewById(R.id.button_sendingFiles_Cancel);
		fileName = (TextView)this.findViewById(R.id.TextView_fileSending_fileName);
		bar = (ProgressBar)this.findViewById(R.id.ProgressBar_fileSending);
		phoneView = (ImageView)this.findViewById(R.id.ImageView_fileSending_sender);
		computerView = (ImageView)this.findViewById(R.id.ImageView_fileSending_receiver);
		arrowsView = (ImageView)this.findViewById(R.id.ImageView_fileSending_arrow);
		
		bar.setVisibility(View.VISIBLE);
		
		HandleButton(CancelButton);
		HandleTextView(fileName);
		try {
			HandleProgressBar(bar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//The transmission should be done in the service .
	}

	private void HandleButton(Button button){
		
		//bind listener with the button
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//add an dialog to deal with the Cancel click
				if(bar.getProgress() != 100){
					dialogs();
				}
				else if(bar.getProgress() == 100){
					intentGoBackToFolders.setClass(getApplicationContext(), TabActivity.class);
					startActivity(intentGoBackToFolders);
				}				
			}
		});
	}
	
	private void HandleTextView(TextView text){
		
		String fileName;
		fileName = intentFromTabActivity.getStringExtra("fileName");
		text.setText(fileName);
	}
	
	private void HandleProgressBar(final ProgressBar bar) throws Exception{
		
		new Thread (new Runnable(){

			@Override
			public void run() {
				
				int progress = 0;
				
				while(bar.getProgress() != 100){
					
					//every 0.5 second the bar grows 5.
					bar.setProgress(progress += 5);
					//we should attach status of file transmittion with the progress of the bar.
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							//chage image with the growing of progressbar
							if((bar.getProgress() / 5) % 3 == 0){
				            	  phoneView.setBackgroundResource(R.drawable.sender3);
				            	  computerView.setBackgroundResource(R.drawable.receiver3);
				            	  arrowsView.setBackgroundResource(R.drawable.arrows1);
				              }
				              else if((bar.getProgress() / 5) % 3 == 1){
				            	  phoneView.setBackgroundResource(R.drawable.sender2);
				            	  computerView.setBackgroundResource(R.drawable.receiver2);
				            	  arrowsView.setBackgroundResource(R.drawable.arrows2);
				              }
				              else if((bar.getProgress() / 5) % 3 == 2){
				            	  phoneView.setBackgroundResource(R.drawable.sender1);
				            	  computerView.setBackgroundResource(R.drawable.receiver);
				            	  arrowsView.setBackgroundResource(R.drawable.arrows3);
				              }
						}
					});
				}
				Message msg = handler.obtainMessage();
				msg.arg1 = bar.getProgress();
				handler.sendMessage(msg);
			}
		}).start();		
				
	}

	//define the dialog connected with Cancel button.
	protected void dialogs(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Attention");
		builder.setMessage("Are you sure about stopping the transmition?");
		builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//If "yes", go back to TabActivity.
				dialog.dismiss();
				intentGoBackToFolders.setClass(getApplicationContext(), TabActivity.class);
				startActivity(intentGoBackToFolders);
				
			}
		});
		builder.setNegativeButton("No", new android.content.DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//if "No" resume the transmition.
				dialog.dismiss();
			}
			
		});
		builder.create().show();
	}
	
	//show toast about success.
	@SuppressLint("HandlerLeak")
	private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
              if(msg.arg1 == 100){
            	  Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            	  //bar.setVisibility(View.GONE);
            	  CancelButton.setText("Done");
              }
        }
    };
}
