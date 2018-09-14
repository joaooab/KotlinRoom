package com.example.joaofreitas.testeroomfinal.views.activity.ui


import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import br.com.maximasistemas.arch.mvp.view.MvpListaView
import br.com.maximasistemas.arch.mvp.view.activities.MvpListaActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.dao.PedidoDao
import com.example.joaofreitas.testeroomfinal.database.AppDatabase
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.model.Pedido
import com.example.joaofreitas.testeroomfinal.presenter.ListPresenter
import com.example.joaofreitas.testeroomfinal.repository.PedidoRepository
import com.example.joaofreitas.testeroomfinal.utilities.InjectorUtil
import com.example.joaofreitas.testeroomfinal.views.activity.recyclerview.PedidoListAdapter
import kotlinx.android.synthetic.main.activity_list_pedido.*

class ListPedidoActivity : MvpListaActivity<ListPedidoView, ListPresenter>() {
	override fun obterClassePresenter(): Class<ListPresenter> = ListPresenter::class.java
	//TODO aplicar injeção de depdência conforme projeto sunflower
	private lateinit var pedidoDao: PedidoDao
	private val pedidoRepository = InjectorUtil.getItemRepository()

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(R.layout.activity_list_pedido)
		title = TITLE_APPBAR
		val database: AppDatabase = Database.getInstance(this)
		pedidoDao = database.pedidoDao()
		configuraRecyclerView()
		configureLiveData()
		configuraBotaoFazerPedido()
		adapter = PedidoListAdapter()
	}

	private fun configureLiveData() {
		val pedidosLiveData = pedidoDao.all()
		pedidosLiveData.observe(this, Observer { pedidos ->
			pedidos?.let {
				adapter.alteraTodosPedidos(it)
			}
		})
	}

	private fun configuraRecyclerView() {
		this.adapter = PedidoListAdapter() {
			vaiParaDetalhesPedido(it)
		}
		list_pedido_recycler_view.adapter = adapter
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

}
