package dev.chu.activityresultsample.new_result

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.activity.result.registerForActivityResult
import androidx.fragment.app.Fragment
import dev.chu.activityresultsample.R
import dev.chu.activityresultsample.ResultActivity
import dev.chu.activityresultsample.toString
import dev.chu.activityresultsample.toast

class NewFragment : Fragment() {
    val requestActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        toast(activityResult.toString)
    }

    val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        toast("Location granted: $isGranted")
    }

    val requestLocation = registerForActivityResult(
        ActivityResultContracts.RequestPermission(), Manifest.permission.ACCESS_FINE_LOCATION
    ) { isGranted ->
        toast("Location granted: $isGranted")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.bt00).setOnClickListener {
            requestActivity.launch(Intent(context, ResultActivity::class.java))
        }
        view.findViewById<Button>(R.id.bt01).setOnClickListener {
            requestLocation.launch()
        }
        view.findViewById<Button>(R.id.bt02).setOnClickListener {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}