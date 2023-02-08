package com.ahmetcanerol.roomdb.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ahmetcanerol.roomdb.R
import com.ahmetcanerol.roomdb.model.User
import com.ahmetcanerol.roomdb.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText

class AddFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val editName = view.findViewById<EditText>(R.id.username)
        val editAge = view.findViewById<EditText>(R.id.userage)
        val insertButton = view.findViewById<Button>(R.id.eklebtn)
        val guncelle=view.findViewById<Button>(R.id.update)
        val sil=view.findViewById<Button>(R.id.deletebtn)
        var id=arguments?.getInt("id")
        var username=arguments?.getString("name")
        var year=arguments?.getInt("year")

        var txt=Editable.Factory.getInstance().newEditable(username)
        editName.text=txt
        var txt1=Editable.Factory.getInstance().newEditable(year.toString())
        editAge.text=txt1

        insertButton.setOnClickListener {
            val user = User(0, editName.text.toString(), editAge.text.toString().toInt())
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Kullanıcı Eklendi!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        guncelle.setOnClickListener {
            val user = id?.let { it1 -> User(it1,editName.text.toString(), editAge.text.toString().toInt()) }
            if (user != null) {
                userViewModel.updateUser(user)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }

        sil.setOnClickListener {
            if (id != null) {
                userViewModel.deleteId(id)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }




        return view
    }

}