package com.example.joaofreitas.testeroom.activity.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joaofreitas.testeroom.R
import com.example.joaofreitas.testeroom.model.Pedido
import kotlinx.android.synthetic.main.pedido_item.view.*

class PedidoListAdapter(private val pedidos: List<Pedido>,
                        private val context: Context) : Adapter<PedidoListAdapter.ViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View = LayoutInflater.from(context).inflate(R.layout.pedido_item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return pedidos.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val pedido: Pedido = pedidos[position]
		holder.bindView(pedido)
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val title = itemView.pedido_item_title
		val total = itemView.pedido_item_total

		fun bindView(pedido: Pedido) {
			this.title.text = pedido.nome
			this.total.text = pedido.total.toString()
		}
	}
}
