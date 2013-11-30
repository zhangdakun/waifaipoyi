package com.oba.wificrack;
import java.lang.ref.SoftReference;

import com.android.mywifi02.parser.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SearchAnimationWidget extends FrameLayout {

	private ImageView[] a;
	private SoftReference<Bitmap> b;

//	priv

	public SearchAnimationWidget(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		c(paramContext);
	}

	public SearchAnimationWidget(Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		c(paramContext);
	}

	private void c(Context context) {
//		LinearLayout layout = new LinearLayout(context);
		this.a = new ImageView[3];
//		setContentView();
//		addView(LayoutInflater.from(getContext()).inflate(
//				R.layout.wt_search_device_anima, this));
//		View rl =
//		(View) LayoutInflater.from(getContext()).inflate(
//				R.layout.wt_search_device_anima, layout);
		LayoutInflater.from(getContext()).inflate(
				R.layout.wt_search_device_anima, this);
		this.a[0] = ((ImageView) findViewById(R.id.radar_ray_1));
		this.a[1] = ((ImageView) findViewById(R.id.radar_ray_2));
		this.a[2] = ((ImageView) findViewById(R.id.radar_ray_3));
		
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        params.gravity = Gravity.NO_GRAVITY;
//        addView(layout, params);  		
	}
	
	class Listener implements AnimationListener {
		
		private ImageView v;
		public Listener(ImageView v) {
			super();
			this.v = v;
		}

		
		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			v.setVisibility(View.GONE);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
	  public final void start()
	  {		
		  long l = 333L ;
//			this.a[0] = ((ImageView) findViewById(R.id.radar_ray_1));
//			this.a[1] = ((ImageView) findViewById(R.id.radar_ray_2));
//			this.a[2] = ((ImageView) findViewById(R.id.radar_ray_3));
		  for(ImageView view:a) {
			  view.setVisibility(View.VISIBLE);
//			  Animation.RELATIVE_TO_SELF;
			  ScaleAnimation localScaleAnimation = new ScaleAnimation(1F, 14.0F, 1F, 14.0F, 1, 0.5F, 1, 0.5F);
		        localScaleAnimation.setDuration(1000L);
		        localScaleAnimation.setFillEnabled(true);
		        localScaleAnimation.setFillBefore(true);
		        localScaleAnimation.setRepeatCount(4);
		        localScaleAnimation.setStartOffset(l);
		        localScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		        localScaleAnimation.setAnimationListener(new Listener(view));
		        view.setAnimation(localScaleAnimation);
		        view.startAnimation(localScaleAnimation);	
//		        localScaleAnimation.start();
		        l += l;
		  }
//	    if (this.b == null);
//	    try
//	    {
//	      this.b = new SoftReference(BitmapFactory.decodeStream(getContext().getResources().openRawResource(R.drawable.wifi_body_ripple)));
//	      if (this.b != null)
//	        break label84;
//	      label84: return;
//	    }
//	    catch (Exception localException)
//	    {
////	      LoggerFactory.getLogger("SearchAnimationWidget").warn(Log.getStackTraceString(localException));
//	    }
//	    catch (OutOfMemoryError localOutOfMemoryError)
//	    {
//	      int i;
//	      do
//	      {
//	        while (true)
//	        {
////	          LoggerFactory.getLogger("SearchAnimationWidget").warn(Log.getStackTraceString(localOutOfMemoryError));
//	          System.gc();
//	        }
//	        i = 0;
//	      }
//	      
////	      View.g
//	      while (i >= this.a.length);
//	      ImageView localImageView = this.a[i];
//	      localImageView.setImageBitmap((Bitmap)this.b.get());
//	      localImageView.setVisibility(0);
//	      long l = 333L * i;
//	      if (localImageView.getAnimation() != null)
//	        localImageView.getAnimation().start();
//	      while (true)
//	      {
//	        while (true)
//	          ++i;
//	        ScaleAnimation localScaleAnimation = new ScaleAnimation(1F, 14.0F, 1F, 14.0F, 1, 0.5F, 1, 0.5F);
//	        localScaleAnimation.setDuration(1000L);
//	        localScaleAnimation.setFillEnabled(true);
//	        localScaleAnimation.setFillBefore(true);
//	        localScaleAnimation.setRepeatCount(4);
//	        localScaleAnimation.setStartOffset(l);
//	        localScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
////	        localScaleAnimation.setAnimationListener(new d(this, localImageView));
//	        localImageView.setAnimation(localScaleAnimation);
//	        localImageView.startAnimation(localScaleAnimation);
//	      }
//	    }
	  }
	  
	  
}
