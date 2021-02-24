package com.appalber.ciudadesespaamapswiki;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClasePeticionRetrofit {
    private String BaseUrl = "https://simplemaps.com/";
    public static void PedirJson(IRellenarSpinner llamante){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://simplemaps.com/").addConverterFactory(GsonConverterFactory.create()).build();
        IServicioPedirCiudades serviciopeticion = retrofit.create(IServicioPedirCiudades.class);
        Call<List<Ciudad>> lista_ciudades = serviciopeticion.listarCiudades();
        lista_ciudades.enqueue(new Callback<List<Ciudad>>() {
            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                List<Ciudad> lista_ciudades = response.body();
                Log.d("RESPUESTA", lista_ciudades.toString());
                llamante.rellenarSpinner(lista_ciudades);
            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                Log.d("ERROR", t.getLocalizedMessage());
            }
        });
    }
    public interface IRellenarSpinner {
        public void rellenarSpinner(List<Ciudad> ciudades);


    }

}
