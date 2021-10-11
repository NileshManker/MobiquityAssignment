package com.nm.mobiquityassignment.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nm.mobiquityassignment.adapter.GalleryItemAdapter
import com.nm.mobiquityassignment.adapter.OnBottomReachedListener
import com.nm.mobiquityassignment.databinding.GalleryFragmentBinding
import com.nm.mobiquityassignment.db.database.GalleryDatabase
import com.nm.mobiquityassignment.network.NetworkApi
import com.nm.mobiquityassignment.repositories.GalleryRepository
import com.nm.mobiquityassignment.viewmodel.AppViewModelFactory
import com.nm.mobiquityassignment.viewmodel.GalleryViewModel

class GalleryFragment : Fragment(), OnBottomReachedListener{

    private lateinit var viewModel: GalleryViewModel
    private lateinit var adapter : GalleryItemAdapter
    private var binding: GalleryFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GalleryFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GalleryItemAdapter(this)
        binding?.recyclerview?.adapter = adapter
        getGalleryData();
    }

    private fun getGalleryData() {
        val galleryDatabase = GalleryDatabase(requireActivity().applicationContext)
        val networkApi = NetworkApi.getInstance()
        val galleryRepository = GalleryRepository(networkApi, galleryDatabase)
        viewModel = AppViewModelFactory(galleryRepository).create(GalleryViewModel::class.java)

        viewModel.galleryItems.observe(viewLifecycleOwner, {
            Log.d("Test Response", it.size.toString())
            adapter.setGalleries(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Log.d("Test Response", "Error")
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.isDataStored.observe(viewLifecycleOwner, {
            if(it){
                viewModel.getGalleryItemsPerPage()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding?.progressDialog?.visibility = View.VISIBLE
            } else {
                binding?.progressDialog?.visibility = View.GONE
            }
        })

        viewModel.fetchGalleryItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onBottomReached() {
        viewModel.getGalleryItemsPerPage()
    }
}