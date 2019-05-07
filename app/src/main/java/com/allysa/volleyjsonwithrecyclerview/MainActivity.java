package com.allysa.volleyjsonwithrecyclerview;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String id, name, username, email, addr, street, suite, city, zipcode;
    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private Button btnGet;
    private HttpURLConnection connection = null;
    private BufferedReader reader = null;
    private ArrayList<Users> usersArrayList;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressDialog progressDialog;

    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = (Button) findViewById(R.id.btnGet);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        rq = Volley.newRequestQueue(MainActivity.this);
        makeJSONArrayRequest();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeJSONArrayRequest();
            }
        });


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJSONArrayRequest();
            }
        });

    }

    public void makeJSONArrayRequest(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String reqURL = "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, reqURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        usersArrayList = new ArrayList<>();
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                //create a JSONObject for  fetching single user data
                                JSONObject userDetail = response.getJSONObject(i);

                                id = userDetail.getString("id");
                                name = userDetail.getString("name");
                                username = userDetail.getString("username");
                                email = userDetail.getString("email");

                                JSONObject address = userDetail.getJSONObject("address");
                                street = address.getString("street");
                                suite = address.getString("suite");
                                city = address.getString("city");
                                zipcode = address.getString("zipcode");

                                addr = street + ", " + suite + ", " + city + ", " + zipcode;

                                usersArrayList.add(new Users(id, name, username, email, addr));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                        progressDialog.dismiss();

                        adapter = new UsersAdapter(usersArrayList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefreshLayout.setRefreshing(false);
                progressDialog.dismiss();
                Log.i("Volley error : ", String.valueOf(error));

            }
        });

        rq.add(jsonArrayRequest);
    }

}
