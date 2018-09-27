package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.com.maximasistemas.arch.mvp.view.activities.MvpListaActivity
import br.com.maximasistemas.arch.mvp.view.adapters.RecyclerViewAdapter
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.data.remoto.conexao.ConexaoService
import com.example.joaofreitas.testeroomfinal.data.remoto.datamanager.DataManagerService
import com.example.joaofreitas.testeroomfinal.ui.pedido.detalhes.DetalhesPedidoActivity
import com.example.joaofreitas.testeroomfinal.ui.pedido.formulario.FormularioPedidoActivity
import kotlinx.android.synthetic.main.activity_list_pedido.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class ListPedidoActivity : MvpListaActivity<ListPedidoView, ListPedidoPresenter>(), ListPedidoView {

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	private val pedidoRepository: PedidoRepository by inject()

	override fun obterClassePresenter(): Class<ListPedidoPresenter> = ListPedidoPresenter::class.java

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list_pedido)
		title = TITLE_APPBAR

		adapter = configureAdapter()
		configuraBotaoFazerPedido()
	}

	//	TODO transferir configuração liveData para MVP
	private fun configureAdapter(): RecyclerViewAdapter<*, *> {
		val listPedidoAdapter = ListPedidoAdapter { pedido -> DetalhesPedidoActivity.startActivity(pedido, this) }
		return configureLiveData(listPedidoAdapter)
	}

	//	TODO transferir configuração liveData para MVP
	private fun configureLiveData(listPedidoAdapter: ListPedidoAdapter): RecyclerViewAdapter<*, *> {
		val liveData = pedidoRepository.obtemPedidosLiveData()
		liveData.observe(this, Observer { pedidos ->
			pedidos?.let {
				listPedidoAdapter.alteraTodosPedidos(it)
			}
		})
		return listPedidoAdapter
	}

	private fun configuraBotaoFazerPedido() {
		list_pedido_add_pedido.setOnClickListener {
			startActivity<FormularioPedidoActivity>()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val inflater = menuInflater
		inflater.inflate(R.menu.menu_list_pedido, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item?.itemId) {
			R.id.action_web_service -> {
				val conexaoService = ConexaoService()
				conexaoService.login()
				return true
			}
		}
		return super.onOptionsItemSelected(item)
	}

}

