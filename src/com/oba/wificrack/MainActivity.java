// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.oba.wificrack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.offers.OffersManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.System;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.adlib.AD;
import com.android.mywifi02.parser.R;
import com.oba.wificrack.view.GridAdapter;
import com.oba.wificrack.view.ListAdapter;
import com.oba.wificrack.view.ListVo;

// Referenced classes of package com.oba.wificrack:
//            CrackActivity

public class MainActivity extends com.android.adlib.ADbaseActivity {
	private SearchAnimationWidget aniWiget;

	class WifiReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			handler.sendEmptyMessage(1);
			infos.clear();
			wifiList = mainWifi.getScanResults();
			Iterator iterator = wifiList.iterator();
			do {
				if (!iterator.hasNext()) {
					adapter.notifyDataSetChanged();
					gridAdapter.notifyDataSetChanged();
					return;
				}
				ScanResult scanresult = (ScanResult) iterator.next();
				Log.d("crack", "ccanresult : " + scanresult);
				// scanresult.capabilities
				ListVo vo = new ListVo(scanresult.SSID, scanresult.level, false);
				vo.setCapabilities(scanresult.capabilities);
				infos.add(vo);
			} while (true);
		}

	}

	public MainActivity() {
		handler = new Handler() {

			public void handleMessage(Message message) {
				switch (message.what) {
				case 0:
					scan();
					break;
				case 1:
					scanFinish();
					break;
				default:
					scanFinish();
					break;
				}
				
				
			}

		};
		temp = 0;
	}

	private void scanThread() {
		scan = true;
		(new Thread() {

			public void run() {
				do {
					if (!scan)
						return;
					handler.sendEmptyMessage(0);
					try {
						sleep(1000L);
					} catch (InterruptedException interruptedexception) {
						interruptedexception.printStackTrace();
					}
				} while (true);
			}

		}).start();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
		Intent in = intent;
		
		if(null == in) {
			Log.e("crack", "onNewIntent in is null");
		}
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		// new AppPosterManager(this);
		setContentView(R.layout.activity_main);
		 AdManager.getInstance(this).init("569b04c694c636f6",
		 "514add3245d36421", false);
		 LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
		 AdView adView = new AdView(this, AdSize.SIZE_320x50);
		 adLayout.addView(adView);
		 
		 Bundle bl = bundle;
		 if(null == bl){
			 Log.e("crack", "bl is null");
		 }
		 
		 Intent in = getIntent();
		 if(null != in) {
//			 in.
		 Uri u = in.getData();
		 Uri[] ua =   (Uri[]) in.getParcelableArrayExtra(Intent.EXTRA_STREAM);
		 
		 ArrayList ual = in.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
		 
		 Uri ur1 = in.getParcelableExtra(Intent.EXTRA_STREAM);
		 
//		 in.getp
//		 in.get
//		 in.getc
//		 Intent.EXTRA_STREAM;
		 if(null == u)  {
			 Log.e("crack", "uri null");
		 }
//		 u.
		 }
//		 long current = java.lang.System.currentTimeMillis();
//		 SharedPreferences sp = getSharedPreferences("myset", 0);
//		 long first = sp.getLong("first", 0);
//		 if( 0== first) {
//			 sp.edit().putLong("first", current).commit();
//		 } else {
////			 if(current > first && (current - first) >  )
//			 Log.d("crack", "used : "+ (current-first));
//			 if(first > current || (current-first)>30*24*60*60*1000L) {
//				 
//			 }
//		 }
		 AD.checkSpend(this);

        float density = getResources().getDisplayMetrics().density;
        width = (int) (getWindowManager().getDefaultDisplay().getWidth()/density);
        
		infos = new ArrayList();
		adapter = new ListAdapter(this, infos);
		gridAdapter = new GridAdapter(this, infos);

		listView = (ListView) findViewById(R.id.wifi_list);

		
		gridView_wifi = (GridView) findViewById(R.id.gridView_wifi);
		// listView.setAdapter(adapter);
		gridView_wifi.setAdapter(gridAdapter);
		gridView_wifi.setHorizontalSpacing(20);
		gridView_wifi.setVerticalSpacing(40);
//		gridView_wifi.
		
		scanBt = (Button) findViewById(R.id.bt_scan);
		crackBt = (Button) findViewById(R.id.bt_crack);
		aniWiget = (com.oba.wificrack.SearchAnimationWidget) findViewById(R.id.wt_radar_layer);
		gridView_wifi
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView adapterview, View view,
							int i, long l) {

						ListVo listvo = (ListVo) infos.get(i);

						Intent intent = new Intent(MainActivity.this,
								DialogCrackActivity.class);		
						
						intent.putExtra("name", listvo.getName());
						intent.putExtra("level", listvo.getLevel());
						intent.putExtra("cap", listvo.getCapabilities());
						startActivity(intent);
//						showCrackDialog();
					}
				});
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView adapterview, View view, int i,
					long l) {
				// Iterator iterator = infos.iterator();
				// do
				// {
				// if(!iterator.hasNext())
				// {
				// ((ListVo)infos.get(i)).setFocusing(true);
				// adapter.notifyDataSetChanged();
				// return;
				// }
				// ((ListVo)iterator.next()).setFocusing(false);
				// } while(true);
				ListVo listvo = (ListVo) infos.get(i);
				Intent intent = new Intent(MainActivity.this,
						CrackActivity.class);
				intent.putExtra("name", listvo.getName());
				intent.putExtra("level", listvo.getLevel());
				intent.putExtra("cap", listvo.getCapabilities());
				startActivity(intent);
			}

		});
		crackBt.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				ListVo listvo;
				Iterator iterator;
				listvo = null;
				iterator = infos.iterator();
				ListVo listvo1;
				while (iterator.hasNext()) {
					listvo1 = (ListVo) iterator.next();
					if (listvo1.isFocusing()) {
						// goto _L4; else goto _L3
						listvo = listvo1;
						break;
					}
					// else{
					//
					// }
				}

				if (listvo == null) {
					(new android.app.AlertDialog.Builder(MainActivity.this))
							.setMessage(
									"\u8BF7\u5148\u9009\u62E9\u9700\u8981\u7834\u89E3\u7684WIFI\u8D26\u53F7.")
							.setPositiveButton(
									"\u786E\u5B9A",
									new android.content.DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialoginterface,
												int i) {
										}

									}).show();
				} else {
					Intent intent = new Intent(MainActivity.this,
							CrackActivity.class);
					intent.putExtra("name", listvo.getName());
					intent.putExtra("level", listvo.getLevel());
					intent.putExtra("cap", listvo.getCapabilities());
					startActivity(intent);
				}
			}
		}

		);
		scanBt.setOnClickListener(new android.view.View.OnClickListener() {

			public void onClick(View view) {
				mainWifi.startScan();
				scanThread();
			}

		});
		mainWifi = (WifiManager) getSystemService("wifi");
		receiverWifi = new WifiReceiver();
		registerReceiver(receiverWifi, new IntentFilter(
				"android.net.wifi.SCAN_RESULTS"));
		mainWifi.startScan();
		scanThread();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	protected void onPause() {
		unregisterReceiver(receiverWifi);
		super.onPause();
	}

	protected void onResume() {
		registerReceiver(receiverWifi, new IntentFilter(
				"android.net.wifi.SCAN_RESULTS"));
		super.onResume();
	}

	public void scan() {
		setTitle(getString(R.string.searching));
//		int i = temp;
//		temp = i + 1;
//		int j = 3 - i % 3;
//		String s = "";
//		do {
//			if (j >= 3) {
////				setTitle((new StringBuilder(
////						"WIFI\u7834\u89E3 -\u626B\u63CF\u4E2D")).append(s)
////						.toString());
//				return;
//			}
//			s = (new StringBuilder(String.valueOf(s))).append(".").toString();
//			j++;
//		} while (true);
	}

	public void scanFinish() {
		scan = false;
		setTitle(getString(R.string.app_name));
	}

	// boolean
	public void onSearchonther(View v) {
		Log.d("crack", "onSearchonther");

		OffersManager.getInstance(this).showOffersWall();

	}

	public void onSearchDevice(View v) {
		Log.d("crack", "onSearchDevice");

		mainWifi.startScan();
		scanThread();

		aniWiget.start();
//		 ImageView rador = (ImageView) findViewById(R.id.wt_rador);
		// rador.setVisibility(View.VISIBLE);
		//
		// ScaleAnimation localScaleAnimation = new ScaleAnimation(1F, 14.0F,
		// 1F, 14.0F, 1, 0.5F, 1, 0.5F);
		// localScaleAnimation.setDuration(1000L);
		// localScaleAnimation.setFillEnabled(true);
		// localScaleAnimation.setFillBefore(true);
		// localScaleAnimation.setRepeatCount(4);
		// localScaleAnimation.setStartOffset(333);
		// localScaleAnimation.setInterpolator(new
		// AccelerateDecelerateInterpolator());
		// localScaleAnimation.setAnimationListener(null);
		// rador.setAnimation(localScaleAnimation);
		// rador.startAnimation(localScaleAnimation);
		// localScaleAnimation.start();
	}
	
	private void showCrackDialog() {
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		
		Window window = dialog.getWindow();
		window.setContentView(R.layout.activity_crack_dialog);
		
	}



	private ListAdapter adapter;
	private GridAdapter gridAdapter;
	private Button crackBt;
	private Handler handler;
	private List infos;
	private ListView listView;
	private GridView gridView_wifi;
	private WifiManager mainWifi;
	private WifiReceiver receiverWifi;
	private boolean scan;
	private Button scanBt;
	int temp;
	private List wifiList;

	int width;

}
