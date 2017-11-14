package com.example.leesanghoon.ridiremotecontroller

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.leesanghoon.ridiremotecontroller.Model.EpubPeerToPeer

class MainActivity : Activity() {

    private lateinit var epubPeerToPeer: com.example.leesanghoon.ridiremotecontroller.Model.EpubPeerToPeer
    private val peersTextView by lazy { findViewById<TextView>(R.id.peersTextView) }
    private val framesTextView by lazy { findViewById<TextView>(R.id.framesTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        epubPeerToPeer = EpubPeerToPeer(this@MainActivity)
    }

    override fun onStart() {
        super.onStart()
        epubPeerToPeer.start()
    }

    override fun onStop() {
        super.onStop()
        epubPeerToPeer.stop()
    }

    fun leftBtnClicked(v: View) {
        val sendMsg = "left"
        val frameData = sendMsg.toByteArray(Charsets.UTF_8)
        epubPeerToPeer.broadcastFrame(frameData)
    }

    fun rightBtnClicked(v: View) {
        val sendMsg = "right"
        val frameData = sendMsg.toByteArray(Charsets.UTF_8)
        epubPeerToPeer.broadcastFrame(frameData)
    }

    fun refreshPeers() {
        peersTextView.text = "${epubPeerToPeer.getLinks().size} connected"
    }

    fun refreshFrames() {
        val frames = epubPeerToPeer.getFrames()
        val frameString = String(frames)
        Log.e("MainActivity", "Frame String => ${frameString}")
        framesTextView.text = "${epubPeerToPeer.getFramesCount()} frames sent"
    }
}
