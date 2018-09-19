package com.example.joaofreitas.testeroomfinal.ui.pedido.list

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import br.com.maximasistemas.arch.mvp.view.activities.MvpListaActivity
import br.com.maximasistemas.arch.mvp.view.adapters.RecyclerViewAdapter
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoRepository
import com.example.joaofreitas.testeroomfinal.data.remoto.RetrofitInitializer
import com.example.joaofreitas.testeroomfinal.ui.pedido.detalhes.DetalhesPedidoActivity
import com.example.joaofreitas.testeroomfinal.ui.pedido.formulario.FormularioPedidoActivity
import kotlinx.android.synthetic.main.activity_list_pedido.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPedidoActivity : MvpListaActivity<ListPedidoView, ListPedidoPresenter>(), ListPedidoView {

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	private val pedidoRepository: PedidoRepository by inject()

	override fun obterClassePresenter(): Class<ListPedidoPresenter> = ListPedidoPresenter::class.java

	override fun obterRepository(): PedidoRepository = pedidoRepository

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list_pedido)
		title = TITLE_APPBAR

//		retrofitInitilaizer()

		adapter = configureAdapter()
		configuraBotaoFazerPedido()
	}

	//	TODO transferir configuração liveData para MVP
	private fun configureAdapter(): RecyclerViewAdapter<*, *> {
		val listPedidoAdapter = ListPedidoAdapter { pedido -> DetalhesPedidoActivity.startActivity(pedido, this) }
		return configureLiveData(listPedidoAdapter)
	}

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
			val abreFormularioPedido = Intent(this, FormularioPedidoActivity::class.java)
			startActivity(abreFormularioPedido)
		}
	}


	private fun retrofitInitilaizer() {
		val call = RetrofitInitializer().retrofitService().list()
		call.enqueue(object : Callback<List<*>?> {
			override fun onFailure(call: Call<List<*>?>?, t: Throwable?) {
				Log.e("onFailure error", t?.message)
			}

			override fun onResponse(call: Call<List<*>?>?, response: Response<List<*>?>?) {
				response?.body()?.let {
					val teste: List<*> = it
				}
			}
		})
	}
}

