package com.example.joaofreitas.testeroom.activity.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.joaofreitas.testeroom.R
import com.example.joaofreitas.testeroom.activity.adapter.PedidoListAdapter
import com.example.joaofreitas.testeroom.dao.PedidoDao
import com.example.joaofreitas.testeroom.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var pedidoDao: PedidoDao

	companion object {
		const val TITLE_APPBAR = "Pedido"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		title = TITLE_APPBAR

		val database: AppDatabase = AppDatabase.getInstance(this)
		pedidoDao = database.pedidoDao()

		configuraRecyclerView()
		configuraBotaoAdicionarPedido()
	}

	private fun configuraBotaoAdicionarPedido() {
		main_fazer_pedido.setOnClickListener {
			val abreFormularioPedido = Intent(this, FormularioPedidoActivity::class.java)
			startActivity(abreFormularioPedido)
		}
	}

	private fun configuraRecyclerView() {
		val recyclerView: RecyclerView = main_recycler_view
		recyclerView.adapter = PedidoListAdapter(pedidoDao.all, this)
	}

	override fun onResume() {
		super.onResume()
//		val pedidos: List<Pedido> = listOf<Pedido>(
//				Pedido(1, "Pedido1"),
//				Pedido(2, "Pedido2"))
//		pedidoDao.add(pedidos)
//
//		val items: List<Item> = listOf<Item>(
//				Item(1, "Item1", 10F, pedidos.get(1).id),
//				Item(2, "Item2", 20F, pedidos.get(1).id))
//		itemDao.add(items)
	}
}
