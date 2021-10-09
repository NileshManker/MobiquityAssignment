package com.nm.mobiquityassignment.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.nm.mobiquityassignment.R
import com.nm.mobiquityassignment.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val btnOpenGallery = view.findViewById<Button>(R.id.btn_openGallery)
        btnOpenGallery.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_photoDetailFragment)
        }
    }
}