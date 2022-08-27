package ie.wit.tritrak.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.tritrak.R
import ie.wit.tritrak.databinding.TrainItemBinding
import ie.wit.tritrak.models.TrainingModel



class TrainingAdapter constructor(private var trainings: List<TrainingModel>)
    : RecyclerView.Adapter<TrainingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = TrainItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val training = trainings[holder.adapterPosition]
        holder.bind(training)
    }

    override fun getItemCount(): Int = trainings.size

    inner class MainHolder(val binding : TrainItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(training: TrainingModel) {
            binding.trainAmount.text = training.trainamount.toString()
            binding.trainMethod.text = training.trainingmethod
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
        }
    }
}