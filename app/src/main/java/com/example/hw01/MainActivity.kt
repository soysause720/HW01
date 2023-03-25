package com.example.hw01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameText = findViewById<TextView>(R.id.nameText)
        val sexText  = findViewById<TextView>(R.id.sexText)
        val sizeText = findViewById<TextView>(R.id.sizeText)
        val colorText= findViewById<TextView>(R.id.colorText)
        val button1  = findViewById<Button>(R.id.sendButton1)

        button1.setOnClickListener {
            if(nameText.text.length == 0 ||sexText.text.length == 0){
                Toast.makeText(this,"尚有未輸入欄位",Toast.LENGTH_SHORT).show()
            }else{
                //Bundle 打包資料  藉由putExtras()
                val bundle = Bundle()
                bundle.putString("name","${nameText.text}")
                bundle.putString("sex","${sexText.text}")
                bundle.putString("size","${sizeText.text}")
                bundle.putString("color","${colorText.text}")

                val intent =Intent(this,SubActivity::class.java)
                intent.putExtras(bundle)
                startActivityForResult(intent,1)//step1 發送intent
            }
        }
    }

    //使用onActivityResult()方法來，取得返回資料
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //判斷Intent不能為空，檢查資料是否有數值
        data?.extras?.let {
            findViewById<TextView>(R.id.size).visibility = View.VISIBLE
            findViewById<TextView>(R.id.color).visibility = View.VISIBLE
            findViewById<TextView>(R.id.sizeText).visibility = View.VISIBLE
            findViewById<TextView>(R.id.colorText).visibility = View.VISIBLE

            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                //show返回資料
                findViewById<TextView>(R.id.nameText).text ="${it.getString("name")}"
                findViewById<TextView>(R.id.sexText).text ="${it.getString("sex")}"
                findViewById<TextView>(R.id.sizeText).text ="${it.getString("size")}"
                findViewById<TextView>(R.id.colorText).text ="${it.getString("color")}"
            }
        }
    }
}