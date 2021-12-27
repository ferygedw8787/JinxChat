package org.stivjobs.jinxchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import org.stivjobs.jinxchat.databinding.ActivityMassageBinding

class MassageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMassageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMassageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseAuth.getInstance().currentUser?.let { user ->

            binding.userName.text = user.displayName
            binding.userEmail.text = user.email

            Picasso.get()
                .load(user.photoUrl)
                .placeholder(R.drawable.images)
                .into(binding.profileimage)

        }
        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
        }


    }
}