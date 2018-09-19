package com.example.joaofreitas.testeroomfinal.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.joaofreitas.testeroomfinal.data.local.repository.item.ItemDao
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.PedidoDao
import com.example.joaofreitas.testeroomfinal.data.local.repository.item.Item
import com.example.joaofreitas.testeroomfinal.data.local.repository.pedido.Pedido

@Database(entities = [(Pedido::class), (Item::class)], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
	abstract fun pedidoDao(): PedidoDao
	abstract fun itemDao(): ItemDao

//	companion object {
//		@JvmField
//		val MIGRATION_1_2 = Migration1To2()
//	}
}

object Database {
	@Volatile
	private lateinit var database: AppDatabase

	fun getInstance(context: Context): AppDatabase {
		synchronized(this) {
			if (com.example.joaofreitas.testeroomfinal.data.local.Database::database.isInitialized) return database
			database = Room.databaseBuilder(
					context,
					AppDatabase::class.java,
					"teste_room")
					.fallbackToDestructiveMigration()
//					.addMigrations(Migration1To2())
					.allowMainThreadQueries()
					.build()
			return database
		}
	}
}


