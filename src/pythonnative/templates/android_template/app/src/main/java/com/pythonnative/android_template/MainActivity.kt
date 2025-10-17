package com.pythonnative.android_template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.chaquo.python.Python
import com.chaquo.python.PyObject
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private var page: PyObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")

        // Initialize Chaquopy
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }
        try {
            val py = Python.getInstance()
            // Instantiate MainPage directly and call on_create
            val module = py.getModule("app.main_page")
            val pageClass = module.get("MainPage")
            page = pageClass?.call(this)
            page?.callAttr("on_create")
        } catch (e: Exception) {
            Log.e("PythonNative", "Python bootstrap failed", e)
            // Fallback: show a simple native label if Python bootstrap fails
            val tv = TextView(this)
            tv.text = "Hello from PythonNative (Android template)"
            setContentView(tv)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
        try { page?.callAttr("on_start") } catch (e: Exception) { Log.w(TAG, "on_start failed", e) }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
        try { page?.callAttr("on_resume") } catch (e: Exception) { Log.w(TAG, "on_resume failed", e) }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
        try { page?.callAttr("on_pause") } catch (e: Exception) { Log.w(TAG, "on_pause failed", e) }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
        try { page?.callAttr("on_stop") } catch (e: Exception) { Log.w(TAG, "on_stop failed", e) }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
        try { page?.callAttr("on_destroy") } catch (e: Exception) { Log.w(TAG, "on_destroy failed", e) }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
        try { page?.callAttr("on_restart") } catch (e: Exception) { Log.w(TAG, "on_restart failed", e) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState() called")
        try { page?.callAttr("on_save_instance_state") } catch (e: Exception) { Log.w(TAG, "on_save_instance_state failed", e) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState() called")
        try { page?.callAttr("on_restore_instance_state") } catch (e: Exception) { Log.w(TAG, "on_restore_instance_state failed", e) }
    }
}