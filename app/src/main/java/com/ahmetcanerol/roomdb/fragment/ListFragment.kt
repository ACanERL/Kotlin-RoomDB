package com.ahmetcanerol.roomdb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmetcanerol.roomdb.R
import com.ahmetcanerol.roomdb.adapter.RecyclerViewAdapter
import com.ahmetcanerol.roomdb.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val simpleCallback=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutposition=viewHolder.layoutPosition
            if(recyclerViewAdapter!=null){
                val select=recyclerViewAdapter!!.userList[layoutposition]
                userViewModel.deleteUser(select)
                recyclerViewAdapter!!.notifyDataSetChanged()
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val fabtn=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fabtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.listeRecyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter

        userViewModel.readAllData.observe(viewLifecycleOwner, Observer { userlist->
            recyclerViewAdapter.setData(userlist)
            if (userlist.isEmpty()) {
                Toast.makeText(context, "Liste Boş", Toast.LENGTH_SHORT).show()
            }

        })


        recyclerViewAdapter.onItemClick={
            userViewModel.deleteId(it.id)

        }

        recyclerViewAdapter.onItemClick={
            val bundle = bundleOf(
                "id" to it.id,
                "name" to it.username,
                "year" to it.year
            )
            findNavController().navigate(R.id.action_listFragment_to_addFragment,bundle)
        }


        return view
    }

}