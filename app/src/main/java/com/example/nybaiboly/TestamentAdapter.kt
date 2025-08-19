package com.example.nybaiboly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class TestamentAdapter(private val context: Context, private val testamentList: ArrayList<Testament>) :
    RecyclerView.Adapter<TestamentAdapter.TestamentViewHolder>() {
    inner class TestamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTestament: Button = itemView.findViewById(R.id.btnTestament)
        //val btnSearch: Button = itemView.findViewById(R.id.searchbtn)
        //val elmSearch: Button = itemView.findViewById(R.id.searchbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestamentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowbtn, parent, false)
        return TestamentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestamentViewHolder, position: Int) {
        val testament = testamentList[position]
        holder.btnTestament.text = testament.nom_testament
        holder.btnTestament.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id_testament", testament.id_testament)
            val livreFragment = LivreFragment()
            livreFragment.arguments = bundle
            DataSample.saveTestament(context,testament.id_testament,0,"")
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, livreFragment)
                .addToBackStack(null)
                .commit()
        }
        /*
        holder.btnSearch.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("search","")
            val searchFragment = SearchFragment()
            searchFragment.arguments = bundle
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, searchFragment)
                .addToBackStack(null)
                .commit()
        }

         */




    }

    override fun getItemCount() = testamentList.size
}
