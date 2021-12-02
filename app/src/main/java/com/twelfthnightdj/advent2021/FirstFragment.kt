package com.twelfthnightdj.advent2021

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.twelfthnightdj.advent2021.databinding.FragmentFirstBinding
import java.time.Instant
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null

    private var daySelected = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val spinner = binding.daySpinner
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.days,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            when(daySelected) {
                1 -> runDay(Day1())
                2 -> runDay(Day2())

                else -> {}
            }
        }
        binding.daySpinner.setSelection(Calendar.getInstance().get(DAY_OF_MONTH), true)
    }

    private fun runDay(day: AocDays) {
        binding.tvAnswerA.text = day.partA()
        binding.tvAnswerB.text = day.partB()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        daySelected = pos
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit
}
