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
import kotlinx.android.synthetic.main.filter_view_holder.view.*
import java.util.*

class APIDataAdapter(val mContext: Context,
                     var mArrayList: ArrayList<api_model>) : RecyclerView.Adapter<APIDataAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return mArrayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val mView = LayoutInflater.from(mContext).inflate(R.layout.filter_view_holder, parent, false)
        return ViewHolder(mView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(mArrayList[position].gender != ""){
            holder.gender.text = mArrayList[position].gender
        } else {
            holder.gender.text = "none"
        }

        if(mArrayList[position].start_year != null && mArrayList[position].end_year != null){
            holder.dateRange.text = mArrayList[position].start_year.toString() + " - " + mArrayList[position].end_year.toString()
        } else {
            holder.dateRange.text = "none"
        }

        if(mArrayList[position].countries!!.isNotEmpty()) {
            var countryStr = ""

            mArrayList[position].countries!!.forEach {
                countryStr += "$it, "
            }
            holder.countries.text = countryStr
        } else {
            holder.countries.text = "none"
        }

        if(mArrayList[position].colors!!.isNotEmpty()) {
            var colorsStr = ""

            mArrayList[position].colors!!.forEach {
                colorsStr += "$it, "
            }
            holder.colors.text = colorsStr
        } else {
            holder.colors.text = "none"
        }

        holder.itemView.setOnClickListener {

            val intent = Intent(mContext, CsvActivity::class.java)
            intent.putExtra("api_filter_data", mArrayList[position])
            intent.putExtra("filter_position", position)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            mContext.startActivity(intent)
        }
    }

    fun clearAdapter() {
        this.mArrayList.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var gender = view.gender
        var dateRange = view.dateRange
        var countries = view.countries
        var colors = view.colors
    }
}