package com.hp.stp;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SavedNames extends Activity {

	TextView t1, t2;
	Button b1;
	SQLiteDatabase sd;
	@SuppressLint("SdCardPath") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_names);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		//t3=(TextView)findViewById(R.id.textView3);
		
		try { 
			sd = SQLiteDatabase.openDatabase(
			"/data/data/com.hp.stp/databases/birthdaybash", null, SQLiteDatabase.OPEN_READWRITE);
			}
			catch (SQLiteException e) {
			Toast.makeText(this, ""+e.getMessage(), 5000).show(); 
			}
		
		Cursor c=sd.rawQuery("select * from details", null);
		if(c.moveToFirst())
		{
			t1.setText("Name: "+c.getString(1));
			t2.setText("D.O.B: "+c.getInt(2)+"/"+c.getInt(3)+"/"+c.getInt(4));
		}
	}
	
	public void nextdata(View v) 
	{
		try{
			Cursor c=sd.rawQuery("select * from details", null);
			if(c.moveToFirst())
			{
				while(!c.isLast())
					{
						if (c.moveToNext()) 
							{
								t1.setText("Name: "+c.getString(1));
								t2.setText("D.O.B: "+c.getInt(2)+"/"+c.getInt(3)+"/"+c.getInt(4));
							}
					}
			}
			
			}catch(Exception e)
			{
				Toast.makeText(this, e.getMessage(), 2000).show();
			}
	}	

}
