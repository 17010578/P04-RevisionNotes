package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv2;
	ArrayAdapter aa;
	ArrayList<Note> note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO implement the Custom ListView
		setContentView(R.layout.activity_second);

		Intent intentReceived = getIntent();

		lv2 = findViewById(R.id.lv);


		note = new ArrayList<Note>();
		aa = new RevisionNotesArrayAdapter(this, R.layout.row, note);
		DBHelper db = new DBHelper(SecondActivity.this);
		ArrayList<Note> data = db.getAllNotes();
		aa = new RevisionNotesArrayAdapter(this,R.layout.row,data);
		lv2.setAdapter(aa);
	}


}
