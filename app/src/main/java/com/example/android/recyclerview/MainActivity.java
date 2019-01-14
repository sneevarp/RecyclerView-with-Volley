package com.example.android.recyclerview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private List<list> listitems;
    private static final String URL_DATA="https://simplifiedcoding.net/demos/marvel/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.rview);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitems=new ArrayList<>();
        loadfromjson();

    }
    public void loadfromjson(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                   // JSONObject jsonObject=new JSONObject(response);
                    JSONArray array= new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject o=array.getJSONObject(i);
                        if(o == null) {
                            continue;
                        }

                       list item=new list(
                        o.getString("name"),
                        o.getString("bio"),
                               o.getString("imageurl"));
                        listitems.add(item);

                    }
                    adapter = new adapterclass(listitems,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "JSON to load", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("praveen",error.toString());
                Toast.makeText(MainActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
