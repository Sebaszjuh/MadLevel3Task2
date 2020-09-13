package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { onPortalClick(it) }

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
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.titleText, it.urlText)
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalsFragment", "Request triggered, but empty reminder text!")
        }
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager =
            StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvPortals.adapter = portalAdapter
    }

    private fun onPortalClick(portal: Portal) {
        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build();

        if(portal.urlText.startsWith("http"))
        customTabsIntent.launchUrl(
            requireContext(),
            Uri.parse(portal.urlText)
        ) else {
            Toast.makeText(context, portal.urlText + " is invalid", Toast.LENGTH_SHORT).show()

        }

    }
}