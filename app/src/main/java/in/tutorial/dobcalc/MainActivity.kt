package `in`.tutorial.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var tvSelDate:TextView? = null;
    var minData:TextView? = null;
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn:Button = findViewById<Button>(R.id.btn2);
        tvSelDate = findViewById<TextView>(R.id.tvSelectedDate);
        minData = findViewById<TextView>(R.id.minData);
        btn.setOnClickListener{
            clickedBtn()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickedBtn(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR);
        val month = myCalendar.get(Calendar.MONTH);
        val day = myCalendar.get(Calendar.DAY_OF_MONTH);
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
                tvSelDate?.text = "$dayOfMonth/${month+1}/$year";
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse("$dayOfMonth/${month+1}/$year");
                theDate?.let {
                    val selectDInMin = theDate.time/60000;
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val diff = ((currentDate.time/60000) - selectDInMin)/60
                    Toast.makeText(this, "Datepicker not wo $diff", Toast.LENGTH_LONG).show()
                    minData?.text  = diff.toString();
                }
            }, year, month, day )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}