package com.maximeferrierdev.testwatch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.wear.widget.CircularProgressLayout
import android.support.wearable.activity.ConfirmationActivity
import android.support.wearable.activity.WearableActivity
import android.view.View

class ConfirmationActivationActivity : WearableActivity(), CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    private var mCircularProgress: CircularProgressLayout? = null
    private val ACTIVATION_REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_activation)

        mCircularProgress = findViewById<CircularProgressLayout>(R.id.circular_progress).apply {
            onTimerFinishedListener = this@ConfirmationActivationActivity
            setOnClickListener(this@ConfirmationActivationActivity)
        }

        mCircularProgress?.apply {
            totalTime = 5000
            startTimer()
        }
    }

    override fun onTimerFinished(p0: CircularProgressLayout?) {
        val intent = Intent(this, ConfirmationActivity::class.java).apply {
            putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE, ConfirmationActivity.SUCCESS_ANIMATION)
            putExtra(ConfirmationActivity.EXTRA_MESSAGE, getString(R.string.message_success_activation))
        }
        startActivity(intent)

        val intentBack : Intent = Intent()
        setResult(Activity.RESULT_OK, intentBack)
        finish()

    }

    override fun onClick(v: View?) {
        mCircularProgress?.stopTimer()
        val intentBack : Intent = Intent()
        setResult(Activity.RESULT_CANCELED, intentBack)
        finish()
    }
}
