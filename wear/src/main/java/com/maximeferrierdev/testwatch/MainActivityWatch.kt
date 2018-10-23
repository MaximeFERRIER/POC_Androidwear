package com.maximeferrierdev.testwatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wearable.activity.WearableActivity
import com.maximeferrierdev.testwatch.Adapters.ListeBateauxAdapter
import com.maximeferrierdev.testwatch.Modeles.Bateau
import com.maximeferrierdev.testwatch.Modeles.NavigationListener
import kotlinx.android.synthetic.main.activity_main_watch.*

class MainActivityWatch : WearableActivity() {

    private var listeBateauxAdapter : ListeBateauxAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_watch)

        rv_liste_bateaux.apply {
            isEdgeItemsCenteringEnabled = true //Active le défilement en suivant la courbe des montres rondes
            layoutManager = WearableLinearLayoutManager(this@MainActivityWatch)
        }

        listeBateauxAdapter = ListeBateauxAdapter(this, getListeBateaux()!!, getNavigationListener(this))
        rv_liste_bateaux.adapter = listeBateauxAdapter

        // Enables Always-on
        setAmbientEnabled()
    }

    private fun getListeBateaux() : ArrayList<Bateau>? {
        var listeBateaux : ArrayList<Bateau> ?= ArrayList()
        listeBateaux?.add(Bateau("Le Roger Rabbit", "Yacht", 17))
        listeBateaux?.add(Bateau("Lucky Luke", "Solitaire", 9))
        listeBateaux?.add(Bateau("Poséidon", "Trimaran", 12))
        listeBateaux?.add(Bateau("Croustibat", "Vedette", 1))
        return listeBateaux
    }

    private fun getNavigationListener(context : Context) : NavigationListener {
        return object : NavigationListener {
            override fun accesDetailBateau(bateau: Bateau) {
                var intent = Intent(context, DetailBateauActivity::class.java)
                intent.putExtra("obj_bateau", bateau)
                startActivity(intent)
            }
        }
    }
}
