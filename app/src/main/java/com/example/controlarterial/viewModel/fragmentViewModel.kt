package com.example.controlarterial.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.controlarterial.AuthInterceptor
import com.example.controlarterial.dao.tomaDAO
import com.example.controlarterial.entity.TomaArterial
import com.example.controlarterial.entity.TomasArteriales
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class fragmentViewModel: ViewModel() {
    private val _textLiveData: MutableLiveData<ArrayList<TomaArterial>> = MutableLiveData()
    val textLiveData: LiveData<ArrayList<TomaArterial>> = _textLiveData
    lateinit var tomas : TomasArteriales

    fun recargarLista() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder().setPrettyPrinting().create()

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("7MAkzfaL82q-CnDVrx5cS2UnR6KEAJO6k4z2vdAudWRYW1UJsg"))
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudapi.co.uk/api/v1/" +
                    "")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(tomaDAO::class.java)



        GlobalScope.launch(Dispatchers.IO) {
            tomas = apiService.getItems()
            _textLiveData.postValue(tomas.items as ArrayList<TomaArterial>?)


            print(gson.toJson(tomas.items))
        }
    }

}