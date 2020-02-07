package com.cosmic.venten.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cosmic.venten.R
import com.cosmic.venten.activities.CsvActivity
import com.cosmic.venten.model.api_model
import com.cosmic.venten.model.csv_model
import kotlinx.android.synthetic.main.filter_view_holder.view.*
import kotlinx.android.synthetic.main.filtered_view_holder.view.*
import java.util.*

class CSVDataAdapter(val mContext: Context,
                     var mArrayList: ArrayList<csv_model>) : RecyclerView.Adapter<CSVDataAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val mView = LayoutInflater.from(mContext).inflate(R.layout.filtered_view_holder, parent, false)
        return ViewHolder(mView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.full_name.text = mArrayList[position].first_name + " " + mArrayList[position].last_name
        holder.country.text = mArrayList[position].country
        holder.job_title.text = mArrayList[position].job_title
        holder.bio.text = mArrayList[position].bio
        holder.gender.text = mArrayList[position].gender
        holder.email.text = mArrayList[position].email
        holder.car_make.text = mArrayList[position].car_model
        holder.car_color.text = mArrayList[position].car_color
        holder.car_year.text = mArrayList[position].car_model_year.toString()

    }

    fun clearAdapter() {
        this.mArrayList.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var full_name = view.fullname
        var email = view.email
        var country = view.country
        var car_make = view.car_make
        var car_year = view.car_year
        var gender = view.bio_gender
        var job_title = view.job_title
        var car_color = view.car_color
        var bio = view.bio
    }
}