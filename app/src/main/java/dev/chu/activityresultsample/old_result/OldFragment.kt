package dev.chu.activityresultsample.old_result

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import dev.chu.activityresultsample.*

class OldFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_old, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btShowSecondActivity).setOnClickListener {
            startSecondView()
        }

        view.findViewById<Button>(R.id.btRequestLocationPermission).setOnClickListener {
            requestLocation(request_location_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == request_second_code) {
            if(resultCode == Activity.RESULT_OK && data != null) {
                toast(data.extras?.toString.orEmpty())
            } else {
                toast("RESULT_CANCELED")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == request_location_code) {
            if(grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                toast("Permission granted")
            } else {
                toast("Permission denied or canceled")
            }
        }
    }

    private fun startSecondView() {
        startActivityForResult(Intent(requireContext(), ResultActivity::class.java), request_second_code)
    }

    companion object {
        private const val request_second_code = 100
        private const val request_location_code = 101
    }
}