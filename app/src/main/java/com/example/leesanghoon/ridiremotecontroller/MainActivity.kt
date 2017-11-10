package com.example.leesanghoon.ridiremotecontroller

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.leesanghoon.ridiremotecontroller.Model.Node

class MainActivity : Activity() {

    private lateinit var node: com.example.leesanghoon.ridiremotecontroller.Model.Node
    private val peersTextView by lazy { findViewById<TextView>(R.id.peersTextView) }
    private val framesTextView by lazy { findViewById<TextView>(R.id.peersTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        node = Node(this@MainActivity)
    }

    override fun onStart() {
        super.onStart()
        node.start()
    }

    override fun onStop() {
        super.onStop()
        node.stop()
    }

    fun sendFrames(view: View) {
        val sendMsg: String = "안녕하세요.dfadfadfadfadfdfadfadfad"
        val frameData = sendMsg.toByteArray(Charsets.UTF_8)
        node.broadcastFrame(frameData)
    }

    fun refreshPeers() {
        peersTextView.text = "${node.getLinks().size} connected"
    }

    fun refreshFrames() {

        var frames = node.getFrames()
        Log.e("MainActicity","Frame count => ${frames.count()}")

        val frameString = String(frames)
        Log.e("MainActivity","Frame String => ${frameString}")

        val links = node.getLinks()
        Log.e("MainActivity","Links Count => ${links.count()}")

        for(link in links) {
            Log.e("MainActivity","Link => ${link}")
        }

        framesTextView.text = "${node.getFramesCount()} frames"
    }
}
