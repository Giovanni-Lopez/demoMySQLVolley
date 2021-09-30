package com.itca.demomysql_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.Context
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.chrono.JapaneseDate;

public class PruebasVolley extends AppCompatActivity {

    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_volley);

        tvRes = (TextView) findViewById(R.id.tvRes);


    }

    public void baseRequest(){
        String url = "http://htpbin.org/html";

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println(response.substring(0, 16));
                tvRes.setText(response.substring(0,16));
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong!");
                Toast.makeText(getContext(), "Sin conexion a Internet", Toast.LENGTH_SHORT).show();
                error.PrintStackTrace();
            }
        });

        MySingleton.getInstance(getContext().addToRequestQueue(stringRequest));

    }

    private void peticionGson(){
        String url = "http://192.168.57.1/service2020/json1.php";
        String url1 = Setting_VAR.URL_PRUEBA;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tvRes.setText("Response: " + response.toString());
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //
            }
        });

        MySingleton.getInstance(getContext().addToRequestQueue(jsonObjectRequest));
    }

    private void recibirJson(){
        StringRequest request = new StringRequest(Request.Method.GET, Setting_VAR.URL_PRUEBA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuestaJSON = new JSONObject(response.toString());

                    String var1 = respuestaJSON.getString("id");
                    String var2 = respuestaJSON.getString("nombre");
                    tvRes.setText("Response: " + response.toString());

                    Toast.makeText(getContext(), "Id: " + var1 + "/nNombre: " + var2, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                }
            }
        );

        MySingleton.getInstance(getContext().addToRequestQueue(request));

    }



}