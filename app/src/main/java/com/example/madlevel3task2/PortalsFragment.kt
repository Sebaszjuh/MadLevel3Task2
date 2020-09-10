package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_add_portal.*
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
//        setFragmentResultListener(ARG_TITLE_INPUT) { key, bundle ->
//            bundle.getString(ARG_URL_INPUT)?.let {
//                val portal = Portal(it, ARG_TITLE_INPUT)
//
//                portals.add(portal)
//                portalAdapter.notifyDataSetChanged()
//            } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!")
//
//        }

        val titleInput = arguments?.getString(ARG_TITLE_INPUT)
        val urlInput = arguments?.getString(ARG_URL_INPUT)
        if(!titleInput.isNullOrBlank()){
            val portal = Portal(titleInput.toString(), urlInput.toString())
            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        } else{
            Log.e("ReminderFragment", "Request triggered, but empty reminder text!")
        }

    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager =
            StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvPortals.adapter = portalAdapter
        rvPortals.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        //adds reminder to view
//        createItemTouchHelper().attachToRecyclerView(rvPortals)

    }
}