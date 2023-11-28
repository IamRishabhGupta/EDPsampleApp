package com.example.edp

import android.app.Activity
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
    fun loadUserData(activity: Activity)
    {
        mFireStore.collection("users")
            .document(getCurrentUserId()).get().addOnSuccessListener {document->
                val loggedInUser=document.toObject(user::class.java)!!
                when(activity)
                {
                    is loginActivity ->{
                        activity.signInSuccess(loggedInUser)
                    }

//                    is MainActivity ->
//                    {
//                        activity.updateNavigationUserDetails(loggedInUser)
//                    }
                    is InfoActivity->{
                        activity.setUserDataInUI(loggedInUser)
                    }
                }
            }.addOnFailureListener{

                    e->
                when(activity)
                {
//                    is MainActivity->
//                    {
//                        activity.hideProgressDialog()
//                    }

                    is loginActivity->
                    {
                        activity.hideProgressDialog()
                    }
                }



                Log.e("FirestoreSignInuser","error in writing document ")
            }
    }







}