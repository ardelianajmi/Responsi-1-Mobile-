package com.example.responsi1mobileh1d023007

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023007.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023007.databinding.ActivityHalamanCoachBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HalamanCoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil data coach
        fetchCoachData()

        // Delay 3 detik sebelum menampilkan card
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.duration = 800
            binding.cardDeskripsi.startAnimation(fadeIn)
            binding.cardDeskripsi.visibility = android.view.View.VISIBLE
        }, 3000) // 3 detik
    }

    private fun fetchCoachData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = RetrofitInstance.api.getTeam(404)
                if (response.isSuccessful) {
                    val coach = response.body()?.coach
                    binding.tvCoachName.text = coach?.name ?: "Tidak diketahui"
                    binding.tvCoachBirth.text = coach?.dateOfBirth ?: "-"
                    binding.tvCoachCountry.text = coach?.nationality ?: "-"
                    binding.imgCoach.setImageResource(R.drawable.phil_parkinsom)
                } else {
                    Toast.makeText(
                        this@HalamanCoachActivity,
                        "Gagal memuat data (${response.code()})",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@HalamanCoachActivity,
                    "Terjadi kesalahan: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
