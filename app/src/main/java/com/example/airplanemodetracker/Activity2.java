package com.example.airplanemodetracker;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//cała logika działania programu juz skonczona, trzeba dodac API requesta i responsa z pobranego tekstu i wyniku od API.

public class Activity2 extends AppCompatActivity {
    private Button fromEnglish,fromPolish,fromFrench,toEnglish,toPolish,toFrench,translate;
    private Button bttn2;





    private boolean fEnglish,fPolish,fFrench,tEnglish,tPolish,tFrench;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        bttn2 = (Button) findViewById(R.id.button2);
        bttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        fromEnglish = (Button) findViewById(R.id.button3);
        fromPolish = (Button) findViewById(R.id.button4);
        fromFrench = (Button) findViewById(R.id.button5);
        toEnglish = (Button) findViewById(R.id.button6);
        toPolish = (Button) findViewById(R.id.button7);
        toFrench = (Button) findViewById(R.id.button8);
        translate = (Button) findViewById(R.id.button9);
        fromEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fEnglish = true;
                fPolish = false;
                fFrench = false;
                Toast.makeText(getApplicationContext(),"Translating form English",Toast.LENGTH_SHORT).show();
            }
        });
        fromPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fEnglish = false;
                fPolish = true;
                fFrench = false;
                Toast.makeText(getApplicationContext(),"Translating form Polish",Toast.LENGTH_SHORT).show();
            }
        });
        fromFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fEnglish = false;
                fPolish = false;
                fFrench = true;
                Toast.makeText(getApplicationContext(),"Translating form French",Toast.LENGTH_SHORT).show();
            }
        });

        toEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tEnglish = true;
                tPolish = false;
                tFrench = false;
                Toast.makeText(getApplicationContext(),"Translating to English",Toast.LENGTH_SHORT).show();
            }
        });
        toPolish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tEnglish = false;
                tPolish = true;
                tFrench = false;
                Toast.makeText(getApplicationContext(),"Translating to Polish",Toast.LENGTH_SHORT).show();
            }
        });
        toFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tEnglish = false;
                tPolish = false;
                tFrench = true;
                Toast.makeText(getApplicationContext(),"Translating to French",Toast.LENGTH_SHORT).show();
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from="",to="";
                if(fEnglish) from="en";
                if(fPolish) from="pl";
                if(fFrench) from="fr";
                if(tEnglish) to="en";
                if(tPolish) to="pl";
                if(tFrench) to="fr";
                tlumacz(from,to);
            }
        });

    }

    private void tlumacz(String from, String to) {

        //https://api.mymemory.translated.net/get?q=Hello World!&langpair=en|it
        String url = "https://api.mymemory.translated.net/get?q=";
        EditText textToTranslateEditText = findViewById(R.id.editText);
        String textToTranslate = textToTranslateEditText.getText().toString();
        textToTranslate = textToTranslate.replaceAll(" ", "%20");
        //Toast.makeText(getApplicationContext(),textToTranslate,Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url + textToTranslate + "&langpair=" + from + "|" + to, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("matches");
                            JSONObject match = jsonArray.getJSONObject(0);
                            String result = match.getString("translation");
                            result=result.replaceAll("&#39;","'");
                            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            TextView widokOdpowiedzi = findViewById(R.id.textView8);
                            widokOdpowiedzi.setText(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(getApplicationContext(),"odpowiedz : "+ response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

    }

    private void openActivity1() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
