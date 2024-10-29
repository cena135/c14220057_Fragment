package uts.c14220057.latihanfragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class fSatu : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var firstButton: Button? = null
    private var firstButtonNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_f_satu, container, false)

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
                button.text = numbers[index].toString()
                button.setTextColor(android.graphics.Color.BLACK)

                if (firstButton == null) {
                    firstButton = button
                    firstButtonNumber = numbers[index]
                } else {
                    val secondButton = button
                    val secondButtonNumber = numbers[index]

                    if (firstButtonNumber == secondButtonNumber) {
                        firstButton = null
                    } else {
                        Handler().postDelayed({
                            firstButton?.text = ""
                            firstButton?.setTextColor(android.graphics.Color.TRANSPARENT)
                            secondButton.text = ""
                            secondButton.setTextColor(android.graphics.Color.TRANSPARENT)

                            firstButton = null
                        }, 1000)
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
