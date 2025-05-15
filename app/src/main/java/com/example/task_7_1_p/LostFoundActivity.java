package com.example.task_7_1_p;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_7_1_p.data.DatabaseHelper;
import com.example.task_7_1_p.model.Item;

import java.util.ArrayList;
import java.util.List;

public class LostFoundActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper db = new DatabaseHelper(this);
        List<Item> itemList = db.getAllItems();

        recyclerViewAdapter = new RecyclerViewAdapter(itemList, this, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item clickedItem) {
                ItemFragment fragment = ItemFragment.newInstance(clickedItem);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(android.R.id.content, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);

    }

}