package com.example.app_sensors.control;

import android.util.Log;

import com.example.app_sensors.view.View_Main_Startup;

public class Control_Main {

    View_Main_Startup class_View_Main;
    private static final String logtag = "Control_Main";

    public Control_Main(View_Main_Startup view_main) {
        class_View_Main = view_main;
        Log.i(logtag, "Control_Main, start");

    }

}