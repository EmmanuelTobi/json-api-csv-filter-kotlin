package com.cosmic.venten.activities

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cosmic.venten.R
import com.cosmic.venten.model.api_model
import com.opencsv.CSVReader
import java.io.File
import java.io.FileReader

class CsvActivity : AppCompatActivity() {

    lateinit var apiModel : api_model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csv)
//        setSupportActionBar(toolbar)

        if (intent.getSerializableExtra("api_filter_data") != null) {
            apiModel = intent.getSerializableExtra("api_filter_data") as api_model
        }

        try {

            val gpath: String = Environment.getExternalStorageDirectory().absolutePath
            val spath = "Venten"
            val fullpath = File(gpath + File.separator + spath)
            Log.w("fullpath", "" + fullpath.listFiles()[0].absolutePath.toString())

            val reader = CSVReader(FileReader(fullpath.listFiles()[0].absolutePath.toString()))
//
            var nextLine: Array<String>
            nextLine = reader.readNext()

            while (nextLine  != null) {

                nextLine = reader.readNext()
                Log.w("csvNextLine", nextLine[0] + nextLine[1] + "etc...")
            }

        } catch (e: Exception) {

            e.printStackTrace()
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show()
        }
    }
}
