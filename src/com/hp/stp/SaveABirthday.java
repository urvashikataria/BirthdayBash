package com.hp.stp;	

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ActionMode;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SaveABirthday extends Activity implements OnClickListener{

	int bdate, bmnth, byear;
	TextView t1, t2;
	EditText e1;
	Button b1, b2, b3;	//b3 sends ecard to frnd
	SQLiteDatabase sd;
	File f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_abirthday);
		f=Environment.getExternalStorageDirectory();
		//Toast.makeText(this, "external directory "+f, 10000).show();	// /storage/sdcard0
		sd=openOrCreateDatabase("birthdaybash", MODE_PRIVATE, null);	
		sd.execSQL("create table if not exists details(id integer primary key autoincrement, name varchar not null, dd int not null, mm int not null, yyyy int not null)");
		//sd.execSQL("alter table {birthday} add column id int primary key autoincrement");
		//DB causing problem
		Bundle b=getIntent().getExtras();
		bdate=b.getInt("bdte");
		bmnth=b.getInt("bmnt");
		byear=b.getInt("byr");
		
		
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		//t3=(TextView)findViewById(R.id.textView3);
		e1=(EditText)findViewById(R.id.editText1);
		//e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		b3=(Button)findViewById(R.id.button3);
		b2=(Button)findViewById(R.id.button2);
		b2.setOnClickListener(this);
		b1.setOnClickListener(this);
		b3.setOnClickListener(this);
		t2.setText("Birthdate is "+bdate+"-"+bmnth+"-"+byear+".");
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try{
		switch(v.getId()){
		
		case R.id.button1:	//save current birth date
			String names=e1.getText().toString();
			//String mail=e2.getText().toString();
			int dd=bdate;
			int mm=bmnth;
			int yyyy=byear;
			sd.execSQL("insert into details (name, dd, mm, yyyy) values('"+names+"', '"+dd+"', '"+mm+"', '"+yyyy+"')");
			Toast.makeText(this, names+"'s birthday saved.", Toast.LENGTH_SHORT).show(); 
			e1.setText("");
			//e2.setText("");
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(System.currentTimeMillis());
			calendar.clear();
			calendar.set(yyyy, mm, dd, 0, 0, 0);
			AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			Intent intent = new Intent(this, MyBroadcastReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
			calendar.add(Calendar.SECOND, 10);
			am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
			Toast.makeText(this, "Reminder set for "+dd+"/"+mm, Toast.LENGTH_SHORT).show();
		break;
		
		case R.id.button2:
			try{
					Intent i=new Intent(this, SavedNames.class);
					startActivity(i);
				}catch(Exception e){
					Toast.makeText(this, e.getMessage(), 5000).show(); }
		break;
			
		case R.id.button3:	//open browser
			Intent myActivity2 = new Intent(Intent.ACTION_VIEW);
			myActivity2.setData(Uri.parse("http://www.123greetings.com"));
			startActivity(myActivity2);
		break;
			default:
				break;
		}
		}catch(Exception e)
		{
			Toast.makeText(this, ""+e.getMessage(), 5000).show();
		}
	}	
}