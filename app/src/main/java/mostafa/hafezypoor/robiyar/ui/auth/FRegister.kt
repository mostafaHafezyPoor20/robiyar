package mostafa.hafezypoor.robiyar.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.R
import mostafa.hafezypoor.robiyar.data.model.ModelRegister

class FRegister(private val iEventRegister: IEventRegister) : Fragment(R.layout.fregister) {
    private val viewModel:RegisterViewModel by viewModels()
    private lateinit var btnLogin: MaterialButton
    private lateinit var btnRegister: MaterialButton
    private lateinit var name: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = view.findViewById<MaterialButton>(R.id.btnLogin)
        btnRegister = view.findViewById<MaterialButton>(R.id.btnRegister)
        name = view.findViewById<TextInputEditText>(R.id.name)
        username = view.findViewById<TextInputEditText>(R.id.username)
        password = view.findViewById<TextInputEditText>(R.id.password)
        btnRegister.setOnClickListener {
            if (name.text.toString().trim().isEmpty()){
                name.setError("نام نمیتواند خالی باشد")
            }else if (username.text.toString().trim().isEmpty()){
                username.setError("نام کاربری نمیتواند خالی باشد")
            }else if (password.text.toString().trim().isEmpty()){
                password.setError("کلمه عبور نمیتواند خالی باشد")
            }else{
                viewModel.register(name.text.toString(),username.text.toString(),password.text.toString())
            }
        }
        btnLogin.setOnClickListener{
            iEventRegister.onLoginClick()
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.registerState.collect { state ->
                    when(state){
                        RegisterState.Idle -> {}
                        RegisterState.Loading -> {}
                        is RegisterState.Success -> {
                            if (state.response.status.equals("userExist")){
                                username.setError("نام کاربری وجود دارد! نام کاربری دیگری انتخاب کنید")
                            }else{
                                iEventRegister.onRegister(state.response)
                            }

                        }
                        is RegisterState.Error -> {
                            name.setText(state.message)
                        }
                    }
                }
            }
        }
    }
}
interface IEventRegister {
    fun onLoginClick()
    fun onRegister(status: ModelRegister)
}