package com.fahmy.news.data.network


import com.fahmy.news.data.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("everything")
    suspend fun search(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("Size=") Size: Int,
    ): ResponseDto

}