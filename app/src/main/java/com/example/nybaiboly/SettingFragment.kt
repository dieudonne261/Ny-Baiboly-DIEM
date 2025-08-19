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
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val languages = arrayOf("Malagasy", "Français", "English")
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        var textViewTest = view.findViewById<TextView>(R.id.textViewTest)
        var languageSelectText = view.findViewById<Spinner>(R.id.language)
        val element = DataSample.retrievePreferences(requireContext())


        languerChange()
        seekBar.progress = element.fourth
        textViewTest.textSize = if (element.fourth == 0) 1F else (element.fourth * 5).toFloat()

        val index = languages.indexOf(element.third)
        if (index != -1) {
            languageSelectText.setSelection(index)
        }

        super.onViewCreated(view, savedInstanceState)
        val switchMaterial = view.findViewById<SwitchMaterial>(R.id.switch1)

        switchMaterial.isChecked = element.first

        switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            DataSample.savePreferences(requireContext(), !element.first,true,languageSelectText.selectedItem.toString(),seekBar.progress)
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }


        languageSelectText.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                DataSample.savePreferences(requireContext(), element.first,false,languageSelectText.selectedItem.toString(),seekBar.progress)
                languerChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val minValue = 1
                val textSize = if (progress == 0) minValue else progress * 5
                textViewTest.textSize = textSize.toFloat()
                DataSample.savePreferences(requireContext(), element.first,false,languageSelectText.selectedItem.toString(),progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })



        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        textVerse = view.findViewById(R.id.textViewTest)
        chapitreList = ArrayList()
        databaseHelper.open()
        chapitreList.addAll(databaseHelper.getVerseData(1,"1"))
        databaseHelper.close()
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

    fun languerChange(){
        val element = DataSample.retrievePreferences(requireContext())
        if (element.third == "Malagasy"){
            view?.findViewById<TextView>(R.id.textView2)?.text = "Maody maizina"
            view?.findViewById<TextView>(R.id.textView3)?.text = "Fiteny"
            view?.findViewById<TextView>(R.id.textView4)?.text = "Haben'ny endritsoratra"
            view?.findViewById<TextView>(R.id.textView19)?.text = "Fikirana"
        }
        else if( element.third == "Français"){
            view?.findViewById<TextView>(R.id.textView2)?.text = "Mode sombre"
            view?.findViewById<TextView>(R.id.textView3)?.text = "Langage"
            view?.findViewById<TextView>(R.id.textView4)?.text = "Taille du police"
            view?.findViewById<TextView>(R.id.textView19)?.text = "Paramètre"
        }
        else {
            view?.findViewById<TextView>(R.id.textView2)?.text = "Dark mode"
            view?.findViewById<TextView>(R.id.textView3)?.text = "Language"
            view?.findViewById<TextView>(R.id.textView4)?.text = "Font size"
            view?.findViewById<TextView>(R.id.textView19)?.text = "Setting"
        }
    }





    companion object {
        const val IS_SETTING = "isSetting"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}