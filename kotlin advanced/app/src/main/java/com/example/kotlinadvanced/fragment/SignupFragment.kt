package com.example.kotlinadvanced.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinadvanced.R
import com.example.kotlinadvanced.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }



}