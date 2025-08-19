package com.example.nybaiboly

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VerseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var chapitreList: ArrayList<ChapitreEtVerse>
    private lateinit var textView: TextView
    private lateinit var textVerse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        val databaseHelperLocal = DatabaseHelperLocal.getInstance(requireContext())
        val idchapitre = arguments?.getString("id_chapitre", "") ?: ""
        val element = DataSample.retrieveTestament(requireContext())
        val element_size = DataSample.retrievePreferences(requireContext())
        val idlivre = element.second
        textView = view.findViewById(R.id.textView7)
        textVerse = view.findViewById(R.id.textViewVerse)
        chapitreList = ArrayList()
        databaseHelper.open()

        if (isNotInt(idchapitre)){
            if(idchapitre.length>7){
                chapitreList.addAll(databaseHelper.getVerseData(element.second,element.third.toString()))
                textView.text = idchapitre
            }
            else {
                chapitreList.addAll(databaseHelper.getVerseData(idlivre,idchapitre))
                textView.text = databaseHelper.getData("b_name","ci_diem_boky",idlivre) + " : $idchapitre"
                val id = databaseHelperLocal.insertInfo(
                    textView.text.toString(),
                    idlivre.toString(),
                    idchapitre
                )
            }

        }

        else {
            chapitreList.addAll(databaseHelper.getVerseData(idlivre,idchapitre))
            textView.text = databaseHelper.getData("b_name","ci_diem_boky",idlivre) + " : $idchapitre"
            val id = databaseHelperLocal.insertInfo(
                textView.text.toString(),
                idlivre.toString(),
                idchapitre
            )
        }


        databaseHelper.close()
        textVerse.textSize = if (element_size.fourth == 0) 1F else (element_size.fourth * 5).toFloat()

        val spannableStringBuilder = SpannableStringBuilder()

        for (chapitreEtVerse in chapitreList) {
            val verseText = "${chapitreEtVerse.id_verse}. ${chapitreEtVerse.text} "
            val spannableString = SpannableString(verseText)
            spannableString.setSpan(
                ForegroundColorSpan(Color.parseColor("#c894ff")),
                0,
                "${chapitreEtVerse.id_verse}.".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                RelativeSizeSpan(1.40f),
                0,
                "${chapitreEtVerse.id_verse}.".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.append(spannableString)
        }

        textVerse.text = spannableStringBuilder


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verse, container, false)
    }

    fun isNotInt(idchapitre: String): Boolean {
        try {
            idchapitre.toInt()
            return false // Conversion successful, it's an integer
        } catch (e: NumberFormatException) {
            return true // Conversion failed, not an integer
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VerseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}