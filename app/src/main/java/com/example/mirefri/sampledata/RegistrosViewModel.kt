import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirefri.sampledata.RegistrosBD
import com.example.mirefri.sampledata.RegistrosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class RegistrosViewModel(private val registrosDao: RegistrosDao) : ViewModel() {

    fun insertarRegistro(nombre:String, descripcion:String, dineroGastado:Int) {
        val nuevoRegistro = RegistrosBD(
            nombre = nombre,
            descripcion = descripcion,
            dineroGastado = dineroGastado,
            fecha = Date().toString()
        )

        viewModelScope.launch(Dispatchers.IO) {
            registrosDao.insert(nuevoRegistro)
        }
    }
}
