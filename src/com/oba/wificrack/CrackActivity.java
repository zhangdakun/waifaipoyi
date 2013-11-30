// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.oba.wificrack;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mywifi02.parser.R;

// Referenced classes of package com.oba.wificrack:
//            DialogActivity

public class CrackActivity extends Activity {
	private PowerManager powerManager = null;
	private WakeLock wakeLock = null;
	private Button crack1;
	private Button crack2;
	private boolean fast;
	private int gold;
	// private Handler handler;
	private ImageView levelView;
	private String name = "";
	private String cap = "[WPA2-PSK-TKIP][ESS]";
	private TextView nameView = null;
	private TextView showItemView[];
	private LinearLayout showListView;
	private boolean start;
	private int startInt = 0x989680;
	private Context mContext;

	private final String TAG = "crack";
	String pw = "12345";

	private int startIntshow = 0x989680;
	private Handler handler = new Handler() {

		public void handleMessage(Message message) {
			int i = startIntshow;
			int j = 0;
			do {
				startIntshow++;
				if (j >= showItemView.length) {
					// TextView textview;
					// StringBuilder stringbuilder;
					// int k = 0;
					if (start)
						if (fast)
							handler.sendEmptyMessageDelayed(0, 100L);
						else
							handler.sendEmptyMessageDelayed(0, 1000L);
					return;
				}
				TextView textview = showItemView[j];
				StringBuilder stringbuilder = new StringBuilder();
				int k = i + 1;
				textview.setText(stringbuilder
						.append(Integer.toBinaryString(i)).toString());
				// textview.setText(stringbuilder.append(pw).toString());
				j++;
				i = k;
			} while (true);
		}

	};

	public boolean connectWifi(String s, String s1) {
		// s1 = "eben123456789";
		WifiManager wifimanager = (WifiManager) getSystemService("wifi");
		// WifiConfiguration wificonfiguration = new WifiConfiguration();
		// wificonfiguration.SSID = (new
		// StringBuilder("\"")).append(s).append("\"").toString();
		// wificonfiguration.preSharedKey = (new
		// StringBuilder("\"")).append(s1).append("\"").toString();
		// wificonfiguration.hiddenSSID = true;
		// wificonfiguration.status = 2;
		// wificonfiguration.allowedGroupCiphers.set(2);
		// wificonfiguration.allowedGroupCiphers.set(3);
		// wificonfiguration.allowedKeyManagement.set(1);
		// wificonfiguration.allowedPairwiseCiphers.set(1);
		// wificonfiguration.allowedPairwiseCiphers.set(2);
		// wificonfiguration.allowedProtocols.set(1);

		WifiConfiguration wc = new WifiConfiguration();
		wc.allowedAuthAlgorithms.clear();
		wc.allowedGroupCiphers.clear();
		wc.allowedKeyManagement.clear();
		wc.allowedPairwiseCiphers.clear();
		wc.allowedProtocols.clear();

		wc.SSID = (new StringBuilder("\"")).append(s).append("\"").toString();
		wc.preSharedKey = (new StringBuilder("\"")).append(s1).append("\"")
				.toString();
		wc.hiddenSSID = true;
		wc.status = WifiConfiguration.Status.ENABLED;
		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		if (cap.contains("WPA-PSK"))
			wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		else
			wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);

		// config.hiddenSSID = true;
		// config.allowedAuthAlgorithms
		// .set(WifiConfiguration.AuthAlgorithm.OPEN);
		// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		// config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		// config.allowedPairwiseCiphers
		// .set(WifiConfiguration.PairwiseCipher.TKIP);
		// config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		// config.status = WifiConfiguration.Status.ENABLED;

		// WifiConfiguration wificonfiguration =
		// CreateWifiInfo(s,s1,WifiCipherType.WIFICIPHER_WPA);
		// WifiConfiguration.AuthAlgorithm.SHARED;

		// int networkId = wifimanager.addNetwork(wificonfiguration);
		// if(networkId>0) {
		// // return true;
		// }
		// int id = wifimanager.updateNetwork(wificonfiguration);
		// if(id > 0) {
		// return true;
		// }
		// android.net.wifi.SCAN_RESULTS
		int res = wifimanager.addNetwork(wc);
		Log.d(TAG, "add Network returned " + res);
		boolean b = wifimanager.enableNetwork(res, true);
		Log.d(TAG, "enableNetwork returned " + b);

		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		while (i < 6) {
			WifiInfo wifiinfo = wifimanager.getConnectionInfo();
			Log.d(TAG, "wifiinfo " + wifiinfo);
			if (wifiinfo.getSupplicantState() == SupplicantState.COMPLETED) {
				return true;
			}
			// else if(wifiinfo.getSupplicantState() ==
			// SupplicantState.DISCONNECTED) {
			// return false;
			// }
			if (wifiinfo.getSupplicantState() != SupplicantState.SCANNING) {
				i++;
			}
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// wifimanager.getScanResults();
		// Iterator iterator = wifimanager.getScanResults().iterator();
		// do
		// {
		// if(!iterator.hasNext())
		// return false;
		// wifimanager.disableNetwork(((ScanResult)iterator.next()).frequency);
		// } while(true);
		return false;
	}

