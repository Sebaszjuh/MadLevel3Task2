package com.example.madlevel3task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val REQ_REMINDER_KEY = "req_reminder"
const val BUNDLE_REMINDER_KEY = "bundle_reminder"

class AddReminderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val titleInput = textInput.text.toString()
        val urlInput = urlInput.text.toString()

        if (titleInput.isNotBlank() ) {
            //set the data as fragmentResult, we are listening for REQ_REMINDER_KEY in RemindersFragment!
            setFragmentResult(REQ_REMINDER_KEY, bundleOf(Pair(titleInput, urlInput)))

            findNavController().popBackStack()

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_portal, Toast.LENGTH_SHORT
            ).show()
        }
    }
}