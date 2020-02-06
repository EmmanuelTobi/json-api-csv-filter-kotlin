package com.cosmic.venten

import android.app.Activity
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    val mActivity: Activity = this@MainActivity

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiTask = ApiTask()
        apiTask.execute()

    }

    class ApiTask: AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {

            val url = "https://ven10.co/assessment/filter.json"
            var result = ""

            try {

                val Url = URL(url)
                val connection = Url.openConnection() as HttpURLConnection
                val `is` = connection.inputStream
                val br = BufferedReader(InputStreamReader(`is`, "utf-8"), 8)
                val sBuilder = StringBuilder()

                var line = br.readLine()
                sBuilder.append(line + "\n")

                while (line != null) {

                    line = br.readLine()
                    sBuilder.append(line + "\n")
                }

                `is`.close()
                result = sBuilder.toString()

            } catch (e: Exception) {

                e.printStackTrace()
            }

            return result
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Log.i("apiTask", result.toString())

            try {

                val jArray = JSONArray(result)
                var i = 0
                while (i < jArray.length()) {

                    val jObject = jArray.getJSONObject(i)

                    val id = jObject.getString("id")

                    Log.i("JsonID", id)

                    i++
                }

                Log.i("JsonLength", i.toString())

            } catch (e: JSONException) {
                Log.e("JSONException", "Error: " + e.toString())
            }
        }
    }
}