	public void crack() {
		Log.d("crack", "name : " + name);
//
//		(new Thread() {
//			int index = 0;
//			int type = 0;
//			String lowName = name.toLowerCase();
//			String firstUpname = name.substring(0, 1).toUpperCase()
//					+ name.substring(1);;
//			String reversename = (new StringBuilder()).append(startInt)
//					.toString();;
//
//			public void run() {
//				do {
//
//					do {
//						if (!start)
//							return;
//						CrackActivity crackactivity = CrackActivity.this;
//
//						if (index < Contants.common.length) {
//							pw = Contants.common[index];
//							if (1 == type) {
//								pw = lowName + pw;
//							} else if (2 == type) {
//								pw = firstUpname + pw;
//							} else if (3 == type) {
//								pw = reversename + pw;
//							}
//							index++;
//						} else if (0 == type) {
//							type++;
//							index = 0;
//							pw = lowName;
//						} else if (1 == type) {
//							type++;
//							index = 0;
//							pw = firstUpname;
//						} else if (2 == type) {
//							type++;
//							index = 0;
//							pw = reversename;
//						} else {
//							crackactivity.startInt = 1 + crackactivity.startInt;
//
//							pw = (new StringBuilder()).append(startInt)
//									.toString();
//						}
//
//						Log.d(TAG, "name : " + name + ",try pw : " + pw);
//					} while (!connectWifi((new StringBuilder()).append(name)
//							.toString(), pw));
//					PointsManager.getInstance(mContext).spendPoints(
//							PointsManager.getInstance(mContext).queryPoints());
//					finishCrack();
//					Intent intent = new Intent(CrackActivity.this,
//							DialogActivity.class);
//					intent.putExtra("name", (new StringBuilder()).append(name)
//							.toString());
//					// intent.putExtra("pass", (new
//					// StringBuilder()).append(startInt).toString());
//					intent.putExtra("pass", (new StringBuilder()).append(pw)
//							.toString());
//					startActivity(intent);
//				} while (true);
//			}
//
//		}).start();
	}

	// public enum WifiCipherType {
	// WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
	// }
	public enum WifiCipherType {
		WIFICIPHER_WEP, WIFICIPHER_WPA
	}

	private WifiConfiguration CreateWifiInfo(String SSID, String Password,
			WifiCipherType Type) {
		WifiConfiguration config = new WifiConfiguration();
		config.allowedAuthAlgorithms.clear();
		config.allowedGroupCiphers.clear();
		config.allowedKeyManagement.clear();
		config.allowedPairwiseCiphers.clear();
		config.allowedProtocols.clear();
		config.SSID = "\"" + SSID + "\"";
		// if (Type == WifiCipherType.WIFICIPHER_NOPASS) {
		// config.wepKeys[0] = "";
		// config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
		// config.wepTxKeyIndex = 0;
		// }
		if (Type == WifiCipherType.WIFICIPHER_WEP) {
			config.preSharedKey = "\"" + Password + "\"";
			config.hiddenSSID = true;
			config.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.SHARED);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
			config.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.WEP104);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		}
		if (Type == WifiCipherType.WIFICIPHER_WPA) {
			config.preSharedKey = "\"" + Password + "\"";
			config.hiddenSSID = true;
			config.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.OPEN);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			config.status = WifiConfiguration.Status.ENABLED;
		} else {
			return null;
		}
		return config;
	}

	public void finishCrack() {
		start = false;
		handler.removeMessages(0);
	}

	public int getLevelImgId(int i) {
		int j;
		if (i > -51)
			j = R.drawable.ic_wifi_lock_signal_4;
		else if (i > -70)
			j = R.drawable.ic_wifi_lock_signal_3;
		else if (i > -80)
			j = R.drawable.ic_wifi_lock_signal_2;
		else
			j = R.drawable.ic_wifi_lock_signal_1;
		return j;
	}

	public void getUpdatePoints(String s, int i) {
		gold = i;
	}

	public void getUpdatePointsFailed(String s) {
	}



	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_crack);
		// AdManager.getInstance(this).init("cfd8b7d55625512e",
		// "768f697bbf2f8807", false);
		// LinearLayout adLayout = (LinearLayout)
		// findViewById(R.id.adLayoutcrack);
		// AdView adView = new AdView(this, AdSize.SIZE_320x50);
		// adLayout.addView(adView);
		
		mContext = this;

		this.powerManager = (PowerManager) this
				.getSystemService(this.POWER_SERVICE);
		this.wakeLock = this.powerManager.newWakeLock(
				PowerManager.FULL_WAKE_LOCK, this.TAG);

		crack1 = (Button) findViewById(0x7f060002);
		crack2 = (Button) findViewById(0x7f060003);
		nameView = (TextView) findViewById(0x7f06000b);
		levelView = (ImageView) findViewById(0x7f06000a);
		showListView = (LinearLayout) findViewById(0x7f060004);
		name = getIntent().getExtras().getString("name");
		cap = getIntent().getExtras().getString("cap");
		nameView.setText((new StringBuilder()).append(name).toString());
		levelView.setImageResource(getLevelImgId(getIntent().getExtras()
				.getInt("level")));
		showItemView = new TextView[30];
		int i = 0;
		do {
			if (i >= 30) {
				crack1.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {

						if (start)
							fast = false;
						else
							startCrack(false);
					}

				});
				crack2.setOnClickListener(new android.view.View.OnClickListener() {

					public void onClick(View view) {


						if (start)
							fast = true;
						else
							startCrack(true);

					}

				});
				return;
			}
			showItemView[i] = (TextView) LayoutInflater.from(this).inflate(
					0x7f030003, null);
			showItemView[i].setText(Integer.toBinaryString(i + startInt));
			showListView.addView(showItemView[i]);
			i++;
		} while (true);
	}

	protected void onPause() {
		super.onPause();
		// finishCrack();
		this.wakeLock.release();
	}

	protected void onResume() {
		super.onResume();
		this.wakeLock.acquire();
	}

	public void startCrack(boolean flag) {
		fast = flag;
		start = true;
		handler.sendEmptyMessage(0);
		crack();
	}

}
