package com.example.act13maps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.act13maps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button mSatelite;
    private Button mRelieve;
    private Button mPredeterminado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getLocalizacion();

        mSatelite = (Button) findViewById(R.id.btnSatelite);
        mRelieve = (Button) findViewById(R.id.btnRelieve);
        mPredeterminado = (Button) findViewById(R.id.btnPredeterminado);

        mSatelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        mRelieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });
        mPredeterminado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lugares(googleMap);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);


    }
    public void lugares(GoogleMap googleMap){
        float v = 16;
        mMap = googleMap;
        final LatLng punto1 = new LatLng(20.6941354,-103.3772634);
        final LatLng punto2 = new LatLng(20.7122876,-103.3790814);
        mMap.addMarker(new MarkerOptions().position(punto1).title("Midtown"));
        mMap.addMarker(new MarkerOptions().position(punto2).title("Plaza patria"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1, v));


    }

}