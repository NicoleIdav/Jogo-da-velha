package com.example.jogodavelhamquinasimples

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class inicio : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        // Configura um listener de clique para o botão com ID 'buttonComeco'
        findViewById<View>(R.id.buttonComeco).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonComeco -> {
                // Se 'buttonComeco' for clicado, inicia 'MainActivity' com um extra indicando o modo de jogo
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("modoJogo", "pessoa")
                startActivity(intent)
            }
        }
    }
}

