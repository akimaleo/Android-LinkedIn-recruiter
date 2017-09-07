package com.letit0or1.akimaleo.linkedinrecruiter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.letit0or1.akimaleo.linkedinrecruiter.storage.UserUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.io.IOException
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    var cookie: Map<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginInput.setText(UserUtil.instance.login)
        passwordInput.setText(UserUtil.instance.password)

        signIn.setOnClickListener {
            val loginSt = loginInput.text.toString()
            val passStr = passwordInput.text.toString()
            UserUtil.instance.saveData(loginSt, passStr)

            thread {
                authorization(loginSt, passStr)
            }
        }

    }

    fun authorization(login: String, password: String) {
        try {

            val url = "https://www.linkedin.com/uas/login?goback=&trk=hb_signin"
            var response = Jsoup
                    .connect(url)
                    .method(Connection.Method.GET)
                    .execute()

            val responseDocument = response.parse()
            val loginCsrfParam = responseDocument
                    .select("input[name=loginCsrfParam]")
                    .first()
            cookie = response.cookies()
            response = Jsoup.connect("https://www.linkedin.com/uas/login-submit")
                    .cookies(response.cookies())
                    .data("loginCsrfParam", loginCsrfParam.attr("value"))
                    .data("session_key", login)
                    .data("session_password", password)
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute()

            val document = response.parse()

            System.out.println("Welcome " + document.select(".act-set-name-split-link").html())
            addConnection()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun addConnection() {
        try {

            val url = "https://www.linkedin.com/mynetwork/"
            var response = Jsoup
                    .connect(url)
                    .method(Connection.Method.GET)
                    .execute()

            val responseDocument = response.parse()
            val loginCsrfParam = responseDocument
                    .select("input[name=loginCsrfParam]")
                    .first()

            response = Jsoup.connect("https://www.linkedin.com/uas/login-submit")
                    .cookies(response.cookies())
                    .data("loginCsrfParam", loginCsrfParam.attr("value"))
//                    .data("session_key", login)
//                    .data("session_password", password)
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute()

            val document = response.parse()

            System.out.println("Welcome " + document.select(".act-set-name-split-link").html())

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

