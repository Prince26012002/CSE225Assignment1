package com.example.cse225assignment1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener

class HomePage : AppCompatActivity() {
    private var lengthOfText = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val pBarCircular = findViewById<ProgressBar>(R.id.pCircular)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        val start = findViewById<Button>(R.id.start)
        val restart = findViewById<Button>(R.id.restart)
        val cancel = findViewById<Button>(R.id.cancel)
        var toolBar = findViewById<Toolbar>(R.id.toolBar)

        //Tool bar
        toolBar.setTitle("Home Page")
        setSupportActionBar(toolBar)

        toolBar.setNavigationOnClickListener{
            //Custom Toast
            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast_layout, vg)

            val textV = layout.findViewById<TextView>(R.id.tView)
            textV.text = "Back Arrow Pressed"

            val toast = Toast(applicationContext)

            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100)
            toast.duration = Toast.LENGTH_LONG
            toast.setView(layout)
            toast.show()
        }

        //Progress Bar
        pBarCircular.visibility = View.INVISIBLE
        editText.addTextChangedListener{
            pBarCircular.visibility = View.VISIBLE
            lengthOfText = editText.length()
            progressBar.progress = lengthOfText
            textView.text = "Words left: "+(progressBar.max-lengthOfText).toString() + "/" + progressBar.max
            if(lengthOfText == progressBar.max){
                pBarCircular.visibility = View.INVISIBLE
            }
        }

        //Rating Bar
        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener{ratingBar, rating, fromUser ->
            //custom toast
            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater = layoutInflater
            val layout: View = inflater.inflate(R.layout.custom_toast_layout, vg)

            val textV = layout.findViewById<TextView>(R.id.tView)
            textV.text = "Rating $rating"

            val toast = Toast(applicationContext)

            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.duration = Toast.LENGTH_LONG
            toast.setView(layout)
            toast.show()
        }
        var alarmManager: AlarmManager
        val intent = Intent(this, BroadCastAlarmManager::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 144, intent, 0)

        start.setOnClickListener{
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, pendingIntent)
        }

        restart.setOnClickListener{
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000, pendingIntent)
        }

        cancel.setOnClickListener {
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }
    // Menu Tool bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tool_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int = item.itemId
        if(id == R.id.action_search)
        {
            Toast.makeText(applicationContext, "Sarch menu", Toast.LENGTH_LONG).show()
            return true
        }
        if(id == R.id.menu_camera)
        {
            Toast.makeText(applicationContext, "Camera Menu", Toast.LENGTH_LONG).show()
            return true
        }

        if(id == R.id.action_settings)
        {
            Toast.makeText(applicationContext, "Setting Menu", Toast.LENGTH_LONG).show()
            return true
        }
        else if(id == R.id.action_info)
        {
            Toast.makeText(applicationContext, "Dialog Info", Toast.LENGTH_LONG).show()
            return true
        }
        else if(id == R.id.action_alert)
        {
            Toast.makeText(applicationContext,"Dialog Alert", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}