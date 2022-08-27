package ie.wit.tritrak.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.tritrak.R
import ie.wit.tritrak.adapters.TrainingAdapter
import ie.wit.tritrak.databinding.ActivityTrainingListBinding
import ie.wit.tritrak.main.TriTraKApp

class TrainingList : AppCompatActivity() {

    lateinit var app: TriTraKApp
    lateinit var trainiglistLayout: ActivityTrainingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trainiglistLayout = ActivityTrainingListBinding.inflate(layoutInflater)
        setContentView(trainiglistLayout.root)

        app = this.application as TriTraKApp
        trainiglistLayout.recyclerView.layoutManager=LinearLayoutManager(this)
        trainiglistLayout.recyclerView.adapter = TrainingAdapter(app.trainingStore.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_traininglist, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_train -> { startActivity(Intent(this, Train::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}