package com.example.leesanghoon.ridiremotecontroller.Model

import com.example.leesanghoon.ridiremotecontroller.MainActivity
import io.underdark.Underdark
import io.underdark.transport.Link
import io.underdark.transport.Transport
import io.underdark.transport.TransportKind
import io.underdark.transport.TransportListener
import io.underdark.transport.TransportListener.ActivityCallback
import java.util.*

class Node(private val activity: MainActivity) : TransportListener {
    private var running: Boolean = false
    private var nodeId: Long = 0
    private val transport: Transport
    private var receivedDatas: ByteArray = ByteArray(1024)
    private val links: ArrayList<Link> = ArrayList()
    private var framesCount = 0
    private val appId = 234235

    init {
        do {
            nodeId = Random().nextLong()
        } while (nodeId == 0L)
        if (nodeId < 0) nodeId = -nodeId
        val kinds = EnumSet.of(TransportKind.WIFI)
        this.transport = Underdark.configureTransport(appId, nodeId, this,
                null, activity.applicationContext, kinds)
    }

    fun start() {
        if (running) return
        running = true
        transport.start()
    }

    fun stop() {
        if (!running) return
        running = false
        transport.stop()
    }

    fun broadcastFrame(frameData: ByteArray) {
        if (links.isEmpty())
            return

        ++framesCount
        receivedDatas = frameData

        activity.refreshFrames()

        for (link in links)
            link.sendFrame(frameData)
    }

    fun getLinks(): ArrayList<Link> = links

    fun getFramesCount(): Int = framesCount

    fun getFrames(): ByteArray = receivedDatas

    override fun transportNeedsActivity(transport: Transport, callback: ActivityCallback) {
        callback.accept(activity)
    }

    override fun transportLinkConnected(transport: Transport, link: Link) {
        links.add(link)
        activity.refreshPeers()
    }

    override fun transportLinkDisconnected(transport: Transport, link: Link) {
        links.remove(link)
        activity.refreshPeers()
        if (links.isEmpty()) {
            framesCount = 0
            activity.refreshFrames()
        }
    }

    override fun transportLinkDidReceiveFrame(transport: Transport, link: Link, frameData: ByteArray) {
        ++framesCount
        receivedDatas = frameData
        activity.refreshFrames()
    }
}