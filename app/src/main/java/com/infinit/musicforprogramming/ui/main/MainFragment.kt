package com.infinit.musicforprogramming.ui.main

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.infinit.musicforprogramming.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(),MediaPlayer.OnPreparedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepareAsync()
        }
        mediaPlayer.setOnPreparedListener(this)
        btnPlayPause.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            } else mediaPlayer.start()
        }
    }

    override fun onPrepared(player: MediaPlayer?) {
        showMessage("track prepared")
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    fun showMessage(message: String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }
}
