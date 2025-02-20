package com.example.tastebuds.utils

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import androidx.test.platform.app.InstrumentationRegistry

class CustomTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        disableAnimations()
    }

    private fun disableAnimations() {
        val uiAutomation = InstrumentationRegistry.getInstrumentation().uiAutomation
        uiAutomation.executeShellCommand("settings put global window_animation_scale 0")
        uiAutomation.executeShellCommand("settings put global transition_animation_scale 0")
        uiAutomation.executeShellCommand("settings put global animator_duration_scale 0")
    }
}