package com.oba.wificrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adlib.AD;
import com.android.adlib.Crack;
import com.android.mywifi02.parser.R;

public class DialogCrackActivity extends com.android.adlib.ADbaseActivity {
	TextView text;
	String name;
	String cap;
	
	ProgressBar anim = null;
	ImageView image = null;
	
	Button btn1 ;
	Button btn2;
	
	boolean isfast = false;
	boolean isstart = false;
	
	Crack myCarsk;
	int showint = 100000000;
	Context mContext;
	class MyHandler extends Handler {
		Activity activiy;

		public MyHandler(Activity activiy) {
//			super();
			this.activiy = activiy;
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 100) {
				Toast.makeText(mContext, activiy.getString(R.string.crack_ok), Toast.LENGTH_LONG).show();
				AD.spendalloffers(activiy);
				activiy.finish();
				return;
			}
		}
		
	}
	MyHandler mHandler = null;
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crack_dialog);
//		getWindow().set
		showint = 100000000;
		mContext = this;
		
		mHandler = new MyHandler(this);
		
		text = (TextView) findViewById(R.id.textViewcrackdialog);
		text.setText(this.getText(R.string.todo));
		
		anim = (ProgressBar) findViewById(R.id.progressBar);
		image = (ImageView) findViewById(R.id.imageView1);
		anim.setVisibility(View.INVISIBLE);
		image.setVisibility(View.VISIBLE);
		
		btn1 = (Button) findViewById(R.id.fastcrack);
		btn2 = (Button) findViewById(R.id.goodcrack);
		
		name = getIntent().getExtras().getString("name");
		cap = getIntent().getExtras().getString("cap");
		
		
		
		myCarsk = new Crack(this,mHandler);
	}

	
	public void onbtnclick(View v) {
		
		switch(v.getId()) {
//		case R.id.fastcrack:

//			break;
		case R.id.goodcrack:
			if(!AD.haveOffers(this)){
				return;
			}
		case R.id.fastcrack:
			if (!isstart) {
				anim.setVisibility(View.VISIBLE);
				image.setVisibility(View.INVISIBLE);
				isstart = true;
				mHandler.sendEmptyMessage(0);
				// new Crack(this,mHandler).crack(name, cap, this);
				myCarsk.crack(name, cap, this);
				
				btn1.setVisibility(View.INVISIBLE);
				btn2.setVisibility(View.INVISIBLE);
				// finishcrask(isok);
			}
			break;
		default:
			break;
		}
		
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		myCarsk.stop();
	}


	private void finishcrask(boolean isok) {
		if(isok) {
			Intent intent = new Intent(this,
					DialogActivity.class);
			intent.putExtra("name", (new StringBuilder()).append(name)
					.toString());
			// intent.putExtra("pass", (new
			// StringBuilder()).append(startInt).toString());
			intent.putExtra("pass", (new StringBuilder()).append("")
					.toString());
			startActivity(intent);
		}		
	}
}
