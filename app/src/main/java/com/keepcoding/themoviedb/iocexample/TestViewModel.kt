package com.keepcoding.themoviedb.iocexample

class TestViewModel {
    // VERSION 1
    val repositorySinInversionOfControl = RepositorySinInversionOfControl()

    // ------------------------ //

    // VERSION 2
    val dependencyProduccion = DependencyProduccion()
    val dependencyStaging = DependencyStaging()


    val repositoryInversionOfControl = RepositoryInversionOfControl(dependencyStaging)
}