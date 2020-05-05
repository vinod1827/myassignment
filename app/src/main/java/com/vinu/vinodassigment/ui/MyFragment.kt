package com.vinu.vinodassigment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.vinu.vinodassigment.ui.adapters.ResponseRecyclerViewAdapter
import com.vinu.vinodassigment.R
import com.vinu.vinodassigment.models.ResponseDataModel
import com.vinu.vinodassigment.models.ResponseModel
import com.vinu.vinodassigment.viewmodels.ResponseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class MyFragment : Fragment() {

    private lateinit var responseViewModel: ResponseViewModel
    private val list = ArrayList<ResponseModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        activity?.application?.let {
            responseViewModel =
                ViewModelProvider(this).get(ResponseViewModel::class.java)
        }
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initializeSwipeRefreshLayout()

        initializeNewsRecyclerView()

        observeResponseData()

        if (savedInstanceState == null) {
            fetchFeeds()
        }
    }


    private fun observeResponseData() {
        responseViewModel.responseModel.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            populateData(it)
        })

    }

    private fun initializeSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            if (swipeRefreshLayout.isRefreshing) {
                fetchFeeds()
            }
        }
    }

    private fun initializeNewsRecyclerView() {
        responseRecyclerView.adapter = ResponseRecyclerViewAdapter(list)
        responseRecyclerView.layoutManager = GridLayoutManager(activity, 2)
    }


    private fun enableNewsFeedView(isEnabled: Boolean) {
        responseRecyclerView.visibility = if (isEnabled) View.VISIBLE else View.GONE
        listEmptyTextView.visibility = if (!isEnabled) View.VISIBLE else View.GONE
    }

    private fun fetchFeeds() {
        swipeRefreshLayout.isRefreshing = true
        responseViewModel.getFeed()
    }

    private fun populateData(it: ResponseDataModel) {
        if (it.feed?.entry?.size ?: 0 > 0) {
            list.clear()
            list.addAll(it.feed?.entry!!)
            responseRecyclerView.adapter?.notifyDataSetChanged()
            enableNewsFeedView(true)
        }
        if (list.isEmpty()) {
            enableNewsFeedView(false)
        }
    }
}