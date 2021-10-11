package com.nm.mobiquityassignment.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.nm.mobiquityassignment.R
import com.nm.mobiquityassignment.adapter.GalleryItemAdapter
import com.nm.mobiquityassignment.databinding.GalleryFragmentBinding
import com.nm.mobiquityassignment.databinding.LoginFragmentBinding
import com.nm.mobiquityassignment.utils.Constants
import com.nm.mobiquityassignment.utils.Constants.Companion.USER_NAME
import com.nm.mobiquityassignment.utils.Constants.Companion.USER_PASSWORD
import com.nm.mobiquityassignment.utils.SharedPreferencesUtils
import com.nm.mobiquityassignment.viewmodel.GalleryViewModel
import com.nm.mobiquityassignment.viewmodel.LoginViewModel

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private var binding: LoginFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding?.login?.setOnClickListener(this)
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            handleProgressBarVisibility(false)
            when (it){
                USER_NAME -> {binding?.userNameLayout?.error = "User name" }
                USER_PASSWORD -> {binding?.userPasswordLayout?.error = "User password"
                    binding?.userNameLayout?.error = null}
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            handleProgressBarVisibility(false)
        })

        viewModel.isLoginSuccess.observe(viewLifecycleOwner, {
            handleProgressBarVisibility(false)
            if (it){
                binding?.userPasswordLayout?.error = null
                val sharedPreferencesUtils = SharedPreferencesUtils(activity as Context)
                sharedPreferencesUtils.setBool(Constants.IS_LOGGED_IN,true)
                sharedPreferencesUtils.setBool(Constants.IS_SESSION_VALID,true)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment,
                            true).build())
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login -> {
                handleProgressBarVisibility(true)
                viewModel.login(binding?.username?.text.toString(), binding?.password?.text.toString())
            }
        }
    }

    private fun handleProgressBarVisibility(isVisible : Boolean){
        binding?.loading?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}