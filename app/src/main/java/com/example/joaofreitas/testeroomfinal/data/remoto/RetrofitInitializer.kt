package com.example.joaofreitas.testeroomfinal.data.remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

	companion object {
		const val BASE_URL = ""
	}

	private val retrofit = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()


	fun retrofitService(): RetrofitService = retrofit.create(RetrofitService::class.java)


}