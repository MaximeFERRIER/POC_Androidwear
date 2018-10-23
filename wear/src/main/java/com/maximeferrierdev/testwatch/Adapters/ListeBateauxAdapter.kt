package com.maximeferrierdev.testwatch.Adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maximeferrierdev.testwatch.Modeles.Bateau
import com.maximeferrierdev.testwatch.Modeles.NavigationListener
import com.maximeferrierdev.testwatch.R

class ListeBateauxAdapter(
    private val context: Context,
    private val listeBateaux: ArrayList<Bateau>,
    private val navigationListener: NavigationListener
) : RecyclerView.Adapter<ListeBateauxAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bateau, parent, false))
    }

    override fun getItemCount(): Int {
        return listeBateaux.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listeBateaux[position].icone == null) {
            holder.icone?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_android))
        } else {
            holder.icone?.setImageDrawable(ContextCompat.getDrawable(context, listeBateaux[position].icone!!))
        }

        holder.nom?.text = listeBateaux[position].nom

        holder.item?.setOnClickListener {
            navigationListener.accesDetailBateau(listeBateaux[position])
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var nom : TextView ?= null
        var icone : ImageView ?= null
        var item : View ?= null

        init {
            nom = itemView.findViewById(R.id.tv_nom_bateau)
            icone = itemView.findViewById(R.id.img_icone_bateau)
            item = itemView.findViewById(R.id.layout_item_bateau)
        }
    }


}