package com.example.nguyenhuynhkieng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.BitSet;

public class ShowInformation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        TextView txtSTT = findViewById(R.id.txtSTT);
        TextView txtType = findViewById(R.id.txtType);
        TextView txtPrice = findViewById(R.id.txtPrice);
        TextView txtCountry = findViewById(R.id.txtCountry);
        TextView txt = findViewById(R.id.txtDis);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://60b6df5a17d1dc0017b886d1.mockapi.io/api/Wood";

        GetData(url);
    }
    private void GetData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView txt = findViewById(R.id.txtDis);
                /*TextView txtSTT = findViewById(R.id.txtSTT);
                TextView txtType = findViewById(R.id.txtType);
                TextView txtPrice = findViewById(R.id.txtPrice);
                TextView txtCountry = findViewById(R.id.txtCountry);

                String data = response.toString();

                txtSTT.setText(data.substring(8,9));
                txtType.setText(data.substring(19,24));
                txtPrice.setText(data.substring(35,36));
                txtCountry.setText(data.substring(48,56));*/
                txt.setText(response.toString());



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowInformation.this,
                        "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}




