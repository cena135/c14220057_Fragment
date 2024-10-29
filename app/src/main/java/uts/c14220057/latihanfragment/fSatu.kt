package uts.c14220057.latihanfragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fSatu : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var firstButton: Button? = null
    private var firstButtonNumber: Int? = null
    var score: Int = 50

    fun transitionToFDua() {
        val mfDua = fDua.newInstance(score.toString(), "") // Pass the score as param1
        val mFragmentManager = parentFragmentManager
        mFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainer, mfDua, fDua::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _btnGiveUp = view.findViewById<Button>(R.id.btnGiveUp)
        _btnGiveUp.setOnClickListener {
            transitionToFDua()
        }

        param1?.let {
            score = it.toIntOrNull() ?: 50
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_satu, container, false)

        val _tvScore = view.findViewById<TextView>(R.id.tvScore)
        _tvScore.text = score.toString()

        randomizeButtonNumbers(view)

        return view
    }

    private fun randomizeButtonNumbers(view: View) {
        val buttons = arrayOf(
            view.findViewById<Button>(R.id.btn1),
            view.findViewById<Button>(R.id.btn2),
            view.findViewById<Button>(R.id.btn3),
            view.findViewById<Button>(R.id.btn4),
            view.findViewById<Button>(R.id.btn5),
            view.findViewById<Button>(R.id.btn6),
            view.findViewById<Button>(R.id.btn7),
            view.findViewById<Button>(R.id.btn8),
            view.findViewById<Button>(R.id.btn9),
            view.findViewById<Button>(R.id.btn10)
        )

        val numbers = mutableListOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
        numbers.shuffle()

        buttons.forEachIndexed { index, button ->
            button.text = ""
            button.setTextColor(android.graphics.Color.TRANSPARENT)

            button.setOnClickListener {
                if (button.text.isNotEmpty()) return@setOnClickListener

                button.text = numbers[index].toString()
                button.setTextColor(android.graphics.Color.BLACK)

                if (firstButton == null) {
                    firstButton = button
                    firstButtonNumber = numbers[index]
                } else {
                    val secondButton = button
                    val secondButtonNumber = numbers[index]

                    if (firstButton !== secondButton) {
                        if (firstButtonNumber == secondButtonNumber) {
                            score += 10
                            view.findViewById<TextView>(R.id.tvScore).text = score.toString()
                            firstButton = null

                            if (buttons.all { it.text.isNotEmpty() }) {
                                transitionToFDua()
                            }
                        } else {
                            score -= 5
                            view.findViewById<TextView>(R.id.tvScore).text = score.toString()

                            if (score <= 0) {
                                transitionToFDua()
                            }

                            Handler().postDelayed({
                                firstButton?.text = ""
                                firstButton?.setTextColor(android.graphics.Color.TRANSPARENT)
                                secondButton.text = ""
                                secondButton.setTextColor(android.graphics.Color.TRANSPARENT)

                                firstButton = null
                            }, 500)
                        }
                    } else {
                        firstButton?.text = ""
                        firstButton?.setTextColor(android.graphics.Color.TRANSPARENT)
                        firstButton = null
                    }
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fSatu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
