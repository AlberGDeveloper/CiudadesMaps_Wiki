package com.appalber.ciudadesespaamapswiki;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IServicioPedirCiudades {
    @GET("static/data/country-cities/es/es.json")
    Call<List<Ciudad>> listarCiudades();
}
