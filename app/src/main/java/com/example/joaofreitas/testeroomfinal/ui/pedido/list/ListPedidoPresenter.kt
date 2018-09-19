package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import android.arch.lifecycle.LiveData
import br.com.maximasistemas.arch.mvp.presenter.MvpListaPresenter
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListPedidoPresenter : MvpListaPresenter<ListPedidoView>() {

	var pedidos = mutableListOf<Pedido>()

	override fun iniciar() {
		super.iniciar()
		consultarPedidos(obterRepository())
	}

	private fun obterRepository() = view?.obterRepository()

	fun consultarPedidos(repository: PedidoRepository?) {
		if (repository == null) return

		view?.mostrarCarregando()

		if (pedidos.isNotEmpty()) {
			processarTela(pedidos)
			return
		}

		doAsync {
			pedidos = repository.obtemPedidos() as MutableList<Pedido>
			uiThread {
				processarTela(pedidos)
			}
		}
	}

	fun obtemPedidosLiveData(): LiveData<List<Pedido>>? {
		return obterRepository()?.obtemPedidosLiveData()
	}
}