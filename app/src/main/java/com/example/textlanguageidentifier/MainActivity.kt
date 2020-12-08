package com.example.textlanguageidentifier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEditText = findViewById<EditText>(R.id.inputString);
        val resultLanguage = findViewById<TextView>(R.id.result);

        val checkLanguage = findViewById<Button>(R.id.checkLanguage);
        checkLanguage.setOnClickListener {
            val languageIdentifier = LanguageIdentification.getClient()
            languageIdentifier.identifyLanguage(inputEditText.text.toString())
                .addOnSuccessListener { languageCode ->
                    if (languageCode == "und") {
                        Toast.makeText(baseContext, "Can't identify language", Toast.LENGTH_SHORT).show();
                    } else {
                        when(languageCode){
                            "en" -> resultLanguage.text = "The Identified language is English"
                            "ru" -> resultLanguage.text = "The Identified language is Russian"
                            "ar" -> resultLanguage.text = "The Identified language is Arabic"
                        }
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext, "Can't identify language", Toast.LENGTH_SHORT).show();
                }
        }
    }
}