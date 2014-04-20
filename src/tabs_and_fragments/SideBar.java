package tabs_and_fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View{
	
	private final static String[] AlphaBetic = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
		"N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
	private final int chosen = -1;
	private TextView mTextDialog;
	private Paint mPaint = new Paint();
	
	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TextView getmTextDialog() {
		return mTextDialog;
	}

	public void setmTextDialog(TextView mTextDialog) {
		this.mTextDialog = mTextDialog;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//Get view height and length
		int mHeight = getHeight();
		int mWidth = getWidth();
		//set height for each letter
		int mSingleHeight = mHeight / AlphaBetic.length;
		
		for (int i = 0; i < AlphaBetic.length; i++){
			//light bue
			mPaint.setColor(Color.rgb(192, 217, 217));
			mPaint.setTypeface(Typeface.DEFAULT_BOLD);
			//Smooth the edge.
			mPaint.setAntiAlias(true);
			mPaint.setTextSize(20);
			
			if(i == chosen){
				mPaint.setColor(Color.parseColor("#CFB53B"));
				mPaint.setFakeBoldText(true);
			}
			
			float xPos = mWidth / 2 - mPaint.measureText(AlphaBetic[i]) / 2;
			float yPos = mSingleHeight * i + mSingleHeight;
			canvas.drawText(AlphaBetic[i], xPos, yPos, mPaint);
			mPaint.reset();
		}
	}
	
	

}
