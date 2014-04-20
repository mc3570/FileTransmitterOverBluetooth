package tabs_and_fragments;

import java.util.ArrayList;
import java.util.HashMap;

import tabs_and_fragments.FragmentA.OnListItemSelectedlistener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.Mengchen_Zhang.filetransmitteroverbluetooth.R;

@SuppressLint("NewApi")
public class TabActivity extends FragmentActivity implements TabListener, Communicator, OnListItemSelectedlistener{

	private ActionBar actionBar;
	private ViewPager viewPager;
	private Handler handler;
	
	private static ArrayList<String> toNameFragmentC = new ArrayList<String>();
	private static ArrayList<String> toTypeFragmentC = new ArrayList<String>();
	private static ArrayList<String> toUrlFragmentC = new ArrayList<String>();
	private static ArrayList<String> toIdFragmentC = new ArrayList<String>();
	private static ArrayList<String> toSizeFragmentC = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.listactivity_for_folder);
		
		viewPager = (ViewPager) findViewById(R.id.pagerForFolders);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Available");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Receive");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText("Recent");
		tab3.setTabListener(this);
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void respond(ArrayList<HashMap<String, Object>> clickedData) {
//		FragmentManager manager = getSupportFragmentManager();
//		FragmentC fc = (FragmentC) manager.findFragmentById(R.id.fragment_c);
//		fc.getHistoryArray(clickedData);
		
	}

	@Override
	public void onItemSelected(ArrayList<String> nameFragment,
			ArrayList<String> typeFragment, ArrayList<String> urlFragment,
			ArrayList<String> sizeFragment, ArrayList<String> idFragment) {
		
		
	}

}

class MyAdapter extends FragmentPagerAdapter{

	public MyAdapter(FragmentManager fm) {
		super(fm);
	}
	
	//do something when each fragment is clicked.
	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = null;
		if(arg0 == 0){
			fragment = new FragmentA();
		}
		else if(arg0 == 1){
			fragment = new FragmentB();
		}
		else if(arg0 == 2){
			fragment = new FragmentC();
		}
		return fragment;
	}

	//There are three fragments attached to this fragment.
	@Override
	public int getCount() {
		return 3;
	}
	
}
