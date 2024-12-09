package com.aman.mviarchitecture

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.aman.mviarchitecture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityMainBinding
    private val viewModel:CounterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{ state ->
                if(state is CounterState.Value ){
                    binding.tvCounter.text = state.count.toString()
                }
            }
        }


        binding.btnDecrement.setOnClickListener {
            viewModel.processIntent(CounterIntent.Decrement)
        }

        binding.btnIncrement.setOnClickListener {
            viewModel.processIntent(CounterIntent.Increment)
        }

    }
}