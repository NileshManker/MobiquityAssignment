package com.nm.mobiquityassignment.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nm.mobiquityassignment.R
import com.nm.mobiquityassignment.utils.Constants.Companion.IS_LOGGED_IN
import com.nm.mobiquityassignment.utils.Constants.Companion.IS_SESSION_VALID
import com.nm.mobiquityassignment.utils.SharedPreferencesUtils

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            context?.let {
                val sharedPreferencesUtils = SharedPreferencesUtils(activity as Context)
                if (!sharedPreferencesUtils.getBool(IS_LOGGED_IN) && !sharedPreferencesUtils.getBool(IS_SESSION_VALID)){
                    performAction(R.id.action_splashFragment_to_loginFragment)
                } else {
                    performAction(R.id.action_splashFragment_to_homeFragment)
                }
            }
        }, 1)
    }

    private fun performAction(actionId : Int){
        findNavController().navigate(actionId,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment,
                    true).build())
    }
}