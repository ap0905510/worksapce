package com.yw.kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView

inline fun <reified TV> v(context: Context, init: TV.() -> Unit): TV {
    val conStr = TV::class.java.getConstructor(Context::class.java)
    val view = conStr.newInstance(context)
    view.init()
    return view
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: (TextView) = findViewById(R.id.tv) as TextView
        tv.setText("my first kotlin")

        val view = v<TextView>(this) {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            text = "Hello kotlin"
        }

        addContentView(view, view.layoutParams)

        val util = utils()
        util.print("YW")

        Classify.cal()

        test()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

fun test() {
    val a: Int = 1000
    print(a == a)
    Log.e("YW", "print: " + (a === a))
    val boxedA: Int? = a
    val anotherboxedA: Int? = a
    print(boxedA == anotherboxedA)
    Log.e("YW", "print: " + (boxedA == anotherboxedA))

}
