package com.vinu.vinodassigment

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinu.vinodassigment.models.ResponseDataModel
import com.vinu.vinodassigment.ui.MyFragment
import com.vinu.vinodassigment.viewmodels.ResponseViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentsTest {

    @Test
    fun testViewModelInitialization() {
        val scenario = launchFragmentInContainer<MyFragment>()
        scenario.onFragment {
            it.activity?.application?.let { app ->
                val newsFragmentViewModel =
                    ViewModelProvider.AndroidViewModelFactory.getInstance(app)
                        .create(ResponseViewModel::class.java)
                newsFragmentViewModel.responseModel.observe(
                    it.viewLifecycleOwner,
                    Observer { responseModel ->
                        it.swipeRefreshLayout.isRefreshing = false
                        populateData(it, responseModel)
                    })
            }
        }
    }

    private fun populateData(
        myFragment: MyFragment,
        responseModel: ResponseDataModel
    ) {
        if (responseModel.feed?.entry?.size ?: 0 > 0) {
            enableNewsFeedView(myFragment, true)
        } else {
            enableNewsFeedView(myFragment, false)
        }
    }

    @Test
    fun testViewsAreEnabled() {
        val isEnabled = true
        val scenario = launchFragmentInContainer<MyFragment>()
        scenario.onFragment {
            enableNewsFeedView(it, isEnabled)
        }
    }

    private fun enableNewsFeedView(
        it: MyFragment,
        isViewEnabled: Boolean
    ) {
        it.responseRecyclerView.visibility = if (isViewEnabled) View.VISIBLE else View.GONE
        it.listEmptyTextView.visibility = if (!isViewEnabled) View.VISIBLE else View.GONE

        assert(it.responseRecyclerView.visibility == View.VISIBLE)
        assert(it.listEmptyTextView.visibility == View.GONE)
    }

}