package com.example.joaofreitas.testeroomfinal.views.activity.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.views.activity.recyclerview.ItemListAdapter
import com.example.joaofreitas.testeroomfinal.dao.ItemDao
import com.example.joaofreitas.testeroomfinal.database.Database
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.activity_pedido_detalhes.*

class PedidoDetalhesActivity : AppCompatActivity() {

	private lateinit var itemDao: ItemDao
	private lateinit var adapter: ItemListAdapter
	private lateinit var pedido: Pedido

	companion object {
		fun startActivity(pedido: Pedido, context: Context) {
			val intentDetalhesPedido = Intent(context, PedidoDetalhesActivity::class.java)
			intentDetalhesPedido.putExtra(CHAVE_PEDIDO, pedido)
			context.startActivity(intentDetalhesPedido)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_pedido_detalhes)
		val database = Database.getInstance(this)
		itemDao = database.itemDao()
		if (!pedidoFoiCarregado()) finish()
		configuraRecyclerView()
		configuraLiveData()
		configuraBotaoAddItem()
	}

//	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//		return super.onOptionsItemSelected(item)
//	}

	private fun configuraLiveData() {
		val itensLiveData = itemDao.allByPedidoId(pedido.id)
		itensLiveData.observe(this, Observer { itens ->
			itens?.let {
				adapter.alteraTodosItens(it)
			}
		})
	}

	private fun configuraRecyclerView() {
		this.adapter = ItemListAdapter(context = this)
		pedido_detalhes_recycler_view.adapter = adapter
	}

	private fun configuraBotaoAddItem() {
		pedido_detalhes_add_item.setOnClickListener {
			val intentFormularioItem = Intent(this, FormularioItemActivity::class.java)
			intentFormularioItem.putExtra(CHAVE_PEDIDO, pedido)
			startActivity(intentFormularioItem)
		}
	}

	private fun pedidoFoiCarregado(): Boolean {
		intent.getParcelableExtra<Pedido>(CHAVE_PEDIDO)?.let { pedido ->
			this.pedido = pedido
			preencheInformacoesProduto()
			return true
		}
		return false
	}

	private fun preencheInformacoesProduto() {
		pedido_detalhes_id.text = this.pedido.id.toString()
		pedido_detalhes_nome.text = this.pedido.nome
		pedido_detalhes_total.text = this.pedido.total.toString()
	}
}
