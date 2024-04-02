package com.example.aplikasipertama

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipertama.model.Student

class ListAdapter(private val context: Context, val student: List<Student>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_item_name)
        val major: TextView = itemView.findViewById(R.id.tv_item_major)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val item = student[position]

        holder.name.text = item.name
        holder.major.text = item.major
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("STUDENT", item)
//            intent.putExtra("NAME", item.name)
//            intent.putExtra("MAJOR", item.major)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = student.size
}