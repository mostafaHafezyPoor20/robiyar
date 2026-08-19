package mostafa.hafezypoor.robiyar.ui.auth
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import mostafa.hafezypoor.robiyar.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.data.model.ModelLogin

class FLogin(private val IEventLogin: IEventLogin) : Fragment(R.layout.flogin){
private val viewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRegister=view.findViewById<MaterialButton>(R.id.btnRegister)
        val btnLogin=view.findViewById<MaterialButton>(R.id.btnLogin)
        val username=view.findViewById<TextInputEditText>(R.id.username)
        val password=view.findViewById<TextInputEditText>(R.id.password)
        btnLogin.setOnClickListener {
            if (username.text.toString().trim().isEmpty()){
                username.error="نام کاربری نمی تواند خالی باشد!"
            }else if (password.text.toString().trim().isEmpty()){
                password.error="کلمه عبور نمیتواند خالی باشد!"
            }else{
                viewModel.login(username.text.toString().trim(),password.text.toString().trim())
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.loginState.collect { state ->
                    when(state){
                        LoginState.Idle -> {

                        }
                        LoginState.Loading -> {

                        }
                        is LoginState.Success -> {
                            IEventLogin.onLogin(state.response)
                        }
                        is LoginState.Error -> {

                        }
                    }
                }
            }
        }
        btnRegister.setOnClickListener {
            IEventLogin.onRegisterClick()
        }
    }


}
interface IEventLogin {
    fun onRegisterClick()
    fun onLogin(status: ModelLogin)

}
