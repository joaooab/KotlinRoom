package com.example.joaofreitas.testeroom.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.joaofreitas.testeroom.dao.ItemDao
import com.example.joaofreitas.testeroom.dao.PedidoDao
import com.example.joaofreitas.testeroom.model.Item
import com.example.joaofreitas.testeroom.model.Pedido

@Database(entities = [(Pedido::class), (Item::class)], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
	abstract fun pedidoDao() : PedidoDao
	abstract fun itemDao() : ItemDao

	companion object {
		private var sInstance: AppDatabase? = null

		@Synchronized
		fun getInstance(context: Context): AppDatabase {
			if (sInstance == null) {
				sInstance = Room
						.databaseBuilder(context.applicationContext, AppDatabase::class.java, "teste_room")
						.fallbackToDestructiveMigration()
						.allowMainThreadQueries()
						.build()
			}
			return sInstance!!
		}
	}
}
