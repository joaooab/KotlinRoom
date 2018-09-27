package com.example.joaofreitas.testeroomfinal.data.remoto.datamanager

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Streaming

interface DataManagerServiceRepository {

	@GET("UpdateDeviceDataManager")
	fun gerarBaseDeDados(
			@HeaderMap headers: Map<String, String>): Call<Map<String, Any>>

	@Streaming
	@GET("{nome}")
	fun download(
			@Path("nome") nome : String,
			@HeaderMap headers: Map<String, String>): Call<ResponseBody>
}