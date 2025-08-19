package com.example.nybaiboly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class RecentAdapter(private val context: Context, private val recentList: ArrayList<Recent>) :
    RecyclerView.Adapter<RecentAdapter.RecentViewHolder>() {
    inner class RecentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnRecent: Button = itemView.findViewById(R.id.btnTestament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowbtn, parent, false)
        return RecentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val recent = recentList[position]
        holder.btnRecent.text = recent.text
        holder.btnRecent.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_chapitre",recent.text)
            val verseFragment = VerseFragment()
            verseFragment.arguments = bundle
            DataSample.saveTestament(context,0,(recent.idlivres).toInt(),recent.idchapitre)
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, verseFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() = recentList.size
}
