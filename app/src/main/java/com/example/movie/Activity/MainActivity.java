package com.example.movie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movie.Adapter.FilmListAdapter;
import com.example.movie.Domain.FilmItem;
import com.example.movie.Domain.ListFilm;
import com.example.movie.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView.Adapter adapterNewMovie, adapterUpComing;
    private RecyclerView recyclerViewNewMovies , recyclerViewUpComing;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2;
    private ProgressBar loading1,loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        sendRequest1();

        sendRequest2();

    }

    private void sendRequest1()
    {
        Log.i("Request 1 started"," ");
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1",response ->
        {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterNewMovie = new FilmListAdapter(items);
            recyclerViewNewMovies.setAdapter(adapterNewMovie);

        },error -> {
            Log.i("sendRequest1 error : ",error.toString());
            loading1.setVisibility(View.GONE);

        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequest2()
    {
        Log.i("Request 2 started"," ");
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=4",response ->
        {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response, ListFilm.class);
            adapterUpComing = new FilmListAdapter(items);
            recyclerViewUpComing.setAdapter(adapterUpComing);

        },error -> {
            Log.i("sendRequest2 error : ",error.toString());
            loading2.setVisibility(View.GONE);

        });
        mRequestQueue.add(mStringRequest2);
    }

    private void initView()
    {
        recyclerViewNewMovies=findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        recyclerViewUpComing=findViewById(R.id.view2);
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loading1=findViewById(R.id.loading1);
        loading2=findViewById(R.id.loading2);

    }
}