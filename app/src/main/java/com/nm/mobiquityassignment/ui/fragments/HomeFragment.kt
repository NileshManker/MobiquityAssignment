package com.nm.mobiquityassignment.ui.fragments

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarView
import com.nm.mobiquityassignment.R
import com.nm.mobiquityassignment.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    val galleryFragment: Fragment = GalleryFragment()
    val aboutUsFragment: Fragment = AboutUsFragment()
    lateinit var fm : FragmentManager
    var active = galleryFragment
    private var binding: HomeFragmentBinding? = null

    private var bottomNavigationListener = object : NavigationBarView.OnItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.gallery_fragment -> {
                    fm.beginTransaction().hide(active).show(galleryFragment).commit()
                    active = galleryFragment
                    return true
                }
                R.id.about_us_fragment -> {
                    fm.beginTransaction().hide(active).show(aboutUsFragment).commit()
                    active = aboutUsFragment
                    return true
                }
            }
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fm = childFragmentManager

        binding?.homeBottomNavigationBar?.setOnItemSelectedListener(bottomNavigationListener)
        if(!aboutUsFragment.isAdded){
            fm.beginTransaction().add(R.id.main_container, aboutUsFragment, "About Us").hide(aboutUsFragment).commit()
        }
        if (!galleryFragment.isAdded){
            fm.beginTransaction().add(R.id.main_container, galleryFragment, "Gallery").commit()
        }
    }
}