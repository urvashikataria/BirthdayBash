package com.hp.stp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveMyBirthday extends Activity implements OnClickListener {

	int bdate, bmnth, byear;
	TextView t, t1;
	EditText e;
	String name;
	Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_my_birthday);
		Bundle b=getIntent().getExtras();
		bdate=b.getInt("bdte");
		bmnth=b.getInt("bmnt");
		byear=b.getInt("byr");
		t=(TextView)findViewById(R.id.textView1);
		t1=(TextView)findViewById(R.id.textView2);
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(this);
		e=(EditText)findViewById(R.id.editText1);
		
		t1.setText("Your birthdate is "+bdate+"-"+bmnth+"-"+byear+".");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		name=e.getText().toString();
		if(!(name.equals(null)))
			{
			Toast.makeText(this, name+"'s birthday saved", Toast.LENGTH_LONG).show();
			Intent i=new Intent(this, BirthdayBash.class);
			startActivity(i);
			}
		else if(name.equals(null))
			{
			Toast.makeText(this, "Please enter a valid name", Toast.LENGTH_LONG).show();
			}
		
	}

}

