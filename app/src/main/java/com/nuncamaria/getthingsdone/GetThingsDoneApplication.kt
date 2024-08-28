package com.nuncamaria.getthingsdone

import android.app.Application
import com.nuncamaria.getthingsdone.di.AppDataDi
import com.nuncamaria.getthingsdone.di.AppDi

class GetThingsDoneApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppDi

    override fun onCreate() {
        super.onCreate()
        container = AppDataDi(this)
    }
}