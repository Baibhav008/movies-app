package com.example.movie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.movie.R;

public class IntroActivity extends AppCompatActivity {

    AppCompatButton getIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getIn = findViewById(R.id.getIn);
        getIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        //http://moviesapi.ir/api/v1/movies?page=1
        //http://moviesapi.ir/api/v1/movies/11


    }
}