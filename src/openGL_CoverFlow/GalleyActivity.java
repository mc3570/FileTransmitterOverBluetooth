package openGL_CoverFlow;

import java.util.ArrayList;

import openGL_CoverFlow.CoverFlowOpenGL.CoverFlowListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;

public class GalleyActivity extends Activity {

//	private static int[] SAMPLE_IMAGES = new int[] {
//        
//		R.drawable._pic1,
//		R.drawable._pic2,
//		R.drawable._pic3,
//		R.drawable._pic4,
//		R.drawable.desert,
//		R.drawable.flower
//	};
	
//	private static String[] SAMPLE_IMAGES;
	
	private static CoverFlowOpenGL gl;
	
	private static ArrayList<String> dirList;
	
	private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(GalleyActivity.this, (String) msg.obj, 1000);
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		//ArrayList<String> nameList = extras.getStringArrayList("nameList");
		dirList = extras.getStringArrayList("dirList");
		Log.v("dirList", "DirList size" + dirList.size());
		
		gl = new CoverFlowOpenGL(getApplicationContext());
		gl.setCoverFlowListener(new CoverFlowListener() {
			
			@Override
			public void topTileClicked(CoverFlowOpenGL view, int position) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void tileOnTop(CoverFlowOpenGL view, int position) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Bitmap getImage(CoverFlowOpenGL anotherCoverFlow, int position) {
				// TODO Auto-generated method stub
				//input the sample images
				return BitmapFactory.decodeFile(dirList.get(position));
			}
			
			@Override
			public int getCount(CoverFlowOpenGL view) {
				// TODO Auto-generated method stub
				//input the length
				return dirList.size();
			}
		});
		//set background
		gl.setBackgroundTexture(R.drawable.background3);
		//set View as gl
		setContentView(gl);
	}
	
	
}
