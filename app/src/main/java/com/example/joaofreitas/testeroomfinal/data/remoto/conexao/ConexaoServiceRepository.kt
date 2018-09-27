package com.example.joaofreitas.testeroomfinal.data.remoto.conexao

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ConexaoServiceRepository {
	@POST("Iniciar")
	fun iniciar(@Body conexaoLogin: ConexaoLogin): Call<Map<String, Any>>

	@POST("GravarDadosConexao")
	fun gravarDadosConexao(
			@HeaderMap headers: Map<String, String>,
			@Body conexao: Conexao): Call<Map<String, Any>>
}