package com.example.controlarterial.dao

import retrofit2.http.*

import com.example.controlarterial.entity.TomaArterial
import com.example.controlarterial.entity.TomasArteriales
import retrofit2.http.*

interface tomaDAO {

    @GET("tomaArterial")
    suspend fun getItems(): TomasArteriales

    @GET("tomaArterial/{uuid}")
    suspend fun getItem(@Path("uuid") uuid: String): TomaArterial

    @POST("tomaArterial")
    suspend fun createItem( @Body items: List<TomaArterial>): TomasArteriales

    @PUT("tomaArterial/{uuid}")
    suspend fun updateItem(@Path("uuid") uuid: String, @Body item: TomaArterial): TomaArterial

    @DELETE("tomaArterial/{uuid}")
    suspend fun deleteItem(@Path("uuid") uuid: String)
}