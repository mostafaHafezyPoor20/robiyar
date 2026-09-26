package mostafa.hafezypoor.robiyar.ui.account

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import mostafa.hafezypoor.robiyar.data.model.ModelDetailAccount
import mostafa.hafezypoor.robiyar.data.remote.RetrofitInit
import mostafa.hafezypoor.robiyar.data.repository.AccountRepository

class AccountViewModel() : ViewModel(){
    private val accountRepository = AccountRepository(RetrofitInit.api)

    private val accountState = MutableStateFlow<AccountState> (AccountState.Idle)
    val account_state : StateFlow<AccountState> = accountState.asStateFlow()

    private val updateAccountState = MutableStateFlow<UpdateAccountState> (UpdateAccountState.Idle)
    val Update_AccountState : StateFlow<UpdateAccountState> = updateAccountState.asStateFlow()

   suspend fun getDetailAccount(token:String){
        try {
            val response = accountRepository.getDetailAccount(token)
            accountState.value = AccountState.Success(response)
        }catch (e: Exception){
            accountState.value = AccountState.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun updateDetailUser(token: String,password: String, name:String){
        try {
            val response  = accountRepository.updateDetailUser(token,password,name)
            updateAccountState.value = UpdateAccountState.Success(response)
        }catch (e: Exception){
            updateAccountState.value = UpdateAccountState.Error(e.message ?: "Unknown error")
        }
    }

}


sealed class AccountState{
    object Idle : AccountState()
    object Loading : AccountState()
    data class Success(val modelDetailAccount: ModelDetailAccount): AccountState()
    data class Error(val response : String): AccountState()
}

sealed class UpdateAccountState{
    object Idle : UpdateAccountState()
    object Loading : UpdateAccountState()
    data class Success(val response:String): UpdateAccountState()
    data class Error(val response : String): UpdateAccountState()
}