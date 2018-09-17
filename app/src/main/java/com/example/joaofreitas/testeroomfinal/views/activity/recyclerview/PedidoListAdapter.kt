package com.example.joaofreitas.testeroomfinal.views.activity.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.maximasistemas.arch.mvp.view.adapters.RecyclerViewAdapter
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.pedido_item.view.*

class PedidoListAdapter(var onClick: (Pedido) -> Unit = {}) : RecyclerViewAdapter<Pedido, PedidoListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pedido_item, parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bindView(lista[position] as Pedido)
		holder.itemView.setOnClickListener { onClick(lista[position] as Pedido)}
	}
//
//	fun add(products: List<Pedido>) {
//		this.lista.addAll(products)
//		notifyItemRangeInserted(0, products.size)
//	}

	fun alteraTodosPedidos(pedidos: List<Pedido>) {
		this.lista.clear()
		this.lista.addAll(pedidos)
		notifyDataSetChanged()
	}

	inner class ViewHolder(itemView: View) : RecyclerViewAdapter.ViewHolder<Pedido>(itemView) {
		override fun bindView(item: Pedido) {
			with(itemView){
				pedido_item_title.text = item.nome
				pedido_item_total.text = item.total.toString()
			}
		}
	}
}
