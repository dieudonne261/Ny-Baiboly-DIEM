package com.example.nybaiboly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class LivresAdapter(private val context: Context, private val livresList: ArrayList<Livres>) :
    RecyclerView.Adapter<LivresAdapter.LivresViewHolder>() {
    inner class LivresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTestament: Button = itemView.findViewById(R.id.btnTestament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivresViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowbtn, parent, false)
        return LivresViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LivresViewHolder, position: Int) {
        val livres = livresList[position]
        holder.btnTestament.text = livres.nom_livre
        holder.btnTestament.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id_livre", livres.id_livre)
            val chapitreFragment = ChapitreFragment()
            val verseFragment = VerseFragment()
            chapitreFragment.arguments = bundle
            verseFragment.arguments = bundle
            DataSample.saveTestament(context,livres.id_testament,livres.id_livre,"")
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, chapitreFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    override fun getItemCount() = livresList.size
    }
