package com.example.joaofreitas.testeroomfinal.activity.ui


import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.activity.recyclerview.PedidoListAdapter
import com.example.joaofreitas.testeroomfinal.dao.PedidoDao
import com.example.joaofreitas.testeroomfinal.database.AppDatabase
import com.example.joaofreitas.testeroomfinal.database.Database
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.activity_list_pedido.*

class ListPedidoActivity : AppCompatActivity() {

	private lateinit var pedidoDao: PedidoDao
	private lateinit var adapter: PedidoListAdapter

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
		this.adapter = PedidoListAdapter(context = this) {
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
