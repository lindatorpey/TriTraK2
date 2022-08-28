package ie.wit.tritrak.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ie.wit.tritrak.R
import ie.wit.tritrak.databinding.FragmentTrainBinding
import ie.wit.tritrak.main.TriTraKApp
import ie.wit.tritrak.models.TrainingModel


class TrainFragment : Fragment() {

    lateinit var app: TriTraKApp
    var totalTraining= 0
    private var _fragBinding: FragmentTrainBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as TriTraKApp
        setHasOptionsMenu(true)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentTrainBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_train)

        fragBinding.progressBar.max = 1000000
        fragBinding.minutePicker.minValue = 1
        fragBinding.minutePicker.maxValue = 60

        fragBinding.minutePicker.setOnValueChangedListener { _, _, newVal ->
            fragBinding.trainAmount.setText("$newVal")
        }
        setButtonListener(fragBinding)
        return root;


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TrainFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    fun setButtonListener(layout: FragmentTrainBinding) {
        layout.trainButton.setOnClickListener {
            val amount = if (layout.trainAmount.text.isNotEmpty())
                layout.trainAmount.text.toString().toInt() else layout.minutePicker.value
            if(totalTraining >= layout.progressBar.max)
                Toast.makeText(context,"You have exceeded your Goal",Toast.LENGTH_LONG).show()
            else {
                val logmethod = if(layout.trainingMethod.checkedRadioButtonId == R.id.Run) "Run"
                else if (layout.trainingMethod.checkedRadioButtonId == R.id.Cycle)"Cycle"
                else "Swim"
                totalTraining += amount
                layout.totalTime.text = "$totalTraining mins"
                layout.progressBar.progress = totalTraining
                app.trainingStore.create(TrainingModel(trainingmethod = logmethod, trainamount = amount))
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
        totalTraining = app.trainingStore.findAll().sumOf { it.trainamount }
        fragBinding.progressBar.progress = totalTraining
        fragBinding.totalTime.text = "$totalTraining mins"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_train, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }


}