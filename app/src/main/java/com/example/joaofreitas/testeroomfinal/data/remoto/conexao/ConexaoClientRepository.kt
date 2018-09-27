package com.example.joaofreitas.testeroomfinal.data.remoto.conexao

interface ConexaoClientRepository {

	fun login(
			conexaoLogin: ConexaoLogin,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	)

	fun gravarDadosConexao(
			conexao: Conexao,
			token: String,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit
	)
}