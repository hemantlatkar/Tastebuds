package com.example.tastebuds.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.tastebuds.R

object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
               capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    fun isPoorConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return true
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return true

        return !(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }


    fun showErrorMessage(context: Context,message :String) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        val textView: TextView = layout.findViewById(R.id.toast_text)
        textView.text = message
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
        toast.show()
    }
}
