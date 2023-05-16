package com.example.controlarterial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.controlarterial.R
import com.example.controlarterial.entity.TomaArterial
import kotlinx.coroutines.CoroutineScope

class TomaArterialAdapter(context: Context, ubicaciones: List<TomaArterial>) :
    ArrayAdapter<TomaArterial>(context, 0, ubicaciones) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val tomas = getItem(position)

        val distolicaTextView = view!!.findViewById<TextView>(R.id.distolica)
        val sistolicaTextView = view.findViewById<TextView>(R.id.sistolica)
        val ritmoTextView = view.findViewById<TextView>(R.id.ritmo)

        distolicaTextView.text = tomas!!.distolica.toString()
        sistolicaTextView.text = tomas.sistolica.toString()
        ritmoTextView.text = tomas.ritmo.toString()

        return view
    }
}