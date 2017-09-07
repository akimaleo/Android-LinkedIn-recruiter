package com.letit0or1.akimaleo.linkedinrecruiter

import com.letit0or1.akimaleo.linkedinrecruiter.storage.UserUtil

/**
 * Created by akimaleo on 07.09.17.
 */

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        UserUtil.instance.setContext(applicationContext)
    }
}
