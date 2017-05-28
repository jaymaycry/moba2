package com.example.jay.spt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    AppCompatActivity searchActivity;
    Button searchButton;
    EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchActivity = this;
        searchButton = (Button)findViewById(R.id.search_button);
        searchInput = (EditText)findViewById(R.id.search_input);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SearchActivity", "button clicked");

                Intent intent = new Intent(searchActivity, StationListActivity.class);
                intent.putExtra("searchTerm", searchInput.getText().toString());

                startActivity(intent);
            }
        });
    }
}
