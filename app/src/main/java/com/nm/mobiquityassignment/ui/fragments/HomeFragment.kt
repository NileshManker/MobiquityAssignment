package com.nm.mobiquityassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.nm.mobiquityassignment.R


class HomeFragment : Fragment() {

    val galleryFragment: Fragment = GalleryFragment()
    val aboutUsFragment: Fragment = AboutUsFragment()
    lateinit var fm : FragmentManager
    var active = galleryFragment

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
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fm = childFragmentManager

        val navigation = view.findViewById(R.id.home_bottom_navigation_bar) as BottomNavigationView
        navigation.setOnItemSelectedListener(bottomNavigationListener)

        if(!aboutUsFragment.isAdded){
            fm.beginTransaction().add(R.id.main_container, aboutUsFragment, "About Us").hide(aboutUsFragment).commit()
        }
        if (!galleryFragment.isAdded){
            fm.beginTransaction().add(R.id.main_container, galleryFragment, "Gallery").commit()
        }
    }
}