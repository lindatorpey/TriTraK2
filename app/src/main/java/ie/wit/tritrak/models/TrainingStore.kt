package ie.wit.tritrak.models

interface TrainingStore {
    fun findAll() : List<TrainingModel>
    fun findById(id: Long) : TrainingModel?
    fun create(traininglog: TrainingModel)
}