package com.example.joaofreitas.testeroomfinal.ui.pedido.formulario

import br.com.maximasistemas.arch.mvp.presenter.MvpPresenter
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.views.activity.ui.FormularioPedidoView
import org.jetbrains.anko.doAsync

class FormularioPedidoPresenter : MvpPresenter<FormularioPedidoView>() {

	override fun processarTela() {
		view?.exibeDados()
	}

	private fun obterRepository() = view?.obterRepository()

	fun salvaPedido(pedido: Pedido) {
		doAsync {
			obterRepository()?.adicionaPedido(pedido)
		}
	}

}