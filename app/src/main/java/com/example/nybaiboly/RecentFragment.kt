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
 * Use the [RecentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var textView: TextView
    private lateinit var recentAdapter: RecentAdapter
    private lateinit var recentList: ArrayList<Recent>

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewRecent)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recentList = ArrayList()
        recentAdapter = RecentAdapter(requireContext(),recentList)
        recyclerView.adapter = recentAdapter
        val databaseHelperLocal = DatabaseHelperLocal.getInstance(requireContext())
        recentList.addAll(databaseHelperLocal.getAllData())
        recentAdapter.notifyDataSetChanged()

        val element = DataSample.retrievePreferences(requireContext())

        if (element.third == "Malagasy"){
            view.findViewById<TextView>(R.id.textView9).text = "Novakina farany"
        }
        else if( element.third == "Français"){
            view.findViewById<TextView>(R.id.textView9).text = "Recent lecture"
        }
        else {
            view.findViewById<TextView>(R.id.textView9).text = "Recent reading"
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}