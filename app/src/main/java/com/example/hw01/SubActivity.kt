package com.example.hw01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val size  = findViewById<TextView>(R.id.sizeText0)
        val color = findViewById<TextView>(R.id.colorText0)
        val button2   = findViewById<Button>(R.id.sendButton2)

        button2.setOnClickListener {
            if(size.text.length==0 || color.text.length==0){
                Toast.makeText(this,"尚有未輸入欄位", Toast.LENGTH_SHORT).show()
            }else{
                intent?.extras?.let {
                    val name = it.getString("name")
                    val sex = it.getString("sex")

                    val bundle = Bundle()
                    bundle.putString("name", "${name}")
                    bundle.putString("sex", "${sex}")
                    bundle.putString("size", "${size.text}")
                    bundle.putString("color", "${color.text}")
                    val intent = Intent().putExtras(bundle)
                    setResult(Activity.RESULT_OK, intent.putExtras(bundle))
                    finish()//結束自己週期
                }
            }
        }
    }
}