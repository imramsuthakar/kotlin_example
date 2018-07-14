package com.example.recyclerviewkotlin

import com.example.recyclerviewkotlin.models.SampleData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WikiApiService {

    companion object {

        fun create(): WikiApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build()

            return retrofit.create(WikiApiService::class.java)
        }

    }

    @GET("posts")
    fun getSampleData():Observable<List<SampleData>>
}