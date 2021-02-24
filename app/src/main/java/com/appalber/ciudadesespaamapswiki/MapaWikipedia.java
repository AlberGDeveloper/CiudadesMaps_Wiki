/**package com.appalber.ciudadesespaamapswiki;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

 ACTIVITY PARA MOSTRAR TODOS LOS MARCADORES EN EL MAPA. FUNCIONA OKEY

public class MapaWikipedia extends FragmentActivity implements OnMapReadyCallback, ClasePeticionRetrofit.IRellenarSpinner{

    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void rellenarSpinner(List<Ciudad> ciudades) {
    //Ahora no se rellenaría nada. Ahora se añaden los marcadores
        for (Ciudad c : ciudades){
            Double latidud = Double.parseDouble(c.getLat());
            Double longitud = Double.parseDouble(c.getLng());
            LatLng posicion = new LatLng(latidud, longitud);
            MarkerOptions opciones_marcador = new MarkerOptions().position(posicion).title(c.getCity());
            mapa.addMarker(opciones_marcador);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    mapa = googleMap;
    //como antes. Cuando se cargue el mapa, hago la petición
    ClasePeticionRetrofit.PedirJson(this);
    }
}**/