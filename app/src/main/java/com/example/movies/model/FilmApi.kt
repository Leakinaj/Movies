package com.example.movies.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {
    abstract val isSuccessful: Boolean

    @GET("movie/top_rated")
    suspend fun getFilmList(
        @Query("api_key") apiKey: String = "ae8730eebd10b0c7e7181e7162b056f1"
    ): Response<Film>

    @GET("movie/{id}")
    suspend fun getFilmById(
        @Path("id") id: String?,
        @Query("api_key") apiKey: String = "ae8730eebd10b0c7e7181e7162b056f1"
    ): Response<Result>

//    @GET("movie/popular?api_key=ae8730eebd10b0c7e7181e7162b056f1")
//    fun getFilmList(): Call<Result>
//
//    @GET("movie/{movie_id}?api_key=ae8730eebd10b0c7e7181e7162b056f1")
//    fun getFilmById(id:Int): Call<Film>

}