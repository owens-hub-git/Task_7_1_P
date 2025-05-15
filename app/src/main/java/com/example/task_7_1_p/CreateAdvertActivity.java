package com.example.task_7_1_p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.task_7_1_p.data.DatabaseHelper;
import com.example.task_7_1_p.model.Item;

public class CreateAdvertActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        EditText descEditText = findViewById(R.id.descEditText);
        EditText dateEditText = findViewById(R.id.dateEditText);
        EditText locationEditText = findViewById(R.id.locationEditText);
        EditText issueEditText = findViewById(R.id.issueEditText);
        Button saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        RadioGroup radioGroup = findViewById(R.id.postTypeRG);


        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int checkedId = radioGroup.getCheckedRadioButtonId();
                String postType = "";
                if (checkedId == R.id.lostRadioB) {
                    postType = "Lost";
                } else if (checkedId == R.id.foundRadioB) {
                    postType = "Found";
                }

                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String desc = descEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String location = locationEditText.getText().toString();
                String issue = issueEditText.getText().toString();

                long result = db.insertItem(new Item(postType, name, phone, desc, date, location, issue));
                if(result > 0)
                {
                    Intent lostfoundIntent = new Intent(CreateAdvertActivity.this, LostFoundActivity.class);
                    startActivity(lostfoundIntent);
                }
                else
                {
                    Toast.makeText(CreateAdvertActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }



            }

        });

    }
}