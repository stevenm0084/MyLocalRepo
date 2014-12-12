package com.warbler.listme;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.preference.*;
import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	public static EditText et;
	public static final String DEBUGTAG = "MyDebug";
	public static final String FILENAME = "textFileTestApp.txt";
	public static final String FILESAVED = "FileSaved";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//CheckedTextView groceryItem = (CheckedTextView) findViewById(R.id.groceryitem);
		//et = (EditText) findViewById(R.id.editText);
		setContentView(R.layout.activity_main);
		addSaveButtonListener();
		addClearButtonListener();
		addOpenButtonListener();
	}		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void loadSavedFile(){
		try {
			
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));
			
			String line;
			//et = (EditText) findViewById(R.id.editText);
			while((line = reader.readLine()) != null){
				et.append(line);
				et.append("\n");
			}
			fis.close(); 
			
		} catch (FileNotFoundException e) {
			Log.d(DEBUGTAG, "Unable to load file");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//et = (EditText) findViewById(R.id.editText);
		//et.getText();
	}
	
	private void addSaveButtonListener() {
		Button saveBtn = (Button) findViewById(R.id.saveButton);

		saveBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				

				
				System.out.println("saved...");

				//et = (EditText) findViewById(R.id.editText);

				String text = et.getText().toString();

				try {
/*					FileOutputStream fos = openFileOutput(FILENAME,
							Context.MODE_PRIVATE);
					fos.write(text.getBytes());
					fos.close();

					SharedPreferences prefs = getPreferences(MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putBoolean(FILESAVED, true);
					editor.commit();*/

					Toast.makeText(MainActivity.this, getString(R.string.toast_file_saved), Toast.LENGTH_LONG).show();
				} /*catch (FileNotFoundException e) {
					Log.d(DEBUGTAG, "Unable to save file");
					e.printStackTrace();
				} catch (IOException e) {
					Log.d(DEBUGTAG, "Save button clicked: " + text);
					e.printStackTrace();
				} */catch (Exception e){
					Log.d(DEBUGTAG, "Save button clicked: " + text);
					e.printStackTrace();
				}
				
			}

		});
	}

	private void addClearButtonListener() {
		Button clearBtn = (Button) findViewById(R.id.clearButton);
		clearBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("cleared...");
				
				//et = (EditText) findViewById(R.id.editText);
				et.setText("");
			}
		});
	}

	private void addOpenButtonListener(){
		Button openBtn = (Button) findViewById(R.id.openButton);
		
		openBtn.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				System.out.println("opening...");
				SharedPreferences  prefs = 	getPreferences(MODE_PRIVATE);	
				boolean fileSaved = prefs.getBoolean(FILESAVED, false);
				
				if(fileSaved){
					loadSavedFile();
				}

				
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}