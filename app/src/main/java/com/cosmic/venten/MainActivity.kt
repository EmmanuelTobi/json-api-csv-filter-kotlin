package com.cosmic.venten

import android.app.Activity
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmic.venten.adapters.APIDataAdapter
import com.cosmic.venten.listeners.OnAPIDataGotten
import com.cosmic.venten.model.api_model
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val mActivity: Activity = this@MainActivity
    lateinit var adapter: APIDataAdapter

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiTask = ApiTask(object : OnAPIDataGotten {

            override fun api_json_loaded(apiModelArr: Array<api_model>) {

                api_data_filter_rv.visibility = View.VISIBLE
                api_data_filter_rv.setHasFixedSize(true)
                api_data_filter_rv.layoutManager = LinearLayoutManager(mActivity)

                val apiModelArrList = ArrayList(listOf(*apiModelArr))
                progressBar.visibility = View.GONE
                adapter = APIDataAdapter(baseContext, apiModelArrList)

                api_data_filter_rv.adapter = adapter
            }
        })
        apiTask.execute()

    }

    class ApiTask(private val listener: OnAPIDataGotten): AsyncTask<Void, Void, String>() {

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
                val mapper = ObjectMapper()

                val apiModelArray = mapper.readValue(jArray.toString(), Array<api_model>::class.java)
                listener.api_json_loaded(apiModelArray)


            } catch (e: JSONException) {
                Log.e("JSONException", "Error: " + e.toString())
            }
        }
    }
}
