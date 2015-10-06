package com.hp.stp;		//stores file in sdcard

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Birthday extends Activity implements OnCompletionListener  {

	TextView t;
	MediaPlayer mp;
	private Uri fileUri;
	Button b1, b2, b3;		//b3 is shoot, 1 capture, 2 stop
	int bdte, bmnt, byr;	//to get passed strings in int, d/m/y
	int byear;	//to get strings passed by prev activity, d/m/y
	public static final int MEDIA_TYPE_IMAGE=1;
	public static final int MEDIA_TYPE_VIDEO=2;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_birthday);
		t=(TextView)findViewById(R.id.textView1);
		
		mp=MediaPlayer.create(this, R.raw.happy);
		for(int i=1;i<2;i++)		//for loop only 1 time
		{
			if(!mp.isPlaying())
			mp.start();
		}
		
		Bundle in=getIntent().getExtras();
		bdte=in.getInt("bdate");
		bmnt=in.getInt("bmonth");
		byr=in.getInt("byear");
		byear=in.getInt("ageyear");
		
		t.setText("Age is "+byear+" years!!!");
	}

		private static Uri getImageUri(int type)
		{
	      return Uri.fromFile(getOutputMediaFile(type));
		}
	
		@SuppressLint("SimpleDateFormat") 
		private static File getOutputMediaFile(int type)
		{
			File mediaStorageDir=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "Birthday Bash");
			//created folder "birthdaybash" is visible in file explorer of mobile, but not visible in laptop when connected
			
			// Create the storage directory if it does not exist
		    if (!mediaStorageDir.exists())
		    	{
		        mediaStorageDir.mkdirs();
		        }

			String ts=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File mediaFile;
			if(type==MEDIA_TYPE_IMAGE)
			{
				mediaFile=new File(mediaStorageDir+File.separator+"IMG_"+ts+".jpg");
			}
			else if(type==MEDIA_TYPE_VIDEO)
			{
				mediaFile=new File(mediaStorageDir+File.separator+"VID_"+ts+".mp4");
			}
			else return null;
			return mediaFile;
		}
		
	public void cameras(View v) 	//on click listener
	{
		Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri=getImageUri(MEDIA_TYPE_IMAGE);	//create a file to save the image
		i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	public void stopsong(View v) 
	{		//on click listener for stop song
		try{
		if(mp.isPlaying())
		{
			mp.stop();
			mp.release();
		}
		}catch(Exception e){}
	}
	
	public void videos(View v) 
	{		//onclick listener for videos
		Intent i=new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		fileUri=getImageUri(MEDIA_TYPE_VIDEO);
		i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		startActivityForResult(i, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
	try{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
		{
			if(resultCode==RESULT_OK)		//image captured and saved to fileUri
				Toast.makeText(this, "Image saved "+data.getData(), 3000).show();		//error in this line
		}
		
		if(requestCode==CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE)
		{
			if(resultCode==RESULT_OK)
			Toast.makeText(this, "Save video "+data.getData(), 5000).show();
		}
	}catch(Exception e)
	{
		Toast.makeText(this, e.getMessage(), 3000).show();
	}
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.stop();
		mp.release();
	}
}