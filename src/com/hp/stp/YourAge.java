package com.hp.stp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class YourAge extends Activity implements OnClickListener {

	TextView t1, t2;
	Button b1, b2;
	int bdte, bmnt, byr;
	int adate, amnth, ayear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_your_age);
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		
		Bundle in=getIntent().getExtras();
		bdte=in.getInt("bdate");
		bmnt=in.getInt("bmonth");
		byr=in.getInt("byear");
		adate=in.getInt("agedate");
		amnth=in.getInt("agemonth");
		ayear=in.getInt("ageyear");
		
		t1.setText("User is "+bdte+"th flower of "+bmnt+"th garden, "+byr+"th park.");
		t2.setText("Age is "+ayear+" years, "+amnth+" months, "+adate+" days.");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try{
			switch(v.getId()) {
		
		case R.id.button1:
			Intent i=new Intent(this, SaveABirthday.class);	//how to show list of bdays
			i.putExtra("bdte", bdte);
			i.putExtra("bmnt", bmnt);
			i.putExtra("byr", byr);
			startActivity(i);
			break;
		case R.id.button2:
			Intent in=new Intent(this, SaveMyBirthday.class);
			in.putExtra("bdte", bdte);
			in.putExtra("bmnt", bmnt);
			in.putExtra("byr", byr);
			startActivity(in);
			break;
		default:
			
			break;
		}
		}catch(Exception e)
		{
			Toast.makeText(this, ""+e.getMessage(), 2000).show();
		}
		
		
	}

}

