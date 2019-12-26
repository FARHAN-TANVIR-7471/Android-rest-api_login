package com.example.loginactivitytest.activity;


import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.loginactivitytest.CustomApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.loginactivitytest.R;
import com.example.loginactivitytest.adapter.RecyclerTransportScheduleAdapter;
import com.example.loginactivitytest.helpers.IdToVenueConverter;
import com.example.loginactivitytest.model.TransportScheduleUser;
import com.example.loginactivitytest.network.ApiClient;
import com.example.loginactivitytest.service.TransportscheduleAPI;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;

    /*General time and date formet*/
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    /*End time and calender*/

    Button startBtn, stopBtn;
    TextView time_Text, date_Text;

    TextView currentTimeid, currentFromLocationId, currentToLocationId;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("fndksa", "oncreate start");
        setContentView(R.layout.activity_home);

        //Toast.makeText(this, ""+getIntent().getStringExtra("token"), Toast.LENGTH_SHORT).show();

        startBtn = findViewById(R.id.startbtn);
        stopBtn = findViewById(R.id.stopbtn);
        stopBtn.setVisibility(View.GONE);

        currentTimeid = (TextView) findViewById(R.id.currentTimeid);
        currentFromLocationId = (TextView) findViewById(R.id.currentFromLocationId);
        currentToLocationId = (TextView) findViewById(R.id.currentToLocationId);

        getTransportList();

        /*Current date*/
        date_Text = findViewById(R.id.dateText);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date_Text.setText(currentDate);

        /*Current time*/
        time_Text = findViewById(R.id.timeId);
        String time = simpleDateFormat.format(calendar.getTime());
        time_Text.setText(time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setVisibility(View.GONE);
                stopBtn.setVisibility(View.VISIBLE);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopBtn.setVisibility(View.GONE);
                startBtn.setVisibility(View.VISIBLE);
// pass actual data
                save("");
            }

        });

        Log.d("fndksa", "oncreate end");
    }

    public void getTransportList() {
        Log.d("fndksa", "getTransportList called");
        try {
            TransportscheduleAPI service = ApiClient.getRetrofit().create(TransportscheduleAPI.class);
            Call<List<TransportScheduleUser>> call = service.getUserData();
            Log.d("fndksa", "getTransportList called2");

            call.enqueue(new Callback<List<TransportScheduleUser>>() {
                @Override
                public void onResponse(Call<List<TransportScheduleUser>> call, retrofit2.Response<List<TransportScheduleUser>> response) {
                    {
                        Log.d("fndksa", "eyyy:" + response.body());
                        Toast.makeText(HomeActivity.this, "Rrsponce is ok", Toast.LENGTH_SHORT).show();
                        List<TransportScheduleUser> transportScheduleUsersList = response.body();
                        Log.d("fndksa", "transportScheduleUsersList:" + transportScheduleUsersList.size());

                        if (transportScheduleUsersList.size() > 0) {

                            currentTimeid.setText("" + transportScheduleUsersList.get(0).getDepartureTime());
                            currentFromLocationId.setText(IdToVenueConverter.getVenueName("" + transportScheduleUsersList.get(0).getFkFromLocation()));
                            currentToLocationId.setText(IdToVenueConverter.getVenueName("" + transportScheduleUsersList.get(0).getFkToLocation()));

//                        for (int i=0; i< transportScheduleUsersList.size(); i++){
//                            Log.d("fndksa","id:"+transportScheduleUsersList.get(i).getId());
//                            Log.d("fndksa","fkTransportId:"+transportScheduleUsersList.get(i).getFkTransportId());
//                            Log.d("fndksa","fkFromLocation:"+transportScheduleUsersList.get(i).getFkFromLocation());
//                            Log.d("fndksa","fkToLocation:"+transportScheduleUsersList.get(i).getFkToLocation());
//                            Log.d("fndksa","departureTime:"+transportScheduleUsersList.get(i).getDepartureTime());
//
//                            Log.d("fndksa", "loop end /n/n");
//                        }
                        }

                        layoutManager = new LinearLayoutManager(HomeActivity.this);

                        RecyclerView recyclerView = findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(layoutManager);
                        /*recyclerView.setHasFixedSize(true);*/

                        RecyclerTransportScheduleAdapter recyclerTransportScheduleAdapter =
                                new RecyclerTransportScheduleAdapter(transportScheduleUsersList);
                        recyclerView.setAdapter(recyclerTransportScheduleAdapter);
                    }
                }

                @Override
                public void onFailure(Call<List<TransportScheduleUser>> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }

    }

    public void save(final String fkScheduleId ) {
        dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.show();
        Log.d("dsjkjsdaad", "http://localhost:8088/saveScheduledTravelInfo/");
        StringRequest postRequest = new StringRequest(Request.Method.POST, "http://localhost:8088/saveScheduledTravelInfo/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("dsjkjsdaad", "response:" + response);
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        try {
                            Gson gson = new GsonBuilder().create();
//                                StatusPojo statusPojo = gson.fromJson(response, StatusPojo.class);

                        } catch (Exception e) {
//                                ContentToastHelper.showMayaWarningToast(getActivity(), getString(R.string.something_went_wrong_please_try_again));
                            Log.d("dsjkjsdaad", "e:" + e.toString());

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("dsjkjsdaad", "e:" + error.toString());
                        if (dialog != null) {
                            dialog.dismiss();
                        }
//                            ContentToastHelper.showMayaWarningToast(getActivity(), getString(R.string.something_went_wrong_please_try_again));

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("fkTrnsportId", "");
                params.put("fkScheduleId", ""+fkScheduleId);
                params.put("fkScheduleId", ""+fkScheduleId);
                params.put("travelDate", "");
                params.put("departureTime", "");
                params.put("arrivalTime", "");

                Log.d("dsjkjsdaad", params.toString());
                return params;

            }
        };
        postRequest.setShouldCache(false);

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        CustomApplication.getInstance().addToRequestQueue(postRequest);

    }

}
