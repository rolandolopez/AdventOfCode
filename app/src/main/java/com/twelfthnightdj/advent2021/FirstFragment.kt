package com.twelfthnightdj.advent2021

import advent2022.day01.Y22D01
import advent2022.day02.Y22D02
import advent2022.day03.Y22D03
import advent2022.day04.Y22D04
import advent2022.day05.Y22D05
import advent2022.day06.Y22D06
import advent2022.day07.Y22D07
import advent2022.day08.Y22D08
import advent2022.day09.Y22D09
import advent2022.day10.Y22D10
import advent2022.day11.Y22D11
import advent2022.day13.Y22D13
import advent2022.day15.Y22D15
import advent2022.day18.Y22D18
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import com.twelfthnightdj.advent2021.day10.Day10
import com.twelfthnightdj.advent2021.day11.Day11
import com.twelfthnightdj.advent2021.day12.Day12
import com.twelfthnightdj.advent2021.day13.Day13
import com.twelfthnightdj.advent2021.day14.Day14
import com.twelfthnightdj.advent2021.day15.Day15
import com.twelfthnightdj.advent2021.day16.Day16
import com.twelfthnightdj.advent2021.day17.Day17
import com.twelfthnightdj.advent2021.day18.Day18
import com.twelfthnightdj.advent2021.day20.Day20
import com.twelfthnightdj.advent2021.day21.Day21
import com.twelfthnightdj.advent2021.day22.Day22
import java.lang.Integer.min
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null

    private var daySelected = 0
    private var yearSelected = 22

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        val year = binding.yearSpinner
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.years,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            year.adapter = adapter
            year.onItemSelectedListener = this
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val day: AocDays? = when (yearSelected) {
                21 -> y2021()
                22 -> y2022()
                else -> null
            }
            day?.let { runDay(it) } ?: Toast.makeText(
                requireContext(),
                "You haven't made this yet",
                Toast.LENGTH_LONG
            ).show()
        }
        binding.daySpinner.setSelection(min(Calendar.getInstance().get(DAY_OF_MONTH), 25), true)
    }

    private fun y2021() =
        when (daySelected) {
            1 -> Day1()
            2 -> Day2()
            3 -> Day3()
            4 -> Day4()
            5 -> Day5()
            6 -> Day6()
            7 -> Day7()
            8 -> Day8()
            9 -> Day9()
            10 -> Day10()
            11 -> Day11()
            12 -> Day12()
            13 -> Day13()
            14 -> Day14()
            15 -> Day15()
            16 -> Day16()
            17 -> Day17()
            18 -> Day18()
            20 -> Day20()
            21 -> Day21()
            22 -> Day22()
            else -> null

        }

    private fun y2022() =
        when (daySelected) {
            1 -> Y22D01()
            2 -> Y22D02()
            3 -> Y22D03()
            4 -> Y22D04()
            5 -> Y22D05()
            6 -> Y22D06()
            7 -> Y22D07()
            8 -> Y22D08()
            9 -> Y22D09()
            10 -> Y22D10()
            11 -> Y22D11()
            13 -> Y22D13()
            15 -> Y22D15()
            18 -> Y22D18()
            else -> null

        }


    @SuppressLint("SetTextI18n")
    private fun runDay(day: AocDays) {
        day.yearId = yearSelected
        day.useTrialInput = binding.swTrialInput.isChecked
        day.setup()
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
        when (parent?.id) {
            binding.daySpinner.id -> daySelected = pos
            binding.yearSpinner.id -> yearSelected = year(pos)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    private fun year(pos: Int) =
        when (pos) {
            0 -> 22
            1 -> 21
            else -> 22
        }
}
