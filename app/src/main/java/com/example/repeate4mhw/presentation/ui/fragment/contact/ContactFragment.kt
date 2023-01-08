package com.example.repeate4mhw.presentation.ui.fragment.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.repeate4mhw.R
import com.example.repeate4mhw.base.BaseFragment
import com.example.repeate4mhw.databinding.FragmentContactBinding
import com.example.repeate4mhw.model.ContactModel

class ContactFragment : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate),ContactAdapter.Clicklistener {
    private var arrayContacts = arrayListOf<ContactModel>()
    private lateinit var adapter: ContactAdapter


    companion object {
        const val READ_CONTACTS = Manifest.permission.READ_CONTACTS
        const val PERMISSION_REQUEST = 200
    }

    override fun setupUI() {
        initContacts()

        adapter = ContactAdapter(this, arrayContacts)
        binding.rvContacts.adapter = adapter
    }

    @SuppressLint("Range")
    private fun initContacts() {
        if (checkPermission(READ_CONTACTS)) {
            val cursor = requireContext().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )

            cursor?.let {
                while (it.moveToNext()) {
                    val fullName = it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val number = it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    val newModel = ContactModel()
                    newModel.name = fullName
                    newModel.number = number
                    arrayContacts.add(newModel)
                }
            }
            cursor?.close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(requireContext(), READ_CONTACTS)
            == PackageManager.PERMISSION_GRANTED) {
            initContacts()
        }
    }

    private fun checkPermission(permission: String) : Boolean {
        return if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),
                PERMISSION_REQUEST
            )
            initContacts()
            false
        } else true
    }



    //Call Contact Button Listener
    fun callListener(position: Int) {
        val number = arrayContacts[position].number.toString()
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        try {
            startActivity(intent)
        } catch (e : Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    // Whatsapp Contact Button Listener
    override fun waListener(position: Int) {
        val number = arrayContacts[position].number.toString()
        val intent = Intent(Intent.ACTION_VIEW)
//        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$number")
        intent.setPackage("com.whatsapp")

        try {
            startActivity(intent)
        } catch (e : Exception) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
            Log.e("ololo", e.message.toString())
        }
    }

}


}