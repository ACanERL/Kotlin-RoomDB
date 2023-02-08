package com.ahmetcanerol.roomdb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ahmetcanerol.roomdb.R
import com.ahmetcanerol.roomdb.fragment.ListFragmentDirections
import com.ahmetcanerol.roomdb.model.User
import com.ahmetcanerol.roomdb.viewmodel.UserViewModel

class RecyclerViewAdapter() :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var userList = emptyList<User>()
    var onItemClick: ((User) -> Unit)? = null
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
      val userN=view.findViewById<TextView>(R.id.userNametxt)
        val userAge=view.findViewById<TextView>(R.id.userAgetxt)
        val delete=view.findViewById<ImageView>(R.id.delete)

        fun bind(user:User){
            userN.text = user.username
            userAge.text = user.year.toString()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        val currentUser= userList[position]
        holder.bind(currentUser)
       /*holder.itemView.setOnClickListener {
           onItemClick?.invoke(currentUser)
       }*/

        holder.userN.setOnClickListener {
            onItemClick?.invoke(currentUser)
        }
        holder.delete.setOnClickListener {
            onItemClick?.invoke(currentUser)
        }
    }

    override fun getItemCount(): Int {
       return userList.size
    }
    fun setData(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}