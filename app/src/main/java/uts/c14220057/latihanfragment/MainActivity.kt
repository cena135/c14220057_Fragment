package uts.c14220057.latihanfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mFragmentManager = supportFragmentManager
        val mfSatu = fSatu()

        mFragmentManager.findFragmentByTag(fSatu::class.java.simpleName)
        mFragmentManager
            .beginTransaction()
            .add(R.id.frameContainer, mfSatu, fSatu::class.java.simpleName)
            .commit()

        findViewById<Button>(R.id.btnPermainan).setOnClickListener {
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, mfSatu, fSatu::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }


        findViewById<Button>(R.id.btnScore).setOnClickListener {
            val mfDua = fDua()
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, mfDua, fDua::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }

        findViewById<Button>(R.id.btnSetting).setOnClickListener {
            val mfTiga = fTiga()
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, mfTiga, fTiga::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }

    }
}