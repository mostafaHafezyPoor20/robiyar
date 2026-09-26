package mostafa.hafezypoor.robiyar.ui.account

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import mostafa.hafezypoor.robiyar.R

class FAccount : Fragment(R.layout.faccount) , TextWatcher {
    private val viewModel: AccountViewModel by viewModels()
    private lateinit var token: String
    private lateinit var name : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var saveChanges : MaterialButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        token = context.getSharedPreferences("save",MODE_PRIVATE).getString("token","null") ?: "null"
        name = view.findViewById<TextInputEditText>(R.id.name)
        password = view.findViewById<TextInputEditText>(R.id.password)
        saveChanges = view.findViewById<MaterialButton>(R.id.saveChanges)

        name.setOnClickListener {
            password.setText("")
        }
        password.setOnClickListener {
            password.setText("")
        }

        name.addTextChangedListener(this)
        password.addTextChangedListener(this)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getDetailAccount(token)
                viewModel.account_state.collect {state ->
                    when(state){
                        AccountState.Idle -> {}
                        AccountState.Loading -> {}
                        is AccountState.Success -> {
                            name.setText(state.modelDetailAccount.name)
                        }
                        is AccountState.Error -> {}
                    }
                }
            }
        }
        saveChanges.setOnClickListener {
           if (name.text.toString().trim().isEmpty()){
               name.error = "نام نمی تواند خالی باشد"
           }else if (password.text.toString().trim().isEmpty()){
               password.error = "کلمه عبور نمیتواند خالی باشد"
           }else{
                  viewLifecycleOwner.lifecycleScope.launch {
                      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                          viewModel.updateDetailUser(token,password.text.toString().trim(),name.text.toString().trim())
                          viewModel.Update_AccountState.collect {state ->
                              when(state){
                                  UpdateAccountState.Idle -> {}
                                  UpdateAccountState.Loading -> {}
                                  is UpdateAccountState.Success -> {
                                      if (state.response.equals("200")){
                                          saveChanges.visibility = View.GONE
                                          Toast.makeText(activity,"ذخیره شد", Toast.LENGTH_LONG).show()
                                      }
                                  }
                                  is UpdateAccountState.Error -> {}
                              }
                          }
                      }
                  }
           }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (!name.text.toString().trim().isEmpty() || !password.text.toString().trim().isEmpty()){
            saveChanges.visibility = View.VISIBLE
        }

    }

    override fun afterTextChanged(p0: Editable?) {

    }
}