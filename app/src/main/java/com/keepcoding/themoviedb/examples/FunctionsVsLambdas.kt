package com.keepcoding.themoviedb.examples

import android.util.Log

class FunctionsVsLambdas {

    fun reciboIntDevuelvoString(int: Int): String{

        val a : (Int)->(String) = {
            Log.d("FUNCIONES_ANONIMAS", "HOLA")
            val it2 = it +1
            val it3 = it + it2
            it3.toString()
        }


        //val b = a(1)

        funcionConFuncionPorParametro(object : (Int)->(String){
            override fun invoke(p1: Int): String {
                Log.d("FUNCIONES_ANONIMAS", "INVOKE")
                return ""
            }

        })

        funcionConFuncionPorParametro(a)

        funcionConFuncionPorParametro(){
            Log.d("FUNCIONES_ANONIMAS:", "HE TERMINADO")
            ""
        }

        return int.toString()
    }

    private fun funcionConFuncionPorParametro(completionHandler: (Int)->(String)){
        Log.d("FUNCIONES_ANONIMAS", "funcionConFuncionPorParametro")
        completionHandler(2)
    }

    private fun funcion1(int:Int): String{
        return int.toString()
    }
}