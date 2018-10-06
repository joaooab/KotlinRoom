package com.example.joaofreitas.testeroomfinal.activity.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joaofreitas.testeroomfinal.R
import com.example.joaofreitas.testeroomfinal.model.Item
import com.example.joaofreitas.testeroomfinal.model.Pedido
import kotlinx.android.synthetic.main.item.view.*

class ItemListAdapter(private val itens: MutableList<Item> = mutableListOf(),
                      private val context: Context) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
		val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itens.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = itens[position]
		holder.bind(item )
	}

	fun add(itens: List<Item>) {
		this.itens.addAll(itens)
		notifyItemRangeInserted(0, itens.size)
	}

	fun alteraTodosItens(itens: List<Item>) {
		this.itens.clear()
		this.itens.addAll(itens)
		notifyDataSetChanged()
	}

	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val nome = itemView.item_title
		private val valor = itemView.item_valor
		private lateinit var item: Item

		fun bind(item: Item) {
			this.item = item
			this.nome.text = item.nome
			this.valor.text = item.valor.toString()
		}
	}
}