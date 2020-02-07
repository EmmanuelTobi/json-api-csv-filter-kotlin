package com.cosmic.venten.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cosmic.venten.R
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(mArrayList[position].gender != ""){
            holder.gender.text = mArrayList[position].gender
        } else {
            holder.gender.text = "none"
        }

        if(mArrayList[position].start_year != null){
            holder.startYear.text = mArrayList[position].start_year.toString()
        } else {
            holder.startYear.text = "none"
        }

        if(mArrayList[position].end_year != null){
            holder.endYear.text = mArrayList[position].end_year.toString()
        } else {
            holder.endYear.text = "none"
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
        var startYear = view.startYear
        var endYear = view.endYear
        var countries = view.countries
        var colors = view.colors
    }
}