package com.example.project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.app.Dialog;

public class LightInInterface extends Activity {
	
	private Button backButton;
	static Button lightInIV;
	private static String localStatus;

	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();
	
   static boolean lightInStatus =false;
	boolean LoopStatus = true;
	static boolean LIGHTIN = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		lightInIV = (Button) findViewById(R.id.SwitchlightInButton);
		backButton = (Button) this.findViewById(R.id.lightbackbutton);
		LightIncheck();
		if(Connection.initStates.contains("alarm"))
		{
			 Dialog alertDialog = new AlertDialog.Builder(this).
		     setTitle("Alarm Reminder").
		     setMessage("Alarm is on!!!!!!!!!!").
             create();
		     alertDialog.show();
		}
		LIGHTIN = true;
	}

	public static void LightIncheck() {
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
			lightInStatus=true;
			
			
		} else {
			lightInIV.setBackgroundResource(R.drawable.lightoff);
			lightInStatus=false;
		}

	}

	public void buttonlightInClicked(View view) {

		if(lightInStatus==false)
		{
		con.setResult("lightIn:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
			lightInStatus=true;
		} else {

		}
		con.UpdateForDeviceImages();
		
		
		}else{
			
			con.setResult("lightIn:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("lightIn:off")) {
				lightInIV.setBackgroundResource(R.drawable.lightoff);
				lightInStatus=false;
			} else {

			}
			con.UpdateForDeviceImages();
			
		}

	}

	public void buttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(LightInInterface.this, MainActivity.class);
		LightInInterface.this.startActivity(intent);

	}

}
