package com.example.madlevel3task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val ARG_TITLE_INPUT = "arg_title_input"
const val ARG_URL_INPUT = "arg_url_input"

class AddPortalFragment : Fragment() {

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

            val args = Bundle()
            args.putString(ARG_TITLE_INPUT, titleInput)
            args.putString(ARG_URL_INPUT, urlInput)

            findNavController().navigate(R.id.action_addPortalFragment_to_portalsFragment, args)

        } else {
            Toast.makeText(
                activity,
                R.string.not_valid_portal, Toast.LENGTH_SHORT
            ).show()
        }
    }
}