package sd_card_handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class SDCardHandler {

	//let the file automatically saved in the fixed directory path.
	private String SDPath = Environment.getExternalStorageDirectory() + "/" + "Mp3Player" + "/";
	
	public boolean IsFileThere(String fileName){
		File file = new File(SDPath + fileName);
		return file.exists();
		
	}
	
	public File CreatNewFile(String fileName) throws IOException{
		File file = new File(SDPath + fileName);
		file.createNewFile();
		return file;
	}
	
	public boolean DeleteFile(String fileName){
		//delete the choosen file
		File file = new File(SDPath + fileName);
		return file.delete();
	}
	
	public boolean SetMp3PlayerPath(){
		File file = new File(SDPath);
		return file.mkdir();
	}
	
	public File writeFile2SDCard(String fileName, InputStream inputStream) throws IOException{
		File file = null;
		OutputStream outputStream = null;
		SetMp3PlayerPath();
		outputStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		while(inputStream.read(buffer) != -1){
			outputStream.write(buffer);
		}
		outputStream.flush();
		outputStream.close();
		return file;
	}
}
