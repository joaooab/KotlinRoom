package com.example.joaofreitas.testeroomfinal.data.remoto.datamanager

interface DataMangerClientRepository {

	fun gerarBaseDeDados(
			token: String,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	)

	fun download(
			nome : String,
//			token: String,
			sucess: (sucess: Any) -> Unit,
			failure: (throwable: Throwable) -> Unit
	)

}