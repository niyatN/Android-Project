package com.example.niyat.httpreq;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    Button b;
    TextView mOutput;
    EditText regNo;
    String reg;
    String macId;
    TextView mMacId;
    String mac="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        b = (Button) findViewById(R.id.submit);
        mOutput = (TextView) findViewById(R.id.output);
        regNo = (EditText) findViewById(R.id.regNo);
        mMacId = (TextView) findViewById(R.id.macId);


        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reg = regNo.getText().toString();
                Pattern p = Pattern.compile("\\d{4}");
                Matcher m = p.matcher(reg);
                boolean b = m.matches();
                if(!b){
                    mOutput.setText("Invalid Reg. no.");
                }
                else {
                    try {

                        List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());

                        for (NetworkInterface networkInterface : all) {
                            if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;

                            byte[] macBytes = networkInterface.getHardwareAddress();
                            if (macBytes == null) {
                                mMacId.setText("Mac Address is unable to be fetched");
                            }

                            StringBuilder res1 = new StringBuilder();
                            for (byte k : macBytes) {

                                res1.append(Integer.toHexString(k & 0xFF) + ":");
                            }

                            if (res1.length() > 0) {
                                res1.deleteCharAt(res1.length() - 1);
                            }
                            mac=mac+res1.toString();
                            mMacId.setText("Mac address :\n"+res1.toString());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    String url ="https://android-club-project.herokuapp.com/upload_details?reg_no="+reg+"&mac="+mac;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    mOutput.setText("Output String: \n "+ response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //mOutput.setText("Something went wrong!"+error);
                        }
                    });
                    queue.add(stringRequest);

                }

            }
        });
    }





}
