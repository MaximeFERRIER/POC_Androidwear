package com.maximeferrierdev.testwatch

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import com.maximeferrierdev.testwatch.Modeles.Bateau
import kotlinx.android.synthetic.main.activity_detail_bateau.*

class DetailBateauActivity : WearableActivity() {

    private var bateau : Bateau ?= null
    private val ACTIVATION_REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bateau)

        if(intent != null) {
            bateau = intent.getSerializableExtra("obj_bateau") as Bateau?
        }

        if(bateau != null) {
            tv_nom_bateau.text = bateau?.nom
            tv_type_bateau.text = bateau?.type
            if(bateau?.nombreJoursrestants!! > 0) {
                layout_activation_journee.visibility = View.VISIBLE
                layout_all_activated.visibility = View.GONE
                tv_jours_restants.text = getString(R.string.jours_restant, bateau?.nombreJoursrestants.toString())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    pb_gauge.setProgress(bateau?.nombreJoursrestants!!, true)
                } else {
                    pb_gauge.visibility = View.GONE
                }

                btn_activer.setOnClickListener {
                    var intent : Intent = Intent(this, ConfirmationActivationActivity::class.java)
                    startActivityForResult(intent, ACTIVATION_REQUEST_CODE)
                }
            } else {
                layout_activation_journee.visibility = View.GONE
                layout_all_activated.visibility = View.VISIBLE
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == ACTIVATION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                bateau?.nombreJoursrestants = bateau?.nombreJoursrestants!!.minus(1)
                if(bateau?.nombreJoursrestants!! > 0) {
                    tv_jours_restants.text = getString(R.string.jours_restant, bateau?.nombreJoursrestants.toString())
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        pb_gauge.setProgress(bateau?.nombreJoursrestants!!, true)
                    }
                } else {
                    layout_activation_journee.visibility = View.GONE
                    layout_all_activated.visibility = View.VISIBLE
                }
            }
        }
    }
}
