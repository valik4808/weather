package com.example.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL

private var user_field: EditText? = null
    private var main_btn: Button? = null
    private var result_info: TextView? = null

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user_field = findViewById(R.id.field)
        main_btn = findViewById(R.id.btn)
        result_info = findViewById(R.id.resul)

        main_btn?.setOnClickListener{
            if(user_field?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Введите город", Toast.LENGTH_LONG).show()
            else{
                var city: String = user_field?.text.toString()
                var key: String = "e37fa16107318a46d8c044e4cb68b9a8"
                var url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key"
                doAsync {
                    val apiResponse = URL(url).readText()
                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")

                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")

                    result_info?.text = "Температура: $temp\n$desc"


                }


            }
        }


    }
}