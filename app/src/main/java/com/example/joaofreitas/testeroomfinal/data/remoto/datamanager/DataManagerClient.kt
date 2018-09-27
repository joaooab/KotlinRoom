package com.example.joaofreitas.testeroomfinal.data.remoto.datamanager

import com.example.joaofreitas.testeroomfinal.data.remoto.Client
import com.example.joaofreitas.testeroomfinal.data.remoto.RetrofitInitializer
import com.example.joaofreitas.testeroomfinal.data.remoto.callback

class DataManagerClient : Client(), DataMangerClientRepository {

	companion object {
		const val BASE_URL_BASE_DE_DADOS = "http://192.168.99.100:8080/v1/"
		const val BASE_URL_DOWNLOAD = "http://maxsolucoes-venda.s3.amazonaws.com/BaseDados/1271/1/"
	}


	override fun gerarBaseDeDados(
			token: String,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	) {

		val headers: Map<String, String> = mapOf(
				Pair("accept", "application/json"),
				Pair("Authorization", "$bearer$token")
		)

		//TODO Aplicar Injeção de dependência
		val call = RetrofitInitializer(BASE_URL_BASE_DE_DADOS).dataManagerService().gerarBaseDeDados(headers)
		responseGenerico(call, sucess, failure)
	}

	override fun download(
			nome: String,
//			token: String,
			sucess: (sucess: Any) -> Unit,
			failure: (throwable: Throwable) -> Unit
	) {

		val headers: Map<String, String> = mapOf(
				Pair("Accept-Encoding", "gzip"),
				Pair("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//				Pair("Authorization", "$bearer$token")
		)

		val call = RetrofitInitializer(BASE_URL_DOWNLOAD).dataManagerService().download(nome, headers)
		call.enqueue(callback({ response ->
			response?.body()?.let {
				sucess(it)
			}
		}, { throwable ->
			throwable?.let {
				failure(it)
			}
		}))
	}

}