package com.letit0or1.akimaleo.linkedinrecruiter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.letit0or1.akimaleo.linkedinrecruiter.storage.UserUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginInput.setText(UserUtil.instance.login)
        passwordInput.setText(UserUtil.instance.password)

        signIn.setOnClickListener {
            UserUtil.instance.saveData(loginInput.text.toString(), passwordInput.text.toString())

        }
    }
}
