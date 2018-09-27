package com.example.joaofreitas.testeroomfinal.data.remoto.conexao

import android.util.Log
import com.example.joaofreitas.testeroomfinal.data.remoto.datamanager.DataManagerService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ConexaoService : KoinComponent{

	val repository: ConexaoClientRepository by inject()

	fun login() {
		repository.login(
				ConexaoLogin(
						login = "FmEsyuxU4RiOBtvSILpFgw==",
						password = "evSOauTX/4svY9/avBZbsg==",
						versao = "evSOauTX/4svY9/avBZbsg=="
				), sucess = {
					val token = it.get("data") as String
					gravaDadosConexao(token, ConexaoUtil().criaDadosConexao())
				}, failure = { erro ->
					Log.e("onFailure error", erro.message)
				})
	}

	fun gravaDadosConexao(token: String, conexao: Conexao) {
		repository.gravarDadosConexao(
				conexao,
				token,
				sucess = {
					val dataManagerService = DataManagerService()
					val nome = "http://maxsolucoes-venda.s3.amazonaws.com/BaseDados/1271/1/Base_000014_000_010432_000012964765_270920180229237555 7555.s3db.gz"
					dataManagerService.download(nome)
				}, failure =  { erro ->
					Log.e("onFailure error", erro.message)
				})
	}

}
