package com.example.joaofreitas.testeroomfinal.data.remoto.conexao

import com.example.joaofreitas.testeroomfinal.data.remoto.Client
import com.example.joaofreitas.testeroomfinal.data.remoto.RetrofitInitializer

class ConexaoClient : Client(), ConexaoClientRepository {

	companion object {
		const val BASE_URL_CONEXAO = "http://192.168.99.100:2026/v1/"
	}

	override fun login(
			conexaoLogin: ConexaoLogin,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	) {
		//TODO Aplicar Injeção de dependência
		val call = RetrofitInitializer(BASE_URL_CONEXAO).conexaoService().iniciar(conexaoLogin)
		responseGenerico(call, sucess, failure)
	}

	override fun gravarDadosConexao(
			conexao: Conexao,
			token: String,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	) {
		//TODO Mover para Classe Client
		val headers: Map<String, String> = mapOf(
				Pair("accept", "application/json"),
				Pair("Authorization", "$bearer$token")
		)

		//TODO Aplicar Injeção de dependência
		val call = RetrofitInitializer(BASE_URL_CONEXAO).conexaoService().gravarDadosConexao(headers, conexao)
		responseGenerico(call, sucess, failure)
	}

}