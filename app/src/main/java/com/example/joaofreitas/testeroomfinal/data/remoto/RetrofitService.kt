package com.example.joaofreitas.testeroomfinal.data.remoto

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
	@GET("prodtuos")
	fun list() : Call<List<*>>
}