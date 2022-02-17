package com.example.cse225assignment1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

class BroadCastAlarmManager : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var mp = MediaPlayer.create(context, R.raw.alarm)
        Log.d("Hey","repeating alarm")
        mp.start()
        Toast.makeText(context, "Alarm started", Toast.LENGTH_LONG).show()
    }


}