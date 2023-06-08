package com.example.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.Fragment
import com.example.mywallet.Fragment.Add_inc_ex_Fragment
import com.example.mywallet.Fragment.ChartFragment
import com.example.mywallet.Fragment.TransactionFragment
import com.example.mywallet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadFragments(Add_inc_ex_Fragment())


        binding.BottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.IncExp -> {
                    loadFragments(Add_inc_ex_Fragment())
                    true
                }

                R.id.trancationlist -> {
                    loadFragments(TransactionFragment())
                    true
                }

                R.id.chart -> {
                    loadFragments(ChartFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }

    }
    private fun loadFragments(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag, fragment)
            .commit()

    }
}