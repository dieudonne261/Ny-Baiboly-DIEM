package com.example.nybaiboly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ChapitreAdapter(private val context: Context, private val chapitreList: ArrayList<ChapitreEtVerse>) :
    RecyclerView.Adapter<ChapitreAdapter.LivresViewHolder>() {
    inner class LivresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTestament: Button = itemView.findViewById(R.id.btnTestament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivresViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowbtn, parent, false)
        return LivresViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LivresViewHolder, position: Int) {
        val chapitre = chapitreList[position]
        holder.btnTestament.text = chapitre.id_chapitre
        holder.btnTestament.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("id_chapitre", chapitre.id_chapitre)
            val verseFragment = VerseFragment()
            verseFragment.arguments = bundle
            val element = DataSample.retrieveTestament(context)
            DataSample.saveTestament(context,element.first,chapitre.id_testament,chapitre.id_chapitre)
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, verseFragment)
                .addToBackStack(null)
                .commit()
        }


    }
    override fun getItemCount() = chapitreList.size
}
