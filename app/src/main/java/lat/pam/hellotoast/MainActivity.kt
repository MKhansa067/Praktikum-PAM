package lat.pam.hellotoast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mShowCount = findViewById<TextView>(R.id.show_count)
        val buttonCountUp = findViewById<Button>(R.id.button_count)
        val buttonToast = findViewById<Button>(R.id.button_toast)
        val buttonSwitchPage = findViewById<Button>(R.id.button_switchpage)
        val buttonBrowser = findViewById<Button>(R.id.button_browser)
        val buttonContact = findViewById<Button>(R.id.button_contact)
        val buttonMap = findViewById<Button>(R.id.button_map)

        // Tombol Count: menambah angka
        buttonCountUp.setOnClickListener {
            mCount++
            Log.d("mCount", Integer.toString(mCount))
            if (mShowCount != null)
                mShowCount.text = mCount.toString()
        }

        // Tombol Toast: menampilkan pesan toast dengan angka saat ini
        buttonToast.setOnClickListener {
            val tulisan: String = mShowCount?.text.toString()
            val toast: Toast = Toast.makeText(this, "Angka yang dimunculkan $tulisan", Toast.LENGTH_LONG)
            toast.show()
        }

        // Explicit Intent: pindah ke halaman kedua (MainActivity2)
        buttonSwitchPage.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Implicit Intent: membuka browser ke Google
        buttonBrowser.setOnClickListener {
            val intentBrowse = Intent(Intent.ACTION_VIEW)
            intentBrowse.data = Uri.parse("https://www.google.com/")
            startActivity(intentBrowse)
        }

        // Implicit Intent: membuka aplikasi Kontak
        buttonContact.setOnClickListener {
            val intentContact = Intent(Intent.ACTION_VIEW)
            intentContact.data = Uri.parse("content://contacts/people/")
            startActivity(intentContact)
        }

        // Implicit Intent: membuka Google Maps
        buttonMap.setOnClickListener {
            val intentMap = Intent(Intent.ACTION_VIEW)
            intentMap.data = Uri.parse("geo:-7.2575,112.7521?q=Institut+Teknologi+Sepuluh+Nopember")
            startActivity(intentMap)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
