package com.example.joaofreitas.testeroomfinal.data.remoto

import com.example.joaofreitas.testeroomfinal.data.remoto.conexao.ConexaoServiceRepository
import com.example.joaofreitas.testeroomfinal.data.remoto.datamanager.DataManagerServiceRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitInitializer(BASE_URL: String) {

	private val retrofitConexao = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()

	var logging = HttpLoggingInterceptor()
			.setLevel(HttpLoggingInterceptor.Level.BODY)

	var client = OkHttpClient.Builder()
			.addInterceptor(logging)
			.connectTimeout(5, TimeUnit.MINUTES)
			.readTimeout(5, TimeUnit.MINUTES).build()

	private val retrofitBaseDeDados = Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()

	fun conexaoService(): ConexaoServiceRepository = retrofitConexao.create(ConexaoServiceRepository::class.java)
	fun dataManagerService(): DataManagerServiceRepository = retrofitBaseDeDados.create(DataManagerServiceRepository::class.java)

}