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
                1 -> runDay1()
                2 -> runDay2()
                3 -> runDay3()
                4 -> runDay4()
                5 -> runDay5()
                6 -> runDay6()
                7 -> runDay7()
                8 -> runDay8()
                9 -> runDay9()
                10 -> runDay10()
                11 -> runDay11()
                12 -> runDay12()
                13 -> runDay13()
                14 -> runDay14()
                15 -> runDay15()
                16 -> runDay16()
                17 -> runDay17()
                18 -> runDay18()
                19 -> runDay19()
                20 -> runDay20()
                21 -> runDay21()
                22 -> runDay22()
                23 -> runDay23()
                24 -> runDay24()
                25 -> runDay25()
                else -> {}
            }
        }
        binding.daySpinner.setSelection(Calendar.getInstance().get(DAY_OF_MONTH), true)
    }

    fun runDay1() {
        val day = Day1()
        binding.tvAnswerA.text = day.partA()
        binding.tvAnswerB.text = day.partB()
    }
    fun runDay2() {
        val day = Day2()
        binding.tvAnswerA.text = day.partA()
        binding.tvAnswerB.text = day.partB()
    }
    fun runDay3() {}
    fun runDay4() {}
    fun runDay5() {}
    fun runDay6() {}
    fun runDay7() {}
    fun runDay8() {}
    fun runDay9() {}
    fun runDay10() {}
    fun runDay11() {}
    fun runDay12() {}
    fun runDay13() {}
    fun runDay14() {}
    fun runDay15() {}
    fun runDay16() {}
    fun runDay17() {}
    fun runDay18() {}
    fun runDay19() {}
    fun runDay20() {}
    fun runDay21() {}
    fun runDay22() {}
    fun runDay23() {}
    fun runDay24() {}
    fun runDay25() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        Log.d("ROLANDO", "item selected pos: $pos id: $id")
        daySelected = pos
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Log.d("ROLANDO", "Nothing selected")
    }
}
