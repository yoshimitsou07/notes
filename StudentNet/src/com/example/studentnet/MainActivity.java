package com.example.studentnet;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	EditText txtIdno,txtFamilyName,txtGivenName;
	Spinner cboCourse,cboYear;
	private String selectedCourse;
	private String selectedYear;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        this.txtIdno=(EditText) this.findViewById(R.id.editText1);
        this.txtFamilyName=(EditText) this.findViewById(R.id.editText2);
        this.txtGivenName=(EditText) this.findViewById(R.id.editText3);
        
        this.cboCourse=(Spinner) this.findViewById(R.id.spinner1);
        this.cboYear=(Spinner) this.findViewById(R.id.spinner2);
        
        this.cboCourse.setOnItemSelectedListener(this);
        this.cboYear.setOnItemSelectedListener(this);
        
        
        //allow android to run a parallel process
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		String idno=this.txtIdno.getText().toString();
		String familyname=this.txtFamilyName.getText().toString();
		String givenname=this.txtGivenName.getText().toString();
		
		//open socket connection to the server 
	
		try {
			//connect and sent data to the server
			URL url=new URL("http://10.0.2.2/android/addstudent.php?idno="+idno+"&familyname="+familyname+"&givenname="+givenname+"&course="+this.selectedCourse+"&yearlevel="+this.selectedYear);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			//get the server responses
			InputStream is=conn.getInputStream();
			int c=0;
			StringBuffer sb=new StringBuffer();
			while((c=is.read())!=-1){				
				sb.append((char)c);				
			}
			is.close();
			conn.disconnect();
			Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int id=arg1.getId();
		
		switch(id){
		case R.id.spinner1:
			selectedCourse=this.cboCourse.getItemAtPosition(arg2).toString();
			break;
		case R.id.spinner2:
			selectedYear = this.cboYear.getItemAtPosition(arg2).toString();
		}
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
