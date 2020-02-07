package com.cosmic.venten.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmic.venten.R
import com.cosmic.venten.adapters.APIDataAdapter
import com.cosmic.venten.adapters.CSVDataAdapter
import com.cosmic.venten.model.api_model
import com.cosmic.venten.model.csv_model
import com.opencsv.CSVReader
import kotlinx.android.synthetic.main.activity_csv.*
import org.jetbrains.anko.UI
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.progressBar
import org.jetbrains.anko.uiThread
import java.io.File
import java.io.FileReader
import java.text.FieldPosition

class CsvActivity : AppCompatActivity() {

    lateinit var apiModel : api_model
    var position: Int = 0

    val mActivity: Activity = this@CsvActivity

    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csv)

        if (intent.getSerializableExtra("api_filter_data") != null) {
            apiModel = intent.getSerializableExtra("api_filter_data") as api_model
            position = intent.getIntExtra("filter_position", 0)
        }

        Log.i("position", position.toString())

        doAsync {

            lateinit var csvModel: csv_model
            val csvModelData = ArrayList<csv_model>()
            lateinit var csvDataAdapter: CSVDataAdapter

            try {

                val gpath: String = Environment.getExternalStorageDirectory().absolutePath
                val spath = "Venten"
                val fullpath = File(gpath + File.separator + spath)
                Log.w("fullpath", "" + fullpath.listFiles()[0].absolutePath.toString())

                val reader = CSVReader(FileReader(fullpath.listFiles()[0].absolutePath.toString()))

                var nextLine: Array<String>
                when (position) {

                    0 -> {

                        while (reader.readNext() != null) {
                            nextLine = reader.readNext()

                            if(nextLine[6].toInt() <= apiModel.end_year!!.toInt() &&
                                nextLine[6].toInt() >= apiModel.start_year!!.toInt() &&
                                nextLine[8] == apiModel.gender!!.capitalize() &&
                                apiModel.countries!!.contains(nextLine[4]) &&
                                apiModel.colors!!.contains(nextLine[7])) {

                                csvModel = csv_model()

                                csvModel.id = nextLine[0].toInt()
                                csvModel.first_name = nextLine[1]
                                csvModel.last_name = nextLine[2]
                                csvModel.gender = nextLine[8]
                                csvModel.bio = nextLine[10]
                                csvModel.email = nextLine[3]
                                csvModel.country = nextLine[4]
                                csvModel.job_title = nextLine[9]
                                csvModel.car_color = nextLine[7]
                                csvModel.car_model = nextLine[5]
                                csvModel.car_model_year = nextLine[6].toInt()

                                csvModelData.add(csvModel)

                            }
                        }
                    }

                    1 -> {

                        while (reader.readNext() != null) {
                            nextLine = reader.readNext()

                            if(nextLine[6].toInt() <= apiModel.end_year!!.toInt() &&
                                nextLine[6].toInt() >= apiModel.start_year!!.toInt() &&
                                apiModel.countries!!.contains(nextLine[4]) &&
                                apiModel.colors!!.contains(nextLine[7])) {

                                csvModel = csv_model()

                                csvModel.id = nextLine[0].toInt()
                                csvModel.first_name = nextLine[1]
                                csvModel.last_name = nextLine[2]
                                csvModel.gender = nextLine[8]
                                csvModel.bio = nextLine[10]
                                csvModel.email = nextLine[3]
                                csvModel.country = nextLine[4]
                                csvModel.job_title = nextLine[9]
                                csvModel.car_color = nextLine[7]
                                csvModel.car_model = nextLine[5]
                                csvModel.car_model_year = nextLine[6].toInt()

                                csvModelData.add(csvModel)

                            }
                        }
                    }

                    2 -> {

                        while (reader.readNext() != null) {
                            nextLine = reader.readNext()

                            if(nextLine[6].toInt() <= apiModel.end_year!!.toInt() &&
                                nextLine[6].toInt() >= apiModel.start_year!!.toInt() &&
                                nextLine[8] == apiModel.gender!!.capitalize() &&
                                apiModel.colors!!.contains(nextLine[7])) {

                                csvModel = csv_model()

                                csvModel.id = nextLine[0].toInt()
                                csvModel.first_name = nextLine[1]
                                csvModel.last_name = nextLine[2]
                                csvModel.gender = nextLine[8]
                                csvModel.bio = nextLine[10]
                                csvModel.email = nextLine[3]
                                csvModel.country = nextLine[4]
                                csvModel.job_title = nextLine[9]
                                csvModel.car_color = nextLine[7]
                                csvModel.car_model = nextLine[5]
                                csvModel.car_model_year = nextLine[6].toInt()

                                csvModelData.add(csvModel)

                            }
                        }
                    }

                    3 -> {

                        while (reader.readNext() != null) {
                            nextLine = reader.readNext()

                            if(nextLine[6].toInt() <= apiModel.end_year!!.toInt() &&
                                nextLine[6].toInt() >= apiModel.start_year!!.toInt()) {

                                csvModel = csv_model()

                                csvModel.id = nextLine[0].toInt()
                                csvModel.first_name = nextLine[1]
                                csvModel.last_name = nextLine[2]
                                csvModel.gender = nextLine[8]
                                csvModel.bio = nextLine[10]
                                csvModel.email = nextLine[3]
                                csvModel.country = nextLine[4]
                                csvModel.job_title = nextLine[9]
                                csvModel.car_color = nextLine[7]
                                csvModel.car_model = nextLine[5]
                                csvModel.car_model_year = nextLine[6].toInt()

                                csvModelData.add(csvModel)

                            }
                        }
                    }

                    4 -> {

                        while (reader.readNext() != null) {
                            nextLine = reader.readNext()

                            if(nextLine[6].toInt() <= apiModel.end_year!!.toInt() &&
                                nextLine[6].toInt() >= apiModel.start_year!!.toInt() &&
                                apiModel.countries!!.contains(nextLine[4])) {

                                csvModel = csv_model()

                                csvModel.id = nextLine[0].toInt()
                                csvModel.first_name = nextLine[1]
                                csvModel.last_name = nextLine[2]
                                csvModel.gender = nextLine[8]
                                csvModel.bio = nextLine[10]
                                csvModel.email = nextLine[3]
                                csvModel.country = nextLine[4]
                                csvModel.job_title = nextLine[9]
                                csvModel.car_color = nextLine[7]
                                csvModel.car_model = nextLine[5]
                                csvModel.car_model_year = nextLine[6].toInt()

                                csvModelData.add(csvModel)

                            }
                        }
                    }
                }

                Log.i("total", csvModelData.size.toString())

            } catch (e: Exception) {

                e.printStackTrace()
            }

            uiThread {

                progressBar_csv.visibility = View.GONE

                csv_data_rv.visibility = View.VISIBLE
                csv_data_rv.setHasFixedSize(true)
                csv_data_rv.layoutManager = LinearLayoutManager(mActivity)
                csvDataAdapter = CSVDataAdapter(baseContext, csvModelData)
                csv_data_rv.adapter = csvDataAdapter

                total_filtered_csv.text = "Total: " + csvModelData.size.toString()

            }
        }
    }
}
