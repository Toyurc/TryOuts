package com.example.alien.tryout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.github.com/search/users?q=language:java+location:lagos";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<User> profiles;
    View noInternetScreenView;
    View noDataView;
    View loadingScreenView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profiles = new ArrayList<>();
         /*
        Initialising default Views
         */
        loadingScreenView = findViewById(R.id.loading_screen);
        noInternetScreenView = findViewById(R.id.no_internet_screen);
        noDataView = findViewById(R.id.no_data);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            fetchData();
        } else {
            /*
            This code will work when there is no internet connectivity.
             */
            recyclerView.setVisibility(View.GONE);
            loadingScreenView.setVisibility(View.GONE);
            noDataView.setVisibility(View.GONE);
            noInternetScreenView.setVisibility(View.VISIBLE);
        }
    }

    private void fetchData() {

        loadingScreenView.setVisibility(View.VISIBLE);
        noInternetScreenView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        noDataView.setVisibility(View.GONE);

        Cache cache = new DiskBasedCache(getCacheDir(), 2048 * 2048);
        Network network = new BasicNetwork(new HurlStack());


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loadingScreenView.setVisibility(View.GONE);
                noDataView.setVisibility(View.GONE);
                noInternetScreenView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String UserName = jsonObject1.getString("login");
                        String GitUrl = jsonObject1.getString("html_url");
                        String UserImage = jsonObject1.getString("avatar_url");

                        User UsersProfile = new User(UserName, GitUrl, UserImage);
                        profiles.add(UsersProfile);

                    }

                    adapter = new UserAdapter(getApplicationContext(), profiles);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    loadingScreenView.setVisibility(View.VISIBLE);
                    noInternetScreenView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    noDataView.setVisibility(View.GONE);

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
