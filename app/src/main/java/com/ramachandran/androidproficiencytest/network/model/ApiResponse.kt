package com.ramachandran.androidproficiencytest.network.model

data class DropBoxUserContent(val title : String,
                              val rows : MutableList<Row>){

}
data class Row(val title : String,
               val description : String,
               val imageHref : String){

}