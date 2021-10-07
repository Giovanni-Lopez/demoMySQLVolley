package com.itca.demomysql_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.chrono.JapaneseDate;
import java.util.HashMap;
import java.util.Map;

public class PruebasVolley extends AppCompatActivity {

    private TextView tvRes;
    private Button btnPeticionHTTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_volley);

        tvRes = (TextView) findViewById(R.id.tvRes);
        btnPeticionHTTP = (Button) findViewById(R.id.btnPeticionHTTP);

        btnPeticionHTTP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pruebaVolley();
                //recibirJson();


            }
        });

    }

    public void baseRequest(){
        String URL = "http://192.168.90.10/index.php";

        StringRequest request  = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
//                System.out.println(response.substring(0, 16));
//                tvRes.setText(response.substring(0,16));
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                System.out.println("Something went wrong!");
//                Toast.makeText(getContext(), "Sin conexion a Internet", Toast.LENGTH_SHORT).show();
//                error.PrintStackTrace();
            }
        }){
          protected Map<String, String> getParams() throws AuthFailureError{
              Map<String, String> map = new HashMap<>();
              map.put("Content-Type", "aplicacion/json; charset=utf-8");
              map.put("Accept", "aplicacion/json");
//              map.put("id", "1");
              map.put("id_categoria", tvRes.getText().toString());
              map.put("Nombre_categoria", "hola");
              map.put("senal", "1");

              return map;
          }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    private void pruebaVolley(){
        String url = "https://mjgl.com.sv/pruebaVolley/test.php";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response.substring(0, 16));
                tvRes.setText(response.toString());
                Toast.makeText(PruebasVolley.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(PruebasVolley.this, "Error, Problemas al intentar conectar con el servidor!!", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }


    private void peticionGson(){
        String url1 = "https://mjgl.com.sv/pruebaVolley/test.php";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, (String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                tvRes.setText(response.toString());
                Toast.makeText(PruebasVolley.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void recibirJson(){

        String url1 = "https://mjgl.com.sv/pruebaVolley/test.php";
        StringRequest request = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respuestaJSON = new JSONObject(response.toString());

                    String var1 = respuestaJSON.getString("id");
                    String var2 = respuestaJSON.getString("nombre");
                    String var3 = respuestaJSON.getString("telefono");

                    tvRes.setText(response.toString());

                    Toast.makeText(getApplicationContext(), "Id: " + var1 + "\n" + "Nombre: " + var2 + "\n" + "Telefono: " + var3, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PruebasVolley.this, "Error. Problemas en la conexion al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

}