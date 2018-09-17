package com.example.joaofreitas.testeroomfinal.utilities

import android.app.Application

class AndroidApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		initializeDependencies()
	}

	private fun initializeDependencies() {

	}
}