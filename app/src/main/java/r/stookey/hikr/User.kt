package r.stookey.hikr

data class User(val id: String, var Username: String, var Email: String, var Password: String){


    fun changePassword(password: String){
        this.Password = password
    }

}