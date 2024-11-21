package com.wavelinemedia.workmanager.data.remote

import com.wavelinemedia.workmanager.data.model.QuoteDTO
import retrofit2.http.GET

interface ApiService {

    // https://dummyjson.com/quotes/random

    @GET("quotes/random")
    suspend fun getQuote(): QuoteDTO
}
