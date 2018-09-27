package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import br.com.maximasistemas.arch.mvp.presenter.MvpListaPresenter
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ListPedidoPresenter : MvpListaPresenter<ListPedidoView>(), KoinComponent {

	private val pedidoRepository: PedidoRepository by inject()
	var pedidos = mutableListOf<Pedido>()

	override fun iniciar() {
		super.iniciar()
		consultarPedidos()
	}

	fun consultarPedidos() {

		view?.mostrarCarregando()

		if (pedidos.isNotEmpty()) {
			processarTela(pedidos)
			return
		}

		doAsync {
			pedidos = pedidoRepository.obtemPedidos() as MutableList<Pedido>
			uiThread {
				processarTela(pedidos)
			}
		}
	}

//	private fun configureLiveData(listPedidoAdapter: ListPedidoAdapter): RecyclerViewAdapter<*, *> {
//		val liveData = pedidoRepository.obtemPedidosLiveData()
//		liveData.observe(, Observer { pedidos ->
//			pedidos?.let {
//				listPedidoAdapter.alteraTodosPedidos(it)
//			}
//		})
//		return listPedidoAdapter
//	}

}