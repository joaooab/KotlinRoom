package com.example.joaofreitas.testeroomfinal.data.remoto

import retrofit2.Call

abstract class Client {

	companion object {
		val bearer = "Bearer "
	}

	protected fun responseGenerico(
			call: Call<Map<String, Any>>,
			sucess: (mapa: Map<String, Any>) -> Unit,
			failure: (throwable: Throwable) -> Unit) {

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