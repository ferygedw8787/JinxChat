package org.stivjobs.jinxchat

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.internal.OnConnectionFailedListener
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.stivjobs.jinxchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , GoogleApiClient.OnConnectionFailedListener{

    private lateinit var  binding: ActivityMainBinding

    private lateinit var client: GoogleApiClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webID))
            .requestEmail()
            .build()

        client = GoogleApiClient.Builder(this)
            .enableAutoManage(this  ,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API , options)
            .build()

        binding.nextBtn.setOnClickListener{
            val  intent = Auth.GoogleSignInApi.getSignInIntent(client)
            startActivityForResult(intent , 300)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 300){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            result?.signInAccount?.let {account ->
               startActivity(Intent(this , MassageActivity::class.java))
            }
        }

    }
    override fun onConnectionFailed(result: ConnectionResult) {
        TODO("Not yet implemented")
    }
}