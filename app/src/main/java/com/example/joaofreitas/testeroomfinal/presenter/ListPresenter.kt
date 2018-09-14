package com.example.joaofreitas.testeroomfinal.presenter

import android.content.Context
import br.com.maximasistemas.arch.mvp.presenter.MvpListaPresenter
import com.example.joaofreitas.testeroomfinal.dao.PedidoDao
import com.example.joaofreitas.testeroomfinal.database.AppDatabase
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.views.activity.ui.ListPedidoView

class ListPresenter : MvpListaPresenter<ListPedidoView>() {
	private lateinit var pedidoDao: PedidoDao

	lateinit var database: AppDatabase

	fun inciarDatabase(context: Context) {
		database = Database.getInstance(context)
	}


	override fun iniciar() {
		super.iniciar()


		pedidoDao = database.pedidoDao()
		consultarPedidos()
	}

	private fun consultarPedidos() {
		view?.mostrarCarregando()

	}

}