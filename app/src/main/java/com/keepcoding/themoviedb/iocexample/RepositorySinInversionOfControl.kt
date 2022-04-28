package com.keepcoding.themoviedb.iocexample

class RepositorySinInversionOfControl {

    private val dependency1 = DependencyProduccion()

    fun getDatos(): Int {
        return dependency1.getDatos()
    }
}





class RepositoryInversionOfControl(
    private val dependency1: DepencencyInterface
){
    fun getDatos(): Int {
        return dependency1.getDatos()
    }
}
