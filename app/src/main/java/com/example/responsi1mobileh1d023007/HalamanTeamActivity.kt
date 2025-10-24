package com.example.responsi1mobileh1d023007

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023007.databinding.ActivityHalamanTeamBinding
import com.example.responsi1mobileh1d023007.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023007.ui.PlayerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HalamanTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPlayers.layoutManager = LinearLayoutManager(this)

        fetchSquadData()
    }

    private fun fetchSquadData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = RetrofitInstance.api.getTeam(404)
                if (response.isSuccessful) {
                    val squad = response.body()?.squad ?: emptyList()
                    val adapter = PlayerAdapter(squad)
                    binding.rvPlayers.adapter = adapter
                }
            } catch (e: Exception) {
            }
        }
    }
}