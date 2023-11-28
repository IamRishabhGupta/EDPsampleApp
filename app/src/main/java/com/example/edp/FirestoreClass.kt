package com.example.edp

import android.util.Log
import com.example.edp.Constants.Companion.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mFireStore = FirebaseFirestore.getInstance()

    //Authentication Logic
    fun registerUser(activity: RegisterActivity, userinfo: user) {
        mFireStore.collection("users").document(getCurrentUserId()).set(
            userinfo,
            SetOptions.merge()
        ).addOnSuccessListener {
            activity.userRegisteredSuccess()
        }.addOnFailureListener { e ->
//            Log.e(activity.javaClass.simpleName, e.toString())
        }
    }
    fun getCurrentUserId(): String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }
}