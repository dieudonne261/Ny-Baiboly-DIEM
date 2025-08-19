package com.example.nybaiboly

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class SearchAdapter(private val context: Context, private val chapitreList: ArrayList<Search>) :
    RecyclerView.Adapter<SearchAdapter.LivresViewHolder>() {
    inner class LivresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTestament: Button = itemView.findViewById(R.id.btnTestament)
        val textaff: TextInputEditText = itemView.findViewById(R.id.rechtxt2)
        val textsearch: TextInputEditText = itemView.findViewById(R.id.rechtxt2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivresViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.searchbnt, parent, false)
        return LivresViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LivresViewHolder, position: Int) {

        val element = DataSample.retrievePreferences(context)
        val databaseHelper = DatabaseHelper.getInstance(context)
        val chapitre = chapitreList[position]
        //holder.btnTestament.text =  databaseHelper.getData("b_name","ci_diem_boky",chapitre.id_testament).toString() + " : " + chapitre.id_chapitre
        holder.btnTestament.text = chapitre.text_aff +" "+ chapitre.idchapitre +" : " + chapitre.idverse

        val text = chapitre.text
        val spannableString = SpannableString(text)
        val wordToColor = chapitre.text_search
        val wordToColorPattern = Pattern.compile(wordToColor, Pattern.CASE_INSENSITIVE)
        val matcher = wordToColorPattern.matcher(text)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#c894ff")),
                start,
                end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        holder.textaff.setText(spannableString)


//        holder.textaff.setText(chapitre.text)
        holder.textaff.textSize = if (element.fourth == 0) 1F else (element.fourth * 5).toFloat()
/*
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

         */


    }
    override fun getItemCount() = chapitreList.size
}
