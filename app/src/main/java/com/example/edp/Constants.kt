package com.example.edp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

class Constants {
    companion object{

        const val USERS:String="users"
        const val EMAIL:String="email"
        const val ID : String = "id"


        const val BOARDS:String="boards"

        const val IMAGE:String="image"
        const val NAME:String="name"
        const val MOBILE:String="mobile"
        const val ASSIGNED_TO:String="assignedTo"

        const val READ_STORAGE_PERMISSION_CODE=1
        const val PICK_IMAGE_REQUEST_CODE=2

        const val DOCUMENT_ID:String="document id"
        const val TASK_LIST:String="taskList"
        const val BOARD_DETAIL:String="board_detail"

        const val TASK_LIST_ITEM_POSITION:String="task_list_item_position"
        const val CARD_LIST_ITEM_POSITION:String="card_list_item_position"

        fun showImageChooser(activity: Activity){
            var galleryIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
        }

        fun getFileExtention(activity: Activity, uri: Uri):String?{
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(activity.contentResolver.getType(uri))
        }
    }



}
