package com.twelfthnightdj.advent2021

import advent2015.day03.Y15D03
import advent2015.day02.Y15D02
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
import advent2022.day20.Y22D20
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
import com.twelfthnightdj.advent2021.day01.Y21D01
import com.twelfthnightdj.advent2021.day02.Y21D02
import com.twelfthnightdj.advent2021.day03.Y21D03
import com.twelfthnightdj.advent2021.day04.Y21D04
import com.twelfthnightdj.advent2021.day05.Y21D05
import com.twelfthnightdj.advent2021.day06.Y21D06
import com.twelfthnightdj.advent2021.day07.Y21D07
import com.twelfthnightdj.advent2021.day08.Y21D08
import com.twelfthnightdj.advent2021.day09.Y21D09
import com.twelfthnightdj.advent2021.day10.Y21D10
import com.twelfthnightdj.advent2021.day11.Y21D11
import com.twelfthnightdj.advent2021.day12.Y21D12
import com.twelfthnightdj.advent2021.day13.Y21D13
import com.twelfthnightdj.advent2021.day14.Y21D14
import com.twelfthnightdj.advent2021.day15.Y21D15
import com.twelfthnightdj.advent2021.day16.Y21D16
import com.twelfthnightdj.advent2021.day17.Y21D17
import com.twelfthnightdj.advent2021.day18.Y21D18
import com.twelfthnightdj.advent2021.day20.Y21D20
import com.twelfthnightdj.advent2021.day21.Y21D21
import com.twelfthnightdj.advent2021.day22.Y21D22
import java.lang.Integer.min
import java.util.*
import java.util.Calendar.DAY_OF_MONTH

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null

    private var daySelected = 0
    private var yearSelected = 23

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val spinner = binding.daySpinner
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.days,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
        val year = binding.yearSpinner
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.years,
            android.R.layout.simple_spinner_item,
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
                15 -> y2015()
                21 -> y2021()
                22 -> y2022()
                23 -> y2023()
                else -> null
            }
            day?.let { runDay(it) } ?: Toast.makeText(
                requireContext(),
                "You haven't made this yet",
                Toast.LENGTH_LONG,
            ).show()
        }
        binding.daySpinner.setSelection(min(Calendar.getInstance().get(DAY_OF_MONTH), 25), true)
    }

    private fun y2015() =
        when (daySelected) {
            2 -> Y15D02()
            3 -> Y15D03()
            else -> null
        }

    private fun y2021() =
        when (daySelected) {
            1 -> Y21D01()
            2 -> Y21D02()
            3 -> Y21D03()
            4 -> Y21D04()
            5 -> Y21D05()
            6 -> Y21D06()
            7 -> Y21D07()
            8 -> Y21D08()
            9 -> Y21D09()
            10 -> Y21D10()
            11 -> Y21D11()
            12 -> Y21D12()
            13 -> Y21D13()
            14 -> Y21D14()
            15 -> Y21D15()
            16 -> Y21D16()
            17 -> Y21D17()
            18 -> Y21D18()
            20 -> Y21D20()
            21 -> Y21D21()
            22 -> Y21D22()
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
            20 -> Y22D20()
            else -> null
        }
    private fun y2023() =
        when (daySelected) {
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
            0 -> 23
            1 -> 22
            2 -> 21
            3 -> 15
            else -> 23
        }
}
