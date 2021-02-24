package com.appalber.ciudadesespaamapswiki;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ClasePeticionRetrofit.IRellenarSpinner {

    private GoogleMap mMap;
    Spinner spinner_ciudades;
    Button boton_mostrar;
    Marker marcador;
    Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_layout_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spinner_ciudades = findViewById(R.id.spinnerciudades);
        boton_mostrar = findViewById(R.id.btn_mostrar);
        View.OnClickListener boton_oyente = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMarcador();
            }
        };
        boton_mostrar.setOnClickListener(boton_oyente);


    }

    private void mostrarMarcador() {
        if(marcador!=null){
            marcador.remove();
        }
        Ciudad c = (Ciudad)spinner_ciudades.getSelectedItem();
        Double lat =Double.parseDouble(c.getLat());
        Double lng =Double.parseDouble(c.getLng());
        LatLng posicion = new LatLng(lat,lng);
        MarkerOptions opciones_marcador = new MarkerOptions().position(posicion).title(c.getCity());
        marcador = mMap.addMarker(opciones_marcador);
        marcador.setTag(c);

        //movecamera and zoom
        //animate camera para mover.
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 7));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Ciudad ciudad_clicada=(Ciudad)marker.getTag();
                Log.d("Clicado", ciudad_clicada.toString());
                Intent intent = new Intent(MapsActivity.this, Activity_webview.class);
                intent.putExtra("Ciudad", ciudad_clicada.getCity());
                startActivity(intent);
                return false;
            }
    });

    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //rellenamos el spinner llamando al método
        ClasePeticionRetrofit.PedirJson(this);
        // Add a marker in Sydney and move the camera

    }

    @Override
    public void rellenarSpinner(List<Ciudad> ciudades) {
        ArrayAdapter<Ciudad> adaptador = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ciudades);
        spinner_ciudades.setAdapter(adaptador);
    }
}