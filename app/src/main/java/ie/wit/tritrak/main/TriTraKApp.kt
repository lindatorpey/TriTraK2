package ie.wit.tritrak.main

import android.app.Application
import ie.wit.tritrak.models.TrainingMemStore
import ie.wit.tritrak.models.TrainingStore
import timber.log.Timber

class TriTraKApp : Application(){

    lateinit var trainingStore: TrainingStore
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        trainingStore = TrainingMemStore()
        Timber.i("TriTraK App Started")
    }
}