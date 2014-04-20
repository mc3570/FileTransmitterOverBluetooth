package bluetooth_relevence;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;
import com.Mengchen_Zhang.filetransmitteroverbluetooth.R.id;
import com.Mengchen_Zhang.filetransmitteroverbluetooth.R.layout;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class Bluetooth_Tryouts extends Activity{

	//Define four buttons for testing
	private Button SetupButton, QueryButton, DiscoverButton;
	
	//Set up the Bluetooth activity to manage the bluetooth examples
	private BluetoothActivity client = new BluetoothActivity();
	
	//Set up the BluetoothAdapter.
	private BluetoothAdapter mBluetoothAdapter = null;
	
	//creat an ArrayAdapter => mArrayAdapter to hold the info of paired device name and address.
	private  ArrayAdapter<String> mArrayAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set view to bluetooth_test.
		setContentView(R.layout.bluetooth_test);
		//Bind Id to these four buttons
		SetupButton = (Button) this.findViewById(R.id.SetUpDeviceId);
		QueryButton = (Button) this.findViewById(R.id.QueryPairedDeviceId);
		DiscoverButton = (Button)this.findViewById(R.id.DiscoverDeviceId);
		
		//Realize a new ClickListener
		ButtonClickListener buttonListener = new ButtonClickListener();
	
		//Bind four button with onClickListener
		SetupButton.setOnClickListener(buttonListener);
		QueryButton.setOnClickListener(buttonListener);
		DiscoverButton.setOnClickListener(buttonListener);
	}
	
	//ButtonClickListener is used to listener the button click operation.
	class ButtonClickListener implements android.view.View.OnClickListener{

		//Build a new Button to accept the button info from view.
		private Button IncomeButton = null;
		
		@Override
		public void onClick(View v) {
			
			//transfer view v into Button type
			IncomeButton = (Button)v;
			//Implement BluetoothActivity
			
			switch(IncomeButton.getId()){
			
			case R.id.SetUpDeviceId:{
				//set the contentView
				setContentView(R.layout.bluetooth_test);
				//Initialize BluetoothAdapter.
				mBluetoothAdapter = client.InitializetoothAdapter();
				//Decide whether support Bluetooth or not
				if(client.BluetoothSupportOrNot(mBluetoothAdapter)){
					Toast.makeText(Bluetooth_Tryouts.this, "We support Bluetooth", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(Bluetooth_Tryouts.this, "We do not support Bluetooth", Toast.LENGTH_LONG).show();
				}
				break;
			}
			case R.id.QueryPairedDeviceId:{
				if(client.BluetoothSupportOrNot(mBluetoothAdapter)){
					//Query whether there are already paired device already.
					client.EnableDiscoverability();
					client.QueryPairDevice();
					if(mArrayAdapter != null){
						//show that if there is devices you want to contact.
						
					}
					else{
						System.out.println("We do not have paired devices");
						Toast.makeText(Bluetooth_Tryouts.this, "Do not have paired device", Toast.LENGTH_LONG).show();
					}
				}
				break;
			}
			case R.id.DiscoverDeviceId:{
				
				client.EnableDiscoverability();
				client.DiscoverDevices();
				if(mArrayAdapter != null){
					
				}
				else{
					Toast.makeText(Bluetooth_Tryouts.this, "No device is discovered", Toast.LENGTH_LONG).show();
				}
				break;
			}
			
			}
		}

	}

	
}
