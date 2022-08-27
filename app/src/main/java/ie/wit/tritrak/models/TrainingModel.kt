package ie.wit.tritrak.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrainingModel(var id: Long = 0,
                         val trainingmethod: String = "N/A",
                         val trainamount: Int = 0) : Parcelable