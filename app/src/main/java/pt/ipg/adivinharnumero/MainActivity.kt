package pt.ipg.adivinharnumero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val textInputEditIdade = findViewById<EditText>(R.id.EditTextNumero)
    val NumeroCerto = 5


}