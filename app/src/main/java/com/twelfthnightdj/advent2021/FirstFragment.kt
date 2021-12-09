package com.twelfthnightdj.advent2021

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.twelfthnightdj.advent2021.databinding.FragmentFirstBinding
import com.twelfthnightdj.advent2021.day01.Day1
import com.twelfthnightdj.advent2021.day02.Day2
import com.twelfthnightdj.advent2021.day03.Day3
import com.twelfthnightdj.advent2021.day04.Day4
import com.twelfthnightdj.advent2021.day05.Day5
import com.twelfthnightdj.advent2021.day06.Day6
import com.twelfthnightdj.advent2021.day07.Day7
import com.twelfthnightdj.advent2021.day08.Day8
import com.twelfthnightdj.advent2021.day09.Day9
import java.lang.Integer.min
import java.util.*
import java.util.Calendar.DAY_OF_MONTH
import kotlin.math.max

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
                3 -> runDay(Day3())
                4 -> runDay(Day4())
                5 -> runDay(Day5())
                6 -> runDay(Day6())
                7 -> runDay(Day7())
                8 -> runDay(Day8())
                9 -> runDay(Day9())

                else -> {}
            }
        }
        binding.daySpinner.setSelection(min(Calendar.getInstance().get(DAY_OF_MONTH), 25), true)
    }

    @SuppressLint("SetTextI18n")
    private fun runDay(day: AocDays) {
        val partA = day.partA()
        day.reset()
        val partB = day.partB()
        println("Part A: $partA")
        println("Part B: $partB")
        binding.tvAnswerA.text = "Part A: $partA"
        binding.tvAnswerB.text = "Part B: $partB"
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
