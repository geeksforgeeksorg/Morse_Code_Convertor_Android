package org.geeksforgeeks.demo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var outputEditText: EditText
    private lateinit var encodeButton: Button
    private lateinit var decodeButton: Button
    private lateinit var clearButton: Button

    // map letters and numbers to morse codes
    private val map = mapOf(
        'A' to ".-",    'B' to "-...",  'C' to "-.-.",  'D' to "-..",   'E' to ".",
        'F' to "..-.",  'G' to "--.",   'H' to "....",  'I' to "..",    'J' to ".---",
        'K' to "-.-",   'L' to ".-..",  'M' to "--",    'N' to "-.",    'O' to "---",
        'P' to ".--.",  'Q' to "--.-",  'R' to ".-.",   'S' to "...",   'T' to "-",
        'U' to "..-",   'V' to "...-",  'W' to ".--",   'X' to "-..-",  'Y' to "-.--",
        'Z' to "--..",  '0' to "-----", '1' to ".----", '2' to "..---", '3' to "...--",
        '4' to "....-", '5' to ".....", '6' to "-....", '7' to "--...", '8' to "---..",
        '9' to "----.", ' ' to "/"
    )

    // reverse map for decoding
    private val reverseMap = map.entries.associate { (key, value) ->
        value to key
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEditText = findViewById(R.id.inputEditText)
        outputEditText = findViewById(R.id.outputEditText)
        encodeButton = findViewById(R.id.encode)
        decodeButton = findViewById(R.id.decode)
        clearButton = findViewById(R.id.clear)

        encodeButton.setOnClickListener {
            encodeText()
        }
        decodeButton.setOnClickListener {
            decodeText()
        }
        clearButton.setOnClickListener {
            clearText()
        }
    }

    // converts the input text into Morse code
    private fun encodeText() {
        // get user input and convert to uppercase for consistency
        val inputText = inputEditText.text.toString().uppercase()

        // map letter by letter and convert to morse code
        val encodedText = inputText.mapNotNull {
            map[it]
        }.joinToString(" ")

        // display output
        outputEditText.setText(encodedText)
    }

    // decode morse code to normal text
    private fun decodeText() {
        // get user input and remove extra spaces
        val inputText = inputEditText.text.toString().trim()

        // split Morse code by spaces (each Morse letter is separated by a space)
        val decodedText = inputText.split(" ")
            // convert Morse code back to letters
            .mapNotNull {
                reverseMap[it]
            }.joinToString("")

        // display output
        outputEditText.setText(decodedText)
    }

    // clear all fields
    private fun clearText() {
        inputEditText.text.clear()
        outputEditText.text.clear()
    }
}