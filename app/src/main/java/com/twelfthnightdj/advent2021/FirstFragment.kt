package com.twelfthnightdj.advent2021

import advent2022.day01.Y22D01
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
            when (yearSelected) {
                21 -> y2021()
                22 -> y2022()
            }
        }
        binding.daySpinner.setSelection(min(Calendar.getInstance().get(DAY_OF_MONTH), 25), true)
    }

    private fun y2021() =
        when (daySelected) {
            1 -> runDay(Day1())
            2 -> runDay(Day2())
            3 -> runDay(Day3())
            4 -> runDay(Day4())
            5 -> runDay(Day5())
            6 -> runDay(Day6())
            7 -> runDay(Day7())
            8 -> runDay(Day8())
            9 -> runDay(Day9())
            10 -> runDay(Day10())
            11 -> runDay(Day11())
            12 -> runDay(Day12())
            13 -> runDay(Day13())
            14 -> runDay(Day14())
            15 -> runDay(Day15())
            16 -> runDay(Day16())
            17 -> runDay(Day17())
            18 -> runDay(Day18())
            20 -> runDay(Day20())
            21 -> runDay(Day21())
            22 -> runDay(Day22())

            else -> {
            }
        }

    private fun y2022() =
        when (daySelected) {
            1 -> runDay(Y22D01())
            2 -> runDay(Day2())
            3 -> runDay(Day3())
            4 -> runDay(Day4())
            5 -> runDay(Day5())
            6 -> runDay(Day6())
            7 -> runDay(Day7())
            8 -> runDay(Day8())
            9 -> runDay(Day9())
            10 -> runDay(Day10())
            11 -> runDay(Day11())
            12 -> runDay(Day12())
            13 -> runDay(Day13())
            14 -> runDay(Day14())
            15 -> runDay(Day15())
            16 -> runDay(Day16())
            17 -> runDay(Day17())
            18 -> runDay(Day18())
            20 -> runDay(Day20())
            21 -> runDay(Day21())
            22 -> runDay(Day22())

            else -> {
            }
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
        when(parent?.id) {
            binding.daySpinner.id -> daySelected = pos
            binding.yearSpinner.id -> yearSelected = year(pos)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    private fun year(pos: Int) =
        when(pos) {
            0 -> 22
            1 -> 21
            else -> 22
        }
}
