package com.example.nybaiboly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChapitreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChapitreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var textView: TextView
    private lateinit var chapitreAdapter: ChapitreAdapter
    private lateinit var chapitreList: ArrayList<ChapitreEtVerse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idlivre = arguments?.getInt("id_livre", 0) ?: 0
        recyclerView = view.findViewById(R.id.recyclerViewChapitre)
        textView = view.findViewById(R.id.textView6)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        chapitreList = ArrayList()
        chapitreAdapter = ChapitreAdapter(requireContext(),chapitreList)
        recyclerView.adapter = chapitreAdapter
        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        databaseHelper.open()
        chapitreList.addAll(databaseHelper.getChapitreData(idlivre))
        chapitreAdapter.notifyDataSetChanged()
        textView.text = databaseHelper.getData("b_name","ci_diem_boky",idlivre)
        databaseHelper.close()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chapitre, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LivreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChapitreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}