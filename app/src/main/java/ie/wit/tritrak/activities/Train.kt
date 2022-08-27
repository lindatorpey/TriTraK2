package ie.wit.tritrak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.tritrak.R
import ie.wit.tritrak.adapters.TrainingAdapter
import ie.wit.tritrak.databinding.ActivityTrainBinding
import ie.wit.tritrak.main.TriTraKApp
import ie.wit.tritrak.models.TrainingModel
import timber.log.Timber

class Train : AppCompatActivity() {

    private lateinit var trainLayout: ActivityTrainBinding
    lateinit var app:TriTraKApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trainLayout = ActivityTrainBinding.inflate(layoutInflater)
        setContentView(trainLayout.root)

        app = this.application as TriTraKApp

        trainLayout.progressBar.max = 1000000
        trainLayout.minutePicker.minValue = 1
        trainLayout.minutePicker.maxValue = 3200

        trainLayout.minutePicker.setOnValueChangedListener{ _, _, newVal ->
            //Display the newly selected number to paymentAmount
            trainLayout.trainAmount.setText("$newVal")
    }

        var totalTraining = 0

        trainLayout.trainButton.setOnClickListener{
            val trainAmount = if ( trainLayout.trainAmount.text.isNotEmpty())
                trainLayout.trainAmount.text.toString().toInt()
            else
                trainLayout.minutePicker.value
            if (totalTraining >= trainLayout.progressBar.max)
                Toast.makeText(applicationContext, "You have hit your Target!", Toast.LENGTH_LONG).show()
            else{
                val trainingmethod = if (trainLayout.trainMethod.checkedRadioButtonId == R.id.Run) "Run"
                else if (trainLayout.trainMethod.checkedRadioButtonId == R.id.Cycle)"Cycle"
                else "Swim"
                totalTraining += trainAmount
                trainLayout.totalTime.text = "$totalTraining mins"
                trainLayout.progressBar.progress = totalTraining
                app.trainingStore.create(TrainingModel(trainingmethod = trainingmethod, trainamount = trainAmount))

                Timber.i("Total time trained so far $totalTraining")
            }
        }



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_train, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_list -> {startActivity(Intent(this, TrainingList::class.java))
            true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}