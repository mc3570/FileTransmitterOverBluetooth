package com.Mengchen_Zhang.filetransmitteroverbluetooth;

import openGL_CoverFlow.GalleyActivity;
import sendingFileActivity.FileSendingActivity;
import tabs_and_fragments.TabActivity;
import bluetooth_relevence.Bluetooth_Tryouts;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

//The activity for the welcome page
public class MainActivity extends Activity implements OnClickListener  {

	private Button TabButton = null;
	private ImageView img = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		//Define Button
		TabButton = (Button)findViewById(R.id.ButtonView_welcome);
		img = (ImageView)findViewById(R.id.ImageView_welcome);
		
		TabButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		Button ClickedButton = (Button)arg0;
		if(ClickedButton.getId() == R.id.ButtonView_welcome){
			Intent ChangeActivityIntent = new Intent();
			ChangeActivityIntent.setClass(MainActivity.this, TabActivity.class);
			startActivity(ChangeActivityIntent);
		}
	}
		
}
