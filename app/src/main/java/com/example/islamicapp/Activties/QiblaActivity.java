package com.example.islamicapp.Activties;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.islamicapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class QiblaActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer, magnetometer;
    private FusedLocationProviderClient fusedLocationClient;

    private float[] gravity, geomagnetic;
    private double userLatitude, userLongitude;

    private ImageView qiblaArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla);

        qiblaArrow = findViewById(R.id.qibla_arrow);

        // Initialize sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Initialize location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Request location permissions if not already granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    userLatitude = location.getLatitude();
                    userLongitude = location.getLongitude();
                } else {
                    Toast.makeText(QiblaActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double calculateQiblaDirection(double userLatitude, double userLongitude) {
        double makkahLatitude = 21.4225;
        double makkahLongitude = 39.8262;

        double deltaLongitude = Math.toRadians(makkahLongitude - userLongitude);
        double userLatRad = Math.toRadians(userLatitude);
        double makkahLatRad = Math.toRadians(makkahLatitude);

        double y = Math.sin(deltaLongitude) * Math.cos(makkahLatRad);
        double x = Math.cos(userLatRad) * Math.sin(makkahLatRad) - Math.sin(userLatRad) * Math.cos(makkahLatRad) * Math.cos(deltaLongitude);
        double bearing = Math.toDegrees(Math.atan2(y, x));

        bearing = (bearing + 360) % 360;  // Normalize to 0-360 degrees
        return bearing;
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                gravity = event.values;
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                geomagnetic = event.values;

            if (gravity != null && geomagnetic != null) {
                float[] R = new float[9];
                float[] I = new float[9];
                if (SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)) {
                    float[] orientation = new float[3];
                    SensorManager.getOrientation(R, orientation);
                    float azimuthInRadians = orientation[0];
                    float azimuthInDegrees = (float) Math.toDegrees(azimuthInRadians);
                    azimuthInDegrees = (azimuthInDegrees + 360) % 360;

                    float qiblaDirection = (float) calculateQiblaDirection(userLatitude, userLongitude);
                    float directionToQibla = qiblaDirection - azimuthInDegrees;

                    updateQiblaArrow(directionToQibla);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    private void updateQiblaArrow(float directionToQibla) {
        qiblaArrow.setRotation(directionToQibla);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(sensorEventListener, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission is required for Qibla finder", Toast.LENGTH_SHORT).show();
            }
        }
    }
}