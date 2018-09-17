package com.example.joaofreitas.testeroomfinal.presenter

import br.com.maximasistemas.arch.mvp.presenter.MvpListaPresenter
import com.example.joaofreitas.testeroomfinal.model.Pedido
import com.example.joaofreitas.testeroomfinal.views.activity.ui.ListPedidoView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListPedidoPresenter : MvpListaPresenter<ListPedidoView>() {

	var pedidos = mutableListOf<Pedido>()

	override fun iniciar() {
		super.iniciar()
		consultarPedidos()
	}

	private fun consultarPedidos() {
		view?.mostrarCarregando()

		if (pedidos.isNotEmpty()) {
			processarTela(pedidos)
			return
		}

		doAsync {
			uiThread {
				processarTela()
			}
		}
	}

//	fun obtemTodosPedidos(pedidoRepository: PedidoRepository): LiveData<List<Pedido>> {
//		return
//	}
}