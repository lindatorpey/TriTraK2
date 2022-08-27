package ie.wit.tritrak.models

import timber.log.Timber


var lastId =0L

internal fun getId(): Long{
    return lastId++
}

class TrainingMemStore : TrainingStore{
    val trainingLogs = ArrayList<TrainingModel>()

    override fun findAll(): List<TrainingModel> {
        return trainingLogs
    }

    override fun findById(id: Long): TrainingModel? {
        val foundTrainingLog : TrainingModel? = trainingLogs.find { it.id == id }
        return foundTrainingLog
    }

    override fun create(traininglog: TrainingModel) {
        traininglog.id = getId()
        trainingLogs.add(traininglog)
        logAll()
    }
    fun logAll(){
        Timber.v("Trainings List")
        trainingLogs.forEach{Timber.v("Donate ${it}")}
    }


}