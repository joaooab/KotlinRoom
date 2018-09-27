package com.example.joaofreitas.testeroomfinal.data.remoto.datamanager

import android.net.Uri
import android.util.Log
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import android.app.DownloadManager




class DataManagerService : KoinComponent {

	val repository: DataMangerClientRepository by inject()

	fun gerarBaseDeDados(token: String) {
		repository.gerarBaseDeDados(
				token,
				sucess = {
					val data = it["data"] as Map<*, *>
					val arquivo = data["arquivo"] as Map<*, *>
//					download(arquivo, token)
				},
				failure = { erro ->
					Log.e("onFailure error", erro.message)
				})
	}

	fun download(nome: String) {
		repository.download(
				nome,
				sucess = {
					it
				},
				failure = { erro ->
					Log.e("onFailure error", erro.message)
				}
		)

//		val uri = Uri.parse(url)
//		val request = DownloadManager.Request(uri)
//		request.setTitle("Base")
//		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//		request.setDestinationUri(Uri.parse("file://" + folderName + "/myfile.mp3"))
//		downloadmanager.enqueue(request);
	}
}