package com.example.joaofreitas.testeroomfinal.ui.pedido.formulario

import br.com.maximasistemas.arch.mvp.presenter.MvpPresenter
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FormularioPedidoPresenter : MvpPresenter<FormularioPedidoView>() {

	var pedidoRepository: PedidoRepository? = null

	override fun iniciar() {
		super.iniciar()
		pedidoRepository = obterRepository()
	}

	override fun processarTela() {}

	private fun obterRepository() = view?.obterRepository()

	fun salvaPedido(pedido: Pedido) {
		doAsync {
			pedidoRepository?.adicionaPedido(pedido)
			uiThread {
				view?.finalizarTela()
			}
		}
	}
}