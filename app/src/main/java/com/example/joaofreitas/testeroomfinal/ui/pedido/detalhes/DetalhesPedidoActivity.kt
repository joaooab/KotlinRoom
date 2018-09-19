package com.example.joaofreitas.testeroomfinal.ui.pedido.detalhes

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.data.local.repository.item.ItemRepository
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido
import com.example.joaofreitas.testeroomfinal.ui.item.formulario.FormularioItemActivity
import com.example.joaofreitas.testeroomfinal.ui.item.list.ItemListAdapter
import com.example.joaofreitas.testeroomfinal.ui.pedido.CHAVE_PEDIDO
import kotlinx.android.synthetic.main.activity_pedido_detalhes.*
import org.koin.android.ext.android.inject

class DetalhesPedidoActivity : AppCompatActivity() {

	private lateinit var adapter: ItemListAdapter
	private lateinit var pedido: Pedido
	private val itemRepository: ItemRepository by inject()

	companion object {
		fun startActivity(pedido: Pedido, context: Context) {
			val intentDetalhesPedido = Intent(context, DetalhesPedidoActivity::class.java)
			intentDetalhesPedido.putExtra(CHAVE_PEDIDO, pedido)
			context.startActivity(intentDetalhesPedido)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_pedido_detalhes)

		if (!pedidoFoiCarregado()) finish()
		configuraRecyclerView()
		configuraLiveData()
		configuraBotaoAddItem()
	}

	private fun configuraLiveData() {
		val itensLiveData = itemRepository.obtemItensLiveDataPorPedidoId(pedido.id)
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
