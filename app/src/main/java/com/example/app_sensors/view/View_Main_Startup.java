package com.example.app_sensors.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.app_sensors.R;
import com.example.app_sensors.control.Control_Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class View_Main_Startup extends AppCompatActivity implements SensorEventListener {

    private static final String logtag = "View_Main_Startup";
    Control_Main class_Control_Main;

    SensorManager sensorManager;
    private TriggerEventListener mTriggerEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(logtag, "onCreate, start");

        setContentView(R.layout.activity_main);

        class_Control_Main = new Control_Main(this);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mTriggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                Log.i(logtag, "onCreate.onTrigger");
            }
        };
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BODY_SENSORS},00);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACTIVITY_RECOGNITION},00);

        accelerationSensor();
        barometerSensor();
        gestureSensor();
        gravitySensor();
        gyroscopeSensor();
        heartSensor();
        lightSensor();
        magneticSensor();
        motionSensor();
        orientationSensor();
        proximitySensor();
        rotationSensor();
        stepSensor();
        tiltSensor();

        //usortert
        //sjekkSensorAdresser, sensor={Sensor name="SContext", vendor="Samsung", version=1, type=65586, maxRange=1.0, resolution=0.0, power=0.001, minDelay=0}

        //ukjente sensorer
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 BLUE", vendor="MAXIM", version=1, type=65574, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=9950}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 GREEN", vendor="MAXIM", version=1, type=65573, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=9950}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 HRM RAW", vendor="MAXIM", version=1, type=65561, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=10000}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 IR", vendor="MAXIM", version=1, type=65571, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=9950}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 Rear ALS", vendor="MAXIM", version=1, type=65577, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=10000}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 Rear PROX", vendor="MAXIM", version=1, type=65580, maxRange=3.0, resolution=1.0, power=1.0, minDelay=0}
        //sjekkSensorAdresser, sensor={Sensor name="MAX86915 RED", vendor="MAXIM", version=1, type=65572, maxRange=524287.0, resolution=1.0, power=1.0, minDelay=9950}

        //sjekkAdresser();

        //sjekkSensorAdresser();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.i(logtag, "onSensorChanged, event="+event+" event.sensor="+event.sensor+" event.sensor.getType()="+event.sensor.getType());
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            double ax = Math.floor(event.values[0]);
            double ay = Math.floor(event.values[1]);
            double az = Math.floor(event.values[2]);
            TextView acceleratorLabel = (TextView) findViewById(R.id.accelerator);
            acceleratorLabel.setText("Accelerator: X=" + ax + " Y=" + ay + " Z=" + az);
            //Log.i(logtag, "onSensorChanged, Accelerator 1: X=" + ax + " Y=" + ay + " Z=" + az);
        } else if (event.sensor.getType()==10){
            double ax=Math.floor(event.values[0]);
            double ay=Math.floor(event.values[1]);
            double az=Math.floor(event.values[2]);
            TextView acceleratorLabel = (TextView) findViewById(R.id.accelerator);
            acceleratorLabel.setText("Accelerator: X="+ax+" Y="+ay+" Z="+az);
            //Log.i(logtag, "onSensorChanged, Accelerator 10: X=" + ax + " Y=" + ay + " Z=" + az);
        } else if (event.sensor.getType()==35){
            double ax=Math.floor(event.values[0]);
            double ay=Math.floor(event.values[1]);
            double az=Math.floor(event.values[2]);
            TextView acceleratorLabel = (TextView) findViewById(R.id.accelerator);
            acceleratorLabel.setText("Accelerator: X="+ax+" Y="+ay+" Z="+az);
            //Log.i(logtag, "onSensorChanged, Accelerator 35: X=" + ax + " Y=" + ay + " Z=" + az);
        } else if (event.sensor.getType()==6) { //gesture
            TextView barometerLabel = (TextView) findViewById(R.id.barometer);
            //Log.i(logtag, "onSensorChanged, barometer 6, verdi=" + Math.floor(event.values[0]));
            double trykk = Math.floor(event.values[0]);
            if ( trykk >= 1025){
                barometerLabel.setText("Barometer: " + trykk+", høytrykk");
            } else if ( trykk >= 1013){
                barometerLabel.setText("Barometer: " + trykk+", lett høytrykk");
            } else if ( trykk >= 980){
                barometerLabel.setText("Barometer: " + trykk+", lett lavtrykk");
            } else if ( trykk < 970){
                barometerLabel.setText("Barometer: " + trykk+", lavtrykk");
            } else {
                barometerLabel.setText("Barometer: " + trykk);
            }
        } else if (event.sensor.getType()==9)  { //gravity
            //developer.android.com om gravity sensor: "The units are the same as those used by the acceleration sensor (m/s2), and the coordinate system is the same as the one used by the acceleration sensor."
            //Log.i(logtag, "onSensorChanged, gravity sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            double a2 = Math.floor(event.values[1]);
            double a3 = Math.floor(event.values[2]);
            //Log.i(logtag, "onSensorChanged, gravity sensor, X="+a1+", Y="+a2+", Z="+a3);
            TextView gravityLabel = (TextView) findViewById(R.id.gravity);
            gravityLabel.setText("Gravity: X="+a1+", Y="+a2+", Z="+a3);
        } else if (event.sensor.getType()==4)  { //gyroscope 1
            //Log.i(logtag, "onSensorChanged, 4: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            double a2 = Math.floor(event.values[1]);
            double a3 = Math.floor(event.values[2]);
            TextView gyroscopeLabel = (TextView) findViewById(R.id.gyroscope);
            gyroscopeLabel.setText("Gyroscope: X="+a1+", Y="+a2+", Z="+a3);
            //Log.i(logtag, "onSensorChanged, Gyroscope: X="+a1+", Y="+a2+", Z="+a3);
        } else if (event.sensor.getType()==16) { //gyroscope 2, ukalibrert
            //Log.i(logtag, "onSensorChanged, gyroscope uncalibrated: " + event.sensor.getType());
            for (int it = 0; it < event.values.length; it++) {
                Object sensor = event.values[it];
                //System.out.println(it.next());
                //Log.i(logtag, "onSensorChanged, sensor "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==21) {
            Log.i(logtag, "onSensorChanged, heart rate sensor: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                //System.out.println(it.next());
                Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==2) {//"AK09916C Magnetic field Sensor"
            //Log.i(logtag, "onSensorChanged, magnetic sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            double a2 = Math.floor(event.values[1]);
            double a3 = Math.floor(event.values[2]);
            TextView magnetismLabel = (TextView) findViewById(R.id.magnetism);
            magnetismLabel.setText("Magnetism: X="+a1+", Y="+a2+", Z="+a3);
            //Log.i(logtag, "onSensorChanged, magnetic sensor: X="+a1+", Y="+a2+", Z="+a3);
        } else if (event.sensor.getType()==14) {//"Uncalibrated Magnetic Sensor"
            //Log.i(logtag, "onSensorChanged, magnetic sensor: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                //System.out.println(it.next());
                //Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==17) {//"SAMSUNG Significant Motion Sensor"
            Log.i(logtag, "onSensorChanged, motion sensor: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                //System.out.println(it.next());
                Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==5) {//"TMD4906 lux Sensor"
            //Log.i(logtag, "onSensorChanged, lux sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            TextView lumenLabel = (TextView) findViewById(R.id.lumen);
            lumenLabel.setText("Lumen: "+a1);
        } else if (event.sensor.getType()==3) {//"Orientation Sensor"
            //Log.i(logtag, "onSensorChanged, Orientation Sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            double a2 = Math.floor(event.values[1]);
            double a3 = Math.floor(event.values[2]);
            TextView orientationLabel = (TextView) findViewById(R.id.orientation);
            orientationLabel.setText("Orientation: Z="+a1+" X="+a2+" Y="+a3);
        } else if (event.sensor.getType()==27) {//"Screen Orientation Sensor"
            //Log.i(logtag, "onSensorChanged, Screen Orientation Sensor: "+event.sensor.getType());
            TextView orientationLabel = (TextView) findViewById(R.id.orientation2);
            if (event.values[0]==0.0){
                orientationLabel.setText("Screen orientation: Portrait");
            } else {
                orientationLabel.setText("Screen orientation: Landscape");
            }

        } else if (event.sensor.getType()==8) {//proximitySensor
            //Log.i(logtag, "onSensorChanged, proximity Sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            TextView proximityLabel = (TextView) findViewById(R.id.proximity);
            proximityLabel.setText("Proximity: "+a1+" cm");
        } else if (event.sensor.getType()==11) {//"Samsung Rotation Vector"
            //Log.i(logtag, "onSensorChanged, Rotation Sensor 1: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                //Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==15) {//Samsung Game Rotation Vector"
            //Log.i(logtag, "onSensorChanged, Rotation Sensor 2: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            double a2 = Math.floor(event.values[1]);
            double a3 = Math.floor(event.values[2]);
            TextView rotationLabel = (TextView) findViewById(R.id.rotation);
            rotationLabel.setText("Rotation: X="+a1+" Y="+a2+" Z="+a3);
        } else if (event.sensor.getType()==18) {//steps
            Log.i(logtag, "onSensorChanged, steps Sensor 1: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==19) {//steps
            Log.i(logtag, "onSensorChanged, steps Sensor 1: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        } else if (event.sensor.getType()==22) {//"SAMSUNG Tilt Detector"
            //Log.i(logtag, "onSensorChanged, tilt Sensor: "+event.sensor.getType());
            double a1 = Math.floor(event.values[0]);
            TextView tiltLabel = (TextView) findViewById(R.id.tilt);
            tiltLabel.setText("Tilt: "+a1);
        } else {
            Log.i(logtag, "onSensorChanged, udefinert sensor: "+event.sensor.getType());
            for (int it = 0;it < event.values.length; it++ ) {
                Object sensor = event.values[it];
                //System.out.println(it.next());
                Log.i(logtag, "onSensorChanged, value "+it+"="+sensor);
            }
        }
        //Log.i(logtag, "onSensorChanged, event="+event+", event.sensor="+event.sensor+", event.sensor.getType()="+event.sensor.getType()+", event.values[0]="+event.values[0]);
    }

    public static float cpuTemperature() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if(line!=null) {
                float temp = Float.parseFloat(line);
                return temp / 1000.0f;
            }else{
                return 51.0f;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    private void sjekkSensorAdresser(){
        for (int i=0; i<=35; i++) {
            //sjekkSensor(i);
        }
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        Iterator it =deviceSensors.iterator();
        for (it = deviceSensors.iterator();it.hasNext(); ) {
            Object sensor = it.next();
            //System.out.println(it.next());
            Log.i(logtag, "sjekkSensorAdresser, sensor="+sensor);
        }

    }

    private void sjekkSensor(int sensor){
        //SensorManager mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor resultat= sensorManager.getDefaultSensor(sensor);
        if (resultat != null) {
            Log.i(logtag, "sjekkSensor, sensor="+sensor+" resultat="+resultat);
        }
    }

    private void accelerationSensor(){
        //#1 og #35 er veldig enige. #10 gir alltid lavere tall
        Log.i(logtag, "accelerationSensor");
        //sensor=1 resultat={Sensor name="LSM6DSL Acceleration Sensor", vendor="STM", version=1, type=1, maxRange=78.4532, resolution=0.0023942017, power=0.25, minDelay=2000}
        Sensor sensor1 = sensorManager.getDefaultSensor(1);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        //sensor=10 resultat={Sensor name="Linear Acceleration Sensor", vendor="Samsung Electronics", version=3, type=10, maxRange=78.4532, resolution=0.0023942017, power=6.0, minDelay=10000}
        Sensor sensor10 = sensorManager.getDefaultSensor(10);
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(10), SensorManager.SENSOR_DELAY_NORMAL);
        //sensor=35 resultat={Sensor name="Acceleration Sensor UnCalibrated", vendor="STM", version=1, type=35, maxRange=78.4532, resolution=0.0023942017, power=0.25, minDelay=2000}
        Sensor sensor35 = sensorManager.getDefaultSensor(35);
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(35), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "accelerationSensor sensor1="+sensor1);
        Log.i(logtag, "accelerationSensor sensor10="+sensor10);
        Log.i(logtag, "accelerationSensor sensor35="+sensor35);
    }

    private void barometerSensor(){
        Log.i(logtag, "barometerSensor");
        //sensor=6 resultat={Sensor name="LPS22H Barometer Sensor", vendor="STMicroelectronics", version=1, type=6, maxRange=1260.0, resolution=2.4414062E-4, power=1.0, minDelay=100000}
        Sensor sensor6 = sensorManager.getDefaultSensor(6);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(6), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "barometerSensor sensor6="+sensor6);
    }

    private void gestureSensor(){
        Log.i(logtag, "gestureSensor");
        //sensor=25 resultat={Sensor name="SAMSUNG Pick Up Gesture", vendor="Samsung Inc.", version=1, type=25, maxRange=1.0, resolution=1.0, power=0.3, minDelay=-1}
        Sensor sensor25 = sensorManager.getDefaultSensor(25);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(25), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "gestureSensor sensor25="+sensor25);
    }

    private void gravitySensor(){
        Log.i(logtag, "gravitySensor");
        //sensor=9 resultat={Sensor name="Gravity Sensor", vendor="Samsung Electronics", version=3, type=9, maxRange=19.6133, resolution=5.9604645E-8, power=6.0, minDelay=10000}
        Sensor sensor9 = sensorManager.getDefaultSensor(9);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(9), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "gravitySensor sensor9="+sensor9);
    }

    private void gyroscopeSensor(){
        Log.i(logtag, "gyroscopeSensor");
        //sensor=4 resultat={Sensor name="LSM6DSL Gyroscope Sensor", vendor="STM", version=1, type=4, maxRange=17.453293, resolution=6.1086525E-4, power=6.1, minDelay=2000}
        Sensor sensor4 = sensorManager.getDefaultSensor(4);
        //sensor=16 resultat={Sensor name="Gyroscope sensor UnCalibrated", vendor="STM", version=1, type=16, maxRange=17.453293, resolution=6.1086525E-4, power=6.1, minDelay=2000}
        Sensor sensor16 = sensorManager.getDefaultSensor(16);
        //sjekkSensorAdresser, sensor={Sensor name="Interrupt Gyroscope Sensor", vendor="STM", version=1, type=65579, maxRange=17.453293, resolution=6.1086525E-4, power=0.061, minDelay=20000}
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(4), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(16), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "gyroscopeSensor sensor4="+sensor4);
        Log.i(logtag, "gyroscopeSensor sensor16="+sensor16);
    }

    private void heartSensor(){
        Log.i(logtag, "heartSensor");
        //sensor=21 resultat={Sensor name="HeartRate Sensor", vendor="Samsung Electronics", version=1, type=21, maxRange=200.0, resolution=1.0, power=0.0, minDelay=1000000}
        Sensor sensor21 = sensorManager.getDefaultSensor(21);
        Log.i(logtag, "heartSensor sensor21="+sensor21);
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(21), SensorManager.SENSOR_DELAY_FASTEST);
        //sjekkSensorAdresser, sensor={Sensor name="HRM Sensor", vendor="Samsung Electronics", version=1, type=65562, maxRange=200.0, resolution=1.0, power=0.0, minDelay=0}
    }

    private void magneticSensor(){
        Log.i(logtag, "magneticSensor");
        //sensor=2 resultat={Sensor name="AK09916C Magnetic field Sensor", vendor="Asahi Kasei Microdevices", version=1, type=2, maxRange=2000.0, resolution=0.06, power=6.0, minDelay=10000}
        Sensor sensor2 = sensorManager.getDefaultSensor(2);
        //sensor=14 resultat={Sensor name="Uncalibrated Magnetic Sensor", vendor="Asahi Kasei Microdevices", version=1, type=14, maxRange=2000.0, resolution=0.06, power=6.0, minDelay=10000}
        Sensor sensor14 = sensorManager.getDefaultSensor(14);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(2), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(14), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "magneticSensor sensor2="+sensor2);
        Log.i(logtag, "magneticSensor sensor14="+sensor14);
    }

    private void motionSensor(){
        Log.i(logtag, "motionSensor");
        //sensor=17 resultat={Sensor name="SAMSUNG Significant Motion Sensor", vendor="Samsung Inc.", version=2, type=17, maxRange=1.0, resolution=1.0, power=0.3, minDelay=-1}
        Sensor sensor17 = sensorManager.getDefaultSensor(17);
        //sjekkSensorAdresser, sensor={Sensor name="Motion Sensor", vendor="Samsung Electronics", version=1, type=65559, maxRange=200.0, resolution=0.0, power=0.0, minDelay=0}
        //sjekkSensorAdresser, sensor={Sensor name="SAMSUNG Wake Up Motion", vendor="Samsung Inc.", version=1, type=65590, maxRange=1.0, resolution=1.0, power=0.3, minDelay=0}
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(17), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.requestTriggerSensor(mTriggerEventListener, sensor17);
        Log.i(logtag, "motionSensor sensor17="+sensor17);
    }

    private void lightSensor(){
        Log.i(logtag, "lightSensor");
        //sensor=5 resultat={Sensor name="TMD4906 lux Sensor", vendor="AMS, Inc.", version=1, type=5, maxRange=60000.0, resolution=1.0, power=0.75, minDelay=200000}
        Sensor sensor5 = sensorManager.getDefaultSensor(5);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(5), SensorManager.SENSOR_DELAY_NORMAL);
        //sjekkSensorAdresser, sensor={Sensor name="Light Flicker Sensor", vendor="Samsung Electronics", version=1, type=65583, maxRange=60000.0, resolution=0.0, power=0.001, minDelay=200000}
        //sjekkSensorAdresser, sensor={Sensor name="SX9320 Grip Sensor", vendor="SEMTECH", version=512, type=65560, maxRange=5.0, resolution=5.0, power=0.75, minDelay=0}
        //sjekkSensorAdresser, sensor={Sensor name="TMD4906 RGB IR Sensor", vendor="AMS, Inc.", version=1, type=65578, maxRange=60000.0, resolution=1.0, power=0.75, minDelay=200000}
        //sjekkSensorAdresser, sensor={Sensor name="TMD4906 RGB Sensor", vendor="AMS, Inc.", version=1, type=65587, maxRange=60000.0, resolution=1.0, power=0.75, minDelay=200000}
        Log.i(logtag, "lightSensor sensor5="+sensor5);
    }

    private void orientationSensor(){
        Log.i(logtag, "orientationSensor");
        //sensor=3 resultat={Sensor name="Orientation Sensor", vendor="Samsung Electronics", version=1, type=3, maxRange=360.0, resolution=0.00390625, power=6.0, minDelay=10000}
        Sensor sensor3 = sensorManager.getDefaultSensor(3);
        //sensor=27 resultat={Sensor name="Screen Orientation Sensor", vendor="Samsung Electronics", version=3, type=27, maxRange=255.0, resolution=255.0, power=0.0, minDelay=0}
        Sensor sensor27 = sensorManager.getDefaultSensor(27);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(3), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(27), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "orientationSensor sensor3="+sensor3);
        Log.i(logtag, "orientationSensor sensor27="+sensor27);
    }

    private void proximitySensor(){
        Log.i(logtag, "proximitySensor");
        //sensor=8 resultat={Sensor name="TMD4906 Proximity Sensor", vendor="AMS, Inc.", version=1, type=8, maxRange=8.0, resolution=8.0, power=0.75, minDelay=0}
        Sensor sensor8 = sensorManager.getDefaultSensor(8);
        //sjekkSensorAdresser, sensor={Sensor name="Proximity Alert Sensor", vendor="Samsung Electronics", version=1, type=65582, maxRange=10.0, resolution=10.0, power=0.75, minDelay=0}
        //sjekkSensorAdresser, sensor={Sensor name="Proximity Pocket", vendor="AMS, Inc.", version=1, type=65589, maxRange=8.0, resolution=8.0, power=0.75, minDelay=0}
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(8), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "proximitySensor sensor8="+sensor8);
    }

    private void rotationSensor(){
        Log.i(logtag, "rotationSensor");
        //sensor=11 resultat={Sensor name="Samsung Rotation Vector", vendor="Samsung Electronics", version=1, type=11, maxRange=1.0, resolution=5.9604645E-8, power=6.0, minDelay=10000}
        Sensor sensor11 = sensorManager.getDefaultSensor(11);
        //sensor=15 resultat={Sensor name="Samsung Game Rotation Vector", vendor="Samsung Electronics", version=1, type=15, maxRange=1.0, resolution=5.9604645E-8, power=6.0, minDelay=10000}
        Sensor sensor15 = sensorManager.getDefaultSensor(15);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(11), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(15), SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(logtag, "rotationSensor sensor11="+sensor11);
        Log.i(logtag, "rotationSensor sensor15="+sensor15);
    }

    private void stepSensor(){
        Log.i(logtag, "stepSensor");
        //sensor=18 resultat={Sensor name="SAMSUNG Step Detector Sensor", vendor="Samsung Inc.", version=1, type=18, maxRange=1.0, resolution=1.0, power=0.3, minDelay=0}
        Sensor sensor18 = sensorManager.getDefaultSensor(18);
        //sensor=19 resultat={Sensor name="SAMSUNG Step Counter Sensor", vendor="Samsung Inc.", version=1, type=19, maxRange=4.2949673E9, resolution=1.0, power=0.3, minDelay=0}
        Sensor sensor19 = sensorManager.getDefaultSensor(19);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(18), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(19), SensorManager.SENSOR_DELAY_FASTEST);
        Log.i(logtag, "stepSensor sensor18="+sensor18);
        Log.i(logtag, "stepSensor sensor19="+sensor19);
    }

    private void tiltSensor(){
        Log.i(logtag, "tiltSensor");
        //sensor=22 resultat={Sensor name="SAMSUNG Tilt Detector", vendor="Samsung Inc.", version=1, type=22, maxRange=1.0, resolution=1.0, power=0.3, minDelay=0}
        Sensor sensor22 = sensorManager.getDefaultSensor(22);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(22), SensorManager.SENSOR_DELAY_FASTEST);
        Log.i(logtag, "tiltSensor sensor22="+sensor22);
    }


}