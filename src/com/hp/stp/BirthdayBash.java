package com.hp.stp;	//show mybday,save cam images

import java.util.Calendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BirthdayBash extends Activity implements OnClickListener {

	TextView t, tv;
	EditText e1, e2, e3;
	Button b;
	String bidate, bimonth, biyear;
	int thisdate, thismonth, tm, thisyear, bdate, bmonth, byear, agedate, agemonth, ageyear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_birthday_bash);
		t=(TextView)findViewById(R.id.textView1);
		tv=(TextView)findViewById(R.id.textView2);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		final Calendar c = Calendar.getInstance();
	    thisyear = c.get(Calendar.YEAR);
	    thismonth = c.get(Calendar.MONTH);
	    thisdate = c.get(Calendar.DAY_OF_MONTH);
	    tm=thismonth+1;
	    // set current date into textview
	    tv.setText("Today's date "+thisdate+"-"+tm+"-"+thisyear);
	 // Month is 0 based, just add 1
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		bidate=e1.getText().toString();
		bdate=Integer.parseInt(bidate);
		bimonth=e2.getText().toString();
		bmonth=Integer.parseInt(bimonth);
		biyear=e3.getText().toString();
		byear=Integer.parseInt(biyear);
		//Toast.makeText(this, "Today's date "+thisdate+"-"+tm+"-"+thisyear, Toast.LENGTH_SHORT).show();
		try{
		if(byear<=thisyear && bdate<=31 && bmonth<=12)	//mandatory condition
		{
			if(bdate>thisdate && bmonth<tm)	//1. 12/6/1995, 10/7/2015
			{
				ageyear=thisyear-byear;
				agemonth=tm-bmonth-1;
				agedate=(30-bdate)+thisdate;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth>tm && bdate<thisdate)	//2. 6/8/1995, 10/7/2015
			{
				ageyear=thisyear-byear-1;
				agemonth=12-(bmonth-tm);	
				agedate=thisdate-bdate;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth<tm && bdate<thisdate)	//3
			{
				ageyear=thisyear-byear;
				agemonth=tm-bmonth;
				agedate=thisdate-bdate;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth>tm && bdate>thisdate)	//4
			{
				ageyear=thisyear-byear-1;	//bday 27/09/1995 today=08/07/2015
				agemonth=12-(bmonth-tm);
				agedate=(30-bdate)+thisdate;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth==tm && bdate<thisdate)	//27/02/1995, 28/02/2015
			{
				ageyear=thisyear-byear;
				agemonth=0;
				agedate=thisdate-bdate;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth==tm && bdate>thisdate)	//27/02/1995, 25/02/2015
			{
				ageyear=thisyear-byear-1;
				agemonth=11;
				agedate=30-(bdate-thisdate);
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth<tm && bdate==thisdate)		//10/6/1995, 10/7/2015
			{
				ageyear=thisyear-byear;
				agemonth=tm-bmonth;
				agedate=0;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bmonth>tm && bdate==thisdate)		//10/8/1995, 10/7/2015
			{
				ageyear=thisyear-byear-1;
				agemonth=11;
				agedate=0;
				Intent i=new Intent(this, YourAge.class);
				i.putExtra("agedate", agedate);
				i.putExtra("agemonth", agemonth);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
			else if(bdate==thisdate && bmonth==tm)
			{
				ageyear=thisyear-byear;
				Intent i=new Intent(this, Birthday.class);
				i.putExtra("ageyear", ageyear);
				i.putExtra("bdate", bdate);
				i.putExtra("bmonth", bmonth);
				i.putExtra("byear", byear);
				startActivity(i);
			}
		}
		else Toast.makeText(this, "Wrong date/month/year", Toast.LENGTH_LONG).show();
		
		}catch(Exception e)
			{
			Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
			}	
	}
}
