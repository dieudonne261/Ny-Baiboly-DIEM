package com.example.nybaiboly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var search: TextInputEditText
    private lateinit var testamentAdapter: TestamentAdapter
    private lateinit var testamentList: ArrayList<Testament>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewTestament)
        search = view.findViewById(R.id.rechtxt)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        testamentList = ArrayList()
        testamentAdapter = TestamentAdapter(requireContext(),testamentList)
        recyclerView.adapter = testamentAdapter

        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        databaseHelper.open()
        testamentList.addAll(databaseHelper.getTestamentData())
        testamentAdapter.notifyDataSetChanged()
        databaseHelper.close()

        val element = DataSample.retrievePreferences(requireContext())

        if (element.third == "Malagasy"){
            search.hint = "Hitady"
            view.findViewById<TextView>(R.id.textView20).text = "Tongasoa"
        }
        else if( element.third == "Français"){
            view.findViewById<TextView>(R.id.textView20).text = "Bienvenue"
            search.hint = "Rechercher"
        }
        else {
            view.findViewById<TextView>(R.id.textView20).text = "Welcome"
            search.hint = "Search"
        }

        view.findViewById<ImageButton>(R.id.searchbtn).setOnClickListener {
            val bundle = Bundle()

            bundle.putString("search",
                view.findViewById<TextInputEditText>(R.id.rechtxt).text.toString()
            )
            val searchFragment = SearchFragment()
            searchFragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, searchFragment)
                .addToBackStack(null)
                .commit()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}