package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Results extends AppCompatActivity {


    // initializing all variables
    private TextView textView;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;

    private TextView name;
    private TextView price;
    private TextView is_closed;
    private TextView url;
    private TextView rating;
    private TextView review_count;
    private Button back;
    private Button next;
    private int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


// giving variable value settings
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        is_closed = findViewById(R.id.is_closed);
        url = findViewById(R.id.url);
        rating = findViewById(R.id.rating);
        review_count = findViewById(R.id.review_count);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);



        final RequestQueue queue = Volley.newRequestQueue(Results.this);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.yelp.com/v3/businesses/search?location=33442",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            JSONObject json = new JSONObject(response);
                            JSONObject item = json.getJSONArray("businesses").getJSONObject(0);
                            name.setText(item.getString("name"));
                            price.setText(item.getString("price"));
                            is_closed.setText(item.getString("is_closed"));
                            url.setText(item.getString("url"));
                            rating.setText(item.getString("rating"));
                            review_count.setText(item.getString("review_count"));


                        }catch(Exception e){

                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        })
        {
            @Override
            public Map< String, String > getHeaders() throws AuthFailureError {
            Map < String, String > params = new HashMap< >();
            params.put("Authorization", "bearer " + "KeGY1lNk3nRrSC_z-n3bHDcmpAQTv8mv2X1jS06iU2J0l6GRwfXDP1EgugQGYUEH6GvMCfCbfG2Z7VUQ8nEZz8m8GqtqGwVqSzxqSwBFMcBcE7Rgy-c4abLEbrDSX3Yx");
            return params;
        }
        };

        queue.add(stringRequest);









        final RequestQueue queue2 = Volley.newRequestQueue(Results.this);
        next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        index += 1;
                                        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "https://api.yelp.com/v3/businesses/search?location=33442",

                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        try {
                                                            if (index > 0) {
                                                                back.setVisibility(View.VISIBLE);
                                                            }
                                                            System.out.println(response);
                                                            JSONObject json = new JSONObject(response);
                                                            JSONObject item = json.getJSONArray("businesses").getJSONObject(index);
                                                            name.setText(item.getString("name"));
                                                            price.setText(item.getString("price"));
                                                            is_closed.setText(item.getString("is_closed"));
                                                            url.setText(item.getString("url"));
                                                            rating.setText(item.getString("rating"));
                                                            review_count.setText(item.getString("review_count"));


                                                        } catch (Exception e) {

                                                        }
                                                    }

                                                }, new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                System.out.println(error);

                                            }
                                        }) {
                                            @Override
                                            public Map<String, String> getHeaders() throws AuthFailureError {
                                                Map<String, String> params = new HashMap<>();
                                                params.put("Authorization", "bearer " + "KeGY1lNk3nRrSC_z-n3bHDcmpAQTv8mv2X1jS06iU2J0l6GRwfXDP1EgugQGYUEH6GvMCfCbfG2Z7VUQ8nEZz8m8GqtqGwVqSzxqSwBFMcBcE7Rgy-c4abLEbrDSX3Yx");
                                                return params;
                                            }

                                        };

                                        queue2.add(stringRequest2);
                                    }
                                });




        final RequestQueue queue3 = Volley.newRequestQueue(Results.this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index -= 1;
                final StringRequest stringRequest3 = new StringRequest(Request.Method.GET, "https://api.yelp.com/v3/businesses/search?location=33442",

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    if (index == 0) {
                                        back.setVisibility(View.INVISIBLE);
                                    }
                                    System.out.println(response);
                                    JSONObject json = new JSONObject(response);
                                    JSONObject item = json.getJSONArray("businesses").getJSONObject(index);
                                    name.setText(item.getString("name"));
                                    price.setText(item.getString("price"));
                                    is_closed.setText(item.getString("is_closed"));
                                    url.setText(item.getString("url"));
                                    rating.setText(item.getString("rating"));
                                    review_count.setText(item.getString("review_count"));


                                } catch (Exception e) {

                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);

                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("Authorization", "bearer " + "KeGY1lNk3nRrSC_z-n3bHDcmpAQTv8mv2X1jS06iU2J0l6GRwfXDP1EgugQGYUEH6GvMCfCbfG2Z7VUQ8nEZz8m8GqtqGwVqSzxqSwBFMcBcE7Rgy-c4abLEbrDSX3Yx");
                        return params;
                    }

                };

                queue3.add(stringRequest3);
            }
        });







        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case R.id.action_search:
                        Intent search = new Intent(getApplicationContext(), Search.class);
                        startActivity(search);
                        break;
                    case R.id.action_results:
                        Intent results = new Intent(getApplicationContext(), Results.class);
                        startActivity(results);
                        break;
                    case R.id.action_favorite:
                        Intent fav = new Intent(getApplicationContext(), Favorites.class);
                        startActivity(fav);
                        break;
                }
                return true;
            }
        });

    }
}