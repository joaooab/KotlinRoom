package com.example.joaofreitas.testeroomfinal.views.activity.ui


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import br.com.maximasistemas.arch.mvp.view.MvpListaView
import br.com.maximasistemas.arch.mvp.view.activities.MvpListaActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.model.Pedido
import com.example.joaofreitas.testeroomfinal.presenter.ListPedidoPresenter
import com.example.joaofreitas.testeroomfinal.utilities.InjectorUtil
import com.example.joaofreitas.testeroomfinal.views.activity.recyclerview.PedidoListAdapter
import kotlinx.android.synthetic.main.activity_list_pedido.*

class ListPedidoActivity : MvpListaActivity<ListPedidoView, ListPedidoPresenter>() {

	override fun obterClassePresenter(): Class<ListPedidoPresenter> = ListPedidoPresenter::class.java

	private val pedidoRepository = InjectorUtil.getPedidoRepository(this)

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list_pedido)
		title = TITLE_APPBAR

		parent
		adapter = PedidoListAdapter { pedido -> vaiParaDetalhesPedido(pedido) }
		configureLiveData(adapter as PedidoListAdapter)
		configuraBotaoFazerPedido()
	}


	private fun configureLiveData(adapter: PedidoListAdapter) {
		val pedidosLiveData = pedidoRepository.obtemPedidos()
		pedidosLiveData.observe(this, Observer { pedidos ->
			pedidos?.let {
				adapter.alteraTodosPedidos(it)
			}
		})
	}

	private fun vaiParaDetalhesPedido(pedido: Pedido) {
		val intentDetalhesPedido = Intent(this, PedidoDetalhesActivity::class.java)
		intentDetalhesPedido.putExtra(CHAVE_PEDIDO, pedido)
		startActivity(intentDetalhesPedido)
	}

	private fun configuraBotaoFazerPedido() {
		list_pedido_add_pedido.setOnClickListener {
			val abreFormularioPedido = Intent(this, FormularioPedidoActivity::class.java)
			startActivity(abreFormularioPedido)
		}
	}
}

interface ListPedidoView : MvpListaView {
	fun exibirAlgumaCoisa()
}
