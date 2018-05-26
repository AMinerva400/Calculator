package com.anthonyminerva.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var num1 : Double = 0.0;
    var operation: Int = 0; // 1 - Add, 2 - Sub, 3 - Multiply, 4 - Divide

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val B1 = findViewById<Button>(R.id.button)
        B1.setOnClickListener(this)
        for(i in 2..20){
            val id: String = "button" + i
            val ResID = resources.getIdentifier(id, "id", packageName)
            val button: Button = findViewById(ResID)
            button.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        val screen = findViewById<TextView>(R.id.Screen)
        val buttonClicked: Button = v as Button
        val buttonText: String = buttonClicked.text as String
        var buttonNum: Int
        var screenNum: Double
        if(buttonText.matches(Regex("\\d+"))){
            buttonNum = Integer.parseInt(buttonText)
        }
        else{
            buttonNum = -1
        }
        if((screen.text as String).endsWith(".")){
            val screenText = (screen.text as String) + "0"
            screenNum = screenText.toDouble()
        }
        else{
            val screenText = screen.text as String
            screenNum = screenText.toDouble()
        }
        when{
            buttonNum in 0..9 -> {
                if(screenNum == 0.0 && screenNum != null){
                    screen.text = buttonNum.toString()
                }
                else{
                    screen.text = (screen.text.toString()) + (buttonNum.toString())
                }
            }
            buttonText == "CLEAR" -> {
                screen.text = "0"
                num1 = 0.0
                operation = 0
            }
            buttonText == "+" -> {
                if(operation == 0){
                    num1 = screenNum
                    operation = 1
                    screen.text = "0"
                }
            }
            buttonText == "-" -> {
                if(operation == 0){
                    num1 = screenNum
                    operation = 2
                    screen.text = "0"
                }
            }
            buttonText == "*" -> {
                if(operation == 0){
                    num1 = screenNum
                    operation = 3
                    screen.text = "0"
                }
            }
            buttonText == "/" -> {
                if(operation == 0){
                    num1 = screenNum
                    operation = 4
                    screen.text = "0"
                }
            }
            buttonText == "." -> {
                if(!screen.text.contains(".")){
                    screen.text = (screen.text.toString()) + "."
                }
            }
            buttonText == "=" -> {
                if(operation != 0){
                    val num2: Double = (screen.text as String).toDouble()
                    when{
                        operation == 1 -> {
                            screen.text = (num1+num2).toString()
                        }
                        operation == 2 -> {
                            screen.text = (num1-num2).toString()
                        }
                        operation == 3 -> {
                            screen.text = (num1*num2).toString()
                        }
                        operation == 4 -> {
                            if(num2 != 0.0){
                                screen.text = (num1/num2).toString()
                            }
                        }
                    }
                }
            }
        }
    }
}
