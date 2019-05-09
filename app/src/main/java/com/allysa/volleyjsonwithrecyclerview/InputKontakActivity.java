package com.allysa.volleyjsonwithrecyclerview;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputKontakActivity extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressDialog progressDialog;
    public EditText inputNama, inputEmail,inputAlamat, inputNoHp;
    private ArrayList<Kontak> kontakArrayList;
    private Button btnSimpan;
    RequestQueue rq;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kontak);

        rq = Volley.newRequestQueue(this);
        inputNama = (EditText) findViewById(R.id.inputNama);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputAlamat = (EditText) findViewById(R.id.inputAlamat);
        inputNoHp = (EditText) findViewById(R.id.inputNoHp);

        btnSimpan = (Button) findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simpanData();

            }
        });

    }
    public void simpanData(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        JSONObject params = new JSONObject();
        try{
            params.put("nama", inputNama.getText().toString());
            params.put("email", inputEmail.getText().toString());
            params.put("alamat", inputAlamat.getText().toString());
            params.put("nohp", inputNoHp.getText().toString());
        }catch (JSONException e){
            e.printStackTrace();
        }

        String reqURL = "http://210.210.154.65/api/kontak/store";
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, reqURL,
                params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String msg = response.getString("message");
                            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.i("Volley error : ", String.valueOf(error));
            }
        }
        );

        rq.add(req);

    }

    }

