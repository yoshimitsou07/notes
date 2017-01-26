package com.example.studentnet;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class StudentList extends Activity {

	WebView wv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.studentlist);
		
		this.wv=(WebView) this.findViewById(R.id.webView1);
		this.wv.loadUrl("http://10.0.2.2/android/showstudent.php");
		
	}
	
	
	

}
