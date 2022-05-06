package com.mohith.ezetap.uitls

import android.widget.LinearLayout

class Const {
    companion object{

        const val phoneNumber = "917090890727"
        const val playStoreUrl = "https://play.google.com/store/apps/developer?id=Iconic+Developer"
        const val linkedInUrl = "https://www.linkedin.com/in/mohith-g-e-298071211"
        const val githubUrl = "https://github.com/mohith0091"

       const val paddingLeft:Int = 50
        const val paddingRight:Int = 50
        const  val paddingTop:Int = 30
        const val paddingBottom:Int =5

        fun getDefaultParams(): LinearLayout.LayoutParams{
            return getDefaultParams(
                paddingLeft,
                paddingTop,
                paddingRight,
                paddingBottom
            )
        }
        fun getDefaultParams(left:Int,top:Int,right:Int,bottom:Int): LinearLayout.LayoutParams{
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(
                left,
                top,
                right,
                bottom
            )
            return params
        }
    }
}