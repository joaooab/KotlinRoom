package com.example.joaofreitas.testeroom.activity.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joaofreitas.testeroom.R
import com.example.joaofreitas.testeroom.model.Item
import kotlinx.android.synthetic.main.item.view.*

class ItemListAdapter(private val itens: List<Item>,
                      private val context: Context) : Adapter<ItemListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view: View = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return itens.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = itens[position]
		holder.bindView(item)
	}

	fun alteraItens(itens: List<Item>) {
//		this.itens.clear()
//		this.itens.addAll(itens)
//		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val title = itemView.item_title
		val valor = itemView.item_valor

		fun bindView(item: Item) {
			this.title.text = item.nome
			this.valor.text = item.valor.toString()
		}
	}
}