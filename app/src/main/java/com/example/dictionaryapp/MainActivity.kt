package com.example.dictionaryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    lateinit var edtText:EditText
    lateinit var searchBtn :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtText = findViewById(R.id.edt_word)
        searchBtn = findViewById(R.id.btn_search)


        val queue = Volley.newRequestQueue(this)

        searchBtn.setOnClickListener {

            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url,
                {response ->
                    try {
                        extractDefinitionFromJson(response)
                    }catch (exception : Exception){
                        exception.printStackTrace()
                    }
                },
                {error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringRequest)
        }
    }

    private fun getUrl() : String{
        val word = edtText.text
        val apiKey = "b87b1b7d-d9a3-49ec-97bc-e858229e2084"
        val url =
            "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

        return url
    }

    private fun extractDefinitionFromJson(response : String){

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, WordDefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)

    }
}