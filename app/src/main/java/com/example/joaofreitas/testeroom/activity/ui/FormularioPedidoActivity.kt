package com.example.joaofreitas.testeroom.activity.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.joaofreitas.testeroom.R
import com.example.joaofreitas.testeroom.activity.adapter.ItemListAdapter
import com.example.joaofreitas.testeroom.dao.ItemDao
import com.example.joaofreitas.testeroom.dao.PedidoDao
import com.example.joaofreitas.testeroom.database.AppDatabase
import com.example.joaofreitas.testeroom.model.Item
import com.example.joaofreitas.testeroom.model.Pedido
import kotlinx.android.synthetic.main.activity_formulario_pedido.*

class FormularioPedidoActivity : AppCompatActivity() {

	private lateinit var pedidoDao: PedidoDao
	private lateinit var itemDao: ItemDao
	private lateinit var adapter: ItemListAdapter
	private val itens: MutableList<Item> = mutableListOf()

	companion object {
		const val CHAVE_PEDIDO = "PEDIDO"
		const val CHAVE_ITEM = "ITEM"
		const val TITLE_APPBAR = "Formulario Pedido"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_formulario_pedido)

		title = TITLE_APPBAR

		val database: AppDatabase = AppDatabase.getInstance(this)
		pedidoDao = database.pedidoDao()
		itemDao = database.itemDao()

		configuraBotaSalvar()
		configraRecyclerView()
		configuraBotaoAdicionarItem()
		Log.d("Debugg", "create")
	}

	override fun onResume() {
		super.onResume()

		val extras = intent.extras
		if (extras?.get(CHAVE_ITEM) != null) {
			val item = extras.get(CHAVE_ITEM) as Item
			itens.add(item)
//			adapter.alteraItens(itens)
		}
	}

	private fun configuraBotaoAdicionarItem() {
		formulario_pedido_add_itens.setOnClickListener {
			vaiParaFormularioItem()
		}
	}

	private fun vaiParaFormularioItem() {
		val vaiParaFormularioItem = Intent(this, FormularioItemActivity::class.java)
		startActivity(vaiParaFormularioItem)
	}

	private fun configraRecyclerView() {
		val recyclerView: RecyclerView = formulario_pedido_recycler_view
		recyclerView.adapter = ItemListAdapter(itemDao.all, this)
	}

	private fun configuraBotaSalvar() {
		formulario_pedido_salvar.setOnClickListener {
			val pedido: Pedido = criaPedido()
			vaiParaMain(pedido)
		}
	}

	private fun vaiParaMain(pedido: Pedido) {
		val vaiParaMain = Intent(this, MainActivity::class.java)
		vaiParaMain.putExtra(CHAVE_PEDIDO, pedido)
		startActivity(vaiParaMain)
	}

	private fun criaPedido(): Pedido {
		val titulo: String = formulario_pedido_title.editText?.text.toString()
		val pedido = Pedido(nome = titulo)

		return pedido
	}
}
