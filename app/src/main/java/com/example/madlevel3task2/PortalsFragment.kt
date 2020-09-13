package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.*
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
//        val titleInput = arguments?.getString(ARG_TITLE_INPUT)
//        val urlInput = arguments?.getString(ARG_URL_INPUT)
//        if (!titleInput.isNullOrBlank()) {
//            val portal = Portal(titleInput.toString(), urlInput.toString())

        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.titleText, it.urlText)
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            }?: Log.e("PortalsFragment", "Request triggered, but empty reminder text!")

        }
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager =
            StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvPortals.adapter = portalAdapter

        //adds reminder to view
//        createItemTouchHelper().attachToRecyclerView(rvPortals)

    }
}