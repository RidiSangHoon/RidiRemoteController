package com.example.leesanghoon.ridiremotecontroller

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.leesanghoon.ridiremotecontroller.Model.Node
import java.util.*

class MainActivity : Activity() {

    private lateinit var node: com.example.leesanghoon.ridiremotecontroller.Model.Node
    private val peersTextView by lazy { findViewById<TextView>(R.id.peersTextView) }
    private val framesTextView by lazy {findViewById<TextView> (R.id.peersTextView)}

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
        node.broadcastFrame(ByteArray(1))
        for (i in 0..1999) {
            val frameData = ByteArray(1024)
            Random().nextBytes(frameData)
            node.broadcastFrame(frameData)
        }
    }

    fun refreshPeers() {
        peersTextView.text = "${node.getLinks().size} connected"
    }

    fun refreshFrames() {
        framesTextView.text = "${node.getFramesCount()} frames"
    }
}
