package com.example.apps10xtask.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apps10xtask.data.model.ForCasteList
import com.example.apps10xtask.databinding.DayItemBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by bhupendrapatel on 09/06/22.
 */
class ForeCastAdapter(var foreCastList: List<ForCasteList>) :
    RecyclerView.Adapter<ForeCastAdapter.ForeCastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DayItemBinding.inflate(inflater, parent, false)
        return ForeCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        holder.bind(foreCastList[position])
    }

    override fun getItemCount(): Int {
        return foreCastList.size
    }

    inner class ForeCastViewHolder(private val dayItemBinding: DayItemBinding) :
        RecyclerView.ViewHolder(dayItemBinding.root) {
        fun bind(item: ForCasteList) {
            dayItemBinding.foreCastList = item
            dayItemBinding.executePendingBindings()

            val simpleDateFormat = SimpleDateFormat("EEEE")
            val dayName = simpleDateFormat.format(Date(item.dt))
            dayItemBinding.dayName.text = dayName

        }
    }

}