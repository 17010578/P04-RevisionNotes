package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    Button insert;
    Button show;
    RadioGroup rg;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        insert = findViewById(R.id.buttonInsertNote);
        show = findViewById(R.id.buttonShowList);
        rg = findViewById(R.id.radioGroupStars);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextNote.getText().toString();
                if (text.length() > 0){

                    DBHelper db = new DBHelper(MainActivity.this);
                    int selectedButtonID = rg.getCheckedRadioButtonId();
                    RadioButton rb = findViewById(selectedButtonID);
                    int stars = Integer.parseInt(rb.getText().toString());
                    ArrayList<String> stored = new ArrayList<>();
                    stored = db.getNoteContent();

                    boolean store = false;

                    for (int i =0; i<stored.size(); i++){
                        if (text.equalsIgnoreCase(stored.get(i))) {
                            store = true;
                        }
                    }

                    if (!store){
                        // Insert a task
                        db.insertNote(text, stars);
                        db.close();

                        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Data exist", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Please key in data", Toast.LENGTH_LONG).show();
                }

            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);

            }
        });
    }
}
