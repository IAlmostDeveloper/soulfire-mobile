package ru.ialmostdeveloper.soulfire_mobile.storage

import android.content.Context
import com.google.gson.Gson
import ru.ialmostdeveloper.soulfire_mobile.network.Session
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder


class Storage(private val context: Context) {
    fun readSession(): Session {
        var session = Session()
        val folder = File(context.filesDir, "mqtt")
        val file = File(folder.absolutePath + "Session.txt")
        val sessionRaw = StringBuilder()
        try {
            if (file.exists()) {
                val stream = FileInputStream(file)
                var i: Int
                while (stream.read().also { i = it } != -1) {
                    sessionRaw.append(i.toChar())
                }
                if (!sessionRaw.toString().isEmpty()) {
                    session = Gson().fromJson(sessionRaw.toString(), Session::class.java)
                }
            } else {
                try {
                    file.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return session
    }

    fun writeSession(session: Session?) {
        val folder = File(context.filesDir, "mqtt")
        val file = File(folder.absolutePath + "Session.txt")
        if (!folder.exists()) {
            folder.mkdir()
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            val fileToWrite = File(file.absolutePath)
            val writer = FileWriter(fileToWrite)
            writer.append(Gson().toJson(session))
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readServerUrl(): String {
        val folder = File(context.filesDir, "mqtt")
        val file = File(folder.absolutePath + "ServerUrl.txt")
        var url = ""
        try {
            val urlRaw = StringBuilder()
            if (file.exists()) {
                val stream = FileInputStream(file)
                var i: Int
                while (stream.read().also { i = it } != -1) {
                    urlRaw.append(i.toChar())
                }
                if (!urlRaw.toString().isEmpty()) {
                    url = urlRaw.toString()
                }
            } else {
                try {
                    file.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return if (url.isEmpty()) "https://ik.remzalp.ru" else url
    }

    fun writeServerUrl(newServerUrl: String?) {
        val folder = File(context.filesDir, "mqtt")
        val file = File(folder.absolutePath + "ServerUrl.txt")
        if (!folder.exists()) {
            folder.mkdir()
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        try {
            val fileToWrite = File(file.absolutePath)
            val writer = FileWriter(fileToWrite)
            writer.append(newServerUrl)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}