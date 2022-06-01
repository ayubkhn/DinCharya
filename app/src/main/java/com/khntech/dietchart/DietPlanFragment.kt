package com.khntech.dietchart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.khntech.dietchart.databinding.FragmentDietPlanBinding

class DietPlanFragment : Fragment() {

    lateinit var binding: FragmentDietPlanBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentDietPlanBinding.inflate(layoutInflater)

        bind.getDietPlanBtn.setOnClickListener {
            val intent = Intent(this@DietPlanFragment.requireContext(), NatureActivity::class.java)
            startActivity(intent)
        }
        return bind.root

    }


}
