package com.example.gatepreviousyear

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.gatepreviousyear.databinding.FragmentQuestionPaperBinding
import com.example.gatepreviousyear.databinding.FragmentResultBinding
import com.example.gatepreviousyear.databinding.FragmentYearListBinding


class Result : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentResultBinding  = DataBindingUtil.inflate(inflater,R.layout.fragment_result,container,false)
        return binding.root
    }


}