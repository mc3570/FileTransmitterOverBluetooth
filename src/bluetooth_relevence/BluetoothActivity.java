package bluetooth_relevence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;
import com.Mengchen_Zhang.filetransmitteroverbluetooth.R.layout;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class BluetoothActivity extends Activity {
	
	//BluetoothAdapter is required for any and all Bluetooth activity.
	private BluetoothAdapter mBluetoothAdapter = null;
	//If >= 0, this code will be returned in onActivityResult() when the activity exits.
	private int REQUEST_ENABLE_BT  = 1;
	//creat an ArrayAdapter => mArrayAdapter to hold the info of paired device name and address.
	private  ArrayAdapter<String> mArrayAdapter = null;

	//Initialize BluetoothAdapter by using getDefaultAdapter method and return the realized BluetoothAdapter.
	public BluetoothAdapter InitializetoothAdapter(){
		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		return mBluetoothAdapter;
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//set the contentView
		setContentView(R.layout.bluetooth_test);
		//Initialize BluetoothAdapter.
		InitializetoothAdapter();
		//Decide whether support Bluetooth or not
		if(BluetoothSupportOrNot(mBluetoothAdapter)){
			//Query whether there are already paired device already.
			QueryPairDevice();
			if(mArrayAdapter != null){
				//show that if there is devices you want to contact.
			}
			else{
				Toast.makeText(BluetoothActivity.this, "Do not have paired device", Toast.LENGTH_LONG).show();
			}
			//Build a listView to see devices.
		
			//need to get the bluetoothAdaptor
		
			//Start to discover devices.
		
			//when returned devices are clicked, get the Bluetooth device and give its data to Device.
			//Finally we can get the Bluetooth variable client.
		}
		//if the Device does not support bluetooth, tell it to users.
		else{
			Toast.makeText(BluetoothActivity.this, "Do not support Bluetooth", Toast.LENGTH_LONG).show();
		}
		
	}

	//enable device's Bluetooth and set Intent
	public void enableBluetooth(BluetoothAdapter mBluetoothAdapter){
		if(!mBluetoothAdapter.isEnabled()){
			mBluetoothAdapter.enable();
			if (mBluetoothAdapter.isEnabled()){
			Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
			//proof for enable
			System.out.println("Device has been enabled");
			}
			else{
				System.out.println("Device has been failed");
			}
		}
		
	}
	
	//check whether the device support Bluetooth or not.
	//If return "true" means that the device support the Bluetooth otherwise it does not.
	public boolean BluetoothSupportOrNot(BluetoothAdapter mBluetoothAdapter){
		boolean SUPPORT = true;
		boolean NOTSUPPORT = false;
		if (mBluetoothAdapter == null){
			return SUPPORT;
		}
		else {
			return NOTSUPPORT;
		}
	}
	
	//Querying the set of paired devices to see if the desired device is already known.
	public void QueryPairDevice(){
		
		//use adapter to find whether there is an already bounded device
		Set<BluetoothDevice> pairedDevice = mBluetoothAdapter.getBondedDevices();
		//there are at least one paired device.
		if(pairedDevice.size() > 0){
			//loop though paired device
			for(BluetoothDevice device : pairedDevice){
				//add devices to the adapter array
				mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}
	}
	
	//use to discover new devices
	public void DiscoverDevices(){
	
		// Create a BroadcastReceiver for ACTION_FOUND
		BroadcastReceiver mReceiver = new BroadcastReceiver() {
		
			@Override
			public void onReceive(Context context, Intent intent) {
				
				String action = intent.getAction();
				//when discovery finds a device
				if(BluetoothDevice.ACTION_FOUND.equals(action)){
					//Get the BluetoothDevice object form the Intent
					BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					// Add the name and address to an array adapter to show in a ListView
					mArrayAdapter.add(device.getName() + "/n" + device.getAddress());
				}
			}
		};
		
		//set the broadcast receiver filter to Bluetooth found
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		//register the broadcast receiver.
		registerReceiver(mReceiver, filter);
	}
	
	//Enable discoverability
	public void EnableDiscoverability(){
		
		//establish a new intent
		Intent discoverabilityIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		//set discoverable duration to 300s
		discoverabilityIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
		//start the activity when called.
		startActivity(discoverabilityIntent);
	}
	
	//the server lintenning thread
	private class ServerAcceptThread extends Thread{
		
		//Create a Bluetooth server socket.
		private BluetoothServerSocket bluetoothServerSoc = null;
		private final UUID MY_UUID = java.util.UUID.randomUUID();
		
		public ServerAcceptThread(){
			
			//Bulid a temporarily socket.
			BluetoothServerSocket tmp = null;
			try{
				// MY_UUID is the app's UUID string, also used by the client code
				tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("DeviceOne", MY_UUID);
			}
			catch(IOException e){}
			bluetoothServerSoc = tmp;
		}
		
		public void run(){
			
			BluetoothSocket socket = null;
			//Keep listening until there is a call.
			while(true){
				try {
					socket = bluetoothServerSoc.accept();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
				if(socket != null){
					// Do work to manage the connection (in a separate thread)
					//manageConnectedSoket(socket);
					try {
						bluetoothServerSoc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		// Will cancel the listening socket, and cause the thread to finish
		public void cancel(){
			
			try{
				bluetoothServerSoc.close();
			}catch(IOException e){}
		}
	}
	
	private class InitialClientThread extends Thread{
		
		private BluetoothDevice device = null;
		private BluetoothSocket socket = null;
		private UUID MY_UUID = java.util.UUID.randomUUID();
		
		public InitialClientThread(){
			
			BluetoothSocket tmp = null;
			try{
				tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
			}catch(IOException e){}
			socket = tmp;
		}
		
		public void run(){
			
			// Cancel discovery because it will slow down the connection
	        mBluetoothAdapter.cancelDiscovery();
	        try {
				socket.connect();
			} catch (IOException e) {
				try{
					socket.close();
				}catch(IOException closeException){}
				return;
			}
	        // Do work to manage the connection (in a separate thread)
	       // manageConnectedSocket(socket);
		}
		
		// Will cancel the listening socket, and cause the thread to finish
		public void Cancel(){
					
			try{
				socket.close();
			}catch(IOException e){}
		}
	}
	
	private class ConnectThread extends Thread{
		
		private BluetoothSocket socket;
		private InputStream input;
		private OutputStream output;
		
		public void ConnectThread(BluetoothSocket socket){
			
			InputStream tmpInput = null;
			OutputStream tmpOutput = null;
			this.socket = socket;
			try {
				tmpInput = socket.getInputStream();
				tmpOutput = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.input = tmpInput;
			this.output = tmpOutput;
		}
		
		public void run(){
			
			// buffer store for the stream
			byte[] buffer = new byte[2048];
			// bytes returned from read()
			int bytes;
			
			while(true){
				try {
					// Read from the InputStream
					bytes = input.read(buffer);
					//Send the obtained bytes to the UI activity
					//mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/* Call this from the main activity to send data to the remote device */
	    public void write(byte[] bytes) {
	        try {
	            output.write(bytes);
	        } catch (IOException e) { }
	    }
	    
	    /* Call this from the main activity to shutdown the connection */
	    public void cancel() {
	        try {
	            socket.close();
	        } catch (IOException e) { }
	    }
	}
}