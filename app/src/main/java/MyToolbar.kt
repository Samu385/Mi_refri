import androidx.appcompat.app.AppCompatActivity
import com.example.mirefri.R

class MyToolbar {
    fun Show(activities: AppCompatActivity, titulo : String){

        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
        activities.supportActionBar?.title = titulo

        activities.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}