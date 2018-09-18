package com.example.joaofreitas.testeroomfinal.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.joaofreitas.testeroomfinal.data.repository.item.ItemDao
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.PedidoDao
import com.example.joaofreitas.testeroomfinal.data.repository.item.Item
import com.example.joaofreitas.testeroomfinal.data.repository.pedido.Pedido

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
			if (::database.isInitialized) return database
			database = Room.databaseBuilder(
					context,
					AppDatabase::class.java,
					"teste_room")
					.fallbackToDestructiveMigration()
//					.addMigrations(Migration1To2())
					.build()
			return database
		}
	}
}


