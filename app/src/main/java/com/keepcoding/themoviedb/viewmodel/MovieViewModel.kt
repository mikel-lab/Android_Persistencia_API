package com.keepcoding.themoviedb.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.themoviedb.data.Repository
import com.keepcoding.themoviedb.model.MovieNetwork
import com.keepcoding.themoviedb.model.MoviePresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data1: MutableLiveData<List<MovieNetwork>> = MutableLiveData()
    val data: MutableLiveData<List<MoviePresentation>> = MutableLiveData()

    val numberOfAppOpened: MutableLiveData<Int> = MutableLiveData()

    fun getViewModelPopularMovies() {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getRepositoryPopularMovies()
            }

            data.postValue(movies)
        }
    }

    fun trabajoPesado(completion: (Int) -> (Unit)) {
        Log.d("CORRUTINAS", "TRABAJO PESADO INIT")

        val thread = Thread {
            Log.d("CORRUTINAS", "THREAD INIT")
            Thread.sleep(15000)
            Log.d("CORRUTINAS", "THREAD FINISHED")
            completion(100)
        }
        thread.start()

        Log.d("CORRUTINAS", "TRABAJO PESADO FINISHED")
    }

    fun trabajoPesadoConCorrutina() {
        viewModelScope.launch(Dispatchers.Main) {

/*            val a = launch(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe10Segundos(10)
                //return@launch result
                Log.d("CORRUTINAS", result.toString())
                result
            }*/



/*            val a = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe10Segundos(20)

                return@async result
            }*/

            Log.d("CORRUTINAS", "INIT TRABAJO") // T = 0

            val a = withContext(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe1Segundos(30)

                return@withContext result
            }


            Log.d("CORRUTINAS", a.toString()) // T = 1


            val d = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe1Segundos(10)

                return@async result
            }
            val b = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe1Segundos(20)

                return@async result
            }
            val c = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDe1Segundos(30)

                return@async result
            }

            Log.d("CORRUTINAS", "${b.await()} ${c.await()} ${d.await()}") // T = 2
            Log.d("CORRUTINAS", "${b.await()}") // T = 2


            val e = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDeXSegundos(5)

                return@async result
            }
            Log.d("CORRUTINAS", "2") // T = 2
            val f = withContext(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDeXSegundos(2)

                result
            }
            Log.d("CORRUTINAS", "3") // T = 4
            val g = async(Dispatchers.IO) {
                val result = devuelveResultadoDespuesDeXSegundos(4)

                return@async result
            }
            Log.d("CORRUTINAS", "4") // T = 4

            Log.d("CORRUTINAS", "F: $f") // T = 4
            Log.d("CORRUTINAS", "E: ${e.await()}") // T = 13
            Log.d("CORRUTINAS", "G: ${g.await()}") // T = 13
        }
    }

    private fun devuelveResultadoDespuesDe1Segundos(int: Int): Int {
        //Log.d("CORRUTINAS", "INIT")
        Thread.sleep(1000)
        //Log.d("CORRUTINAS", "FINISHED")
        return int
    }

    private fun devuelveResultadoDespuesDeXSegundos(int: Int): Int {
        //Log.d("CORRUTINAS", "INIT")
        Thread.sleep((int * 1000).toLong())
        //Log.d("CORRUTINAS", "FINISHED")
        return int
    }



    private fun getNumberOfAppOpened() {
        numberOfAppOpened.postValue(repository.getNumberOfAppOpened())
    }

    fun increaseNumberOfAppOpened() {
        repository.increaseNumberOfAppOpened()
        getNumberOfAppOpened()
    }

    fun getMoviesReactivo() {
        Log.d("HOLA", "getMoviesReactivo")
        data1.postValue(listOf(
            "Batman 1",
            "Batman 2",
            "Batman 3",
        ).map { MovieNetwork(it) })
    }

    fun updateSoloStarWarsReactivo() {
        Log.d("HOLA", "updateSoloStarWarsReactivo")
        data1.postValue(listOf(
            "Star wars 1",
            "Star wars 2",
            "Star wars 3",
            "Star wars 4",
            "Star wars 5",
            "Star wars 6",
        ).map { MovieNetwork(it) })
    }

    fun threadBloqueante() {
/*        val thread = Thread(
            Runnable {
                Log.d("THREAD_BLOQUEANTE", "START")
                Thread.sleep(5000)
                Log.d("THREAD_BLOQUEANTE", "END")
            }
        )
  */
        val handler = Handler(Looper.getMainLooper()).post(Runnable {
            Log.d("THREAD_BLOQUEANTE", "START")
            Thread.sleep(5000)
            Log.d("THREAD_BLOQUEANTE", "END")
        })


//        thread.run()
    }

    fun corrutinaBloqueante() {
        GlobalScope.launch {
            Log.d("CORRUTINA", "START")
            Thread.sleep(10000)
            Log.d("CORRUTINA", "END")
        }
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("CORRUTINA", "START 1")
            Thread.sleep(10000)
            Log.d("CORRUTINA", "END 1")
        }
    }

    fun getMoviesConCorrutina() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(10000)
            data1.postValue(listOf(
                "Star wars 1",
                "Star wars 2",
                "Star wars 3",
                "Star wars 4",
                "Star wars 5",
                "Star wars 6",
            ).map { MovieNetwork(it) })
        }
    }

    fun getMoviesConCorrutinaResult() {
        viewModelScope.launch(Dispatchers.IO) {


            val resultLaunch = launch {
                delay(3000)
                "HOLA"
            }
            resultLaunch.cancel()

            val resultWithContext = withContext(Dispatchers.IO) {
                delay(1000)
                "HOLA"
            }


            val resultAsync = async {
                delay(3000)
                "HOLA"
            }

            resultAsync.await()

            Log.d("CORRUTINAS", resultWithContext)
            Log.d("CORRUTINAS", resultLaunch.toString())
            Log.d("CORRUTINAS", resultAsync.await())
        }
    }

    private suspend fun getListaMovies(start: Int, size: Int): List<MovieNetwork> {
        val movies = mutableListOf<MovieNetwork>()
        for (i in start..(start + size)) {
            movies.add(MovieNetwork("Title $i"))
        }

        return movies
    }


    fun testCorrutinas() {
        viewModelScope.launch {

            val suma = async(Dispatchers.IO) {
                val suma = sumaLos100PrimerosNumeros()
                suma
            }

            val suma1 = async(Dispatchers.IO) {
                val suma = sumaLos100PrimerosNumeros()
                suma
            }
            suma1.await()

            val suma2 = withContext(Dispatchers.IO) {
                val suma = sumaLos100PrimerosNumeros()
                suma
            }

            launch(Dispatchers.IO) {
                cuentaHasta100()
            }

            launch(Dispatchers.IO) {
                Log.d("IMPRIME_VALOR", "ANTES")
                imprimeValor(suma1.await())
                Log.d("IMPRIME_VALOR", "DESPUES")
            }


        }
    }

    private suspend fun cuentaHasta100() {
        for (i in 0..100) {
            Log.d("CUENTA", "$i")
            delay(10)
        }
    }

    private suspend fun sumaLos100PrimerosNumeros(): Int {
        var count = 0
        for (i in 0..100) {
            count += i
            Log.d("SUMA", "$i Total: $count")
            delay(10)
        }
        return count
    }

    private suspend fun imprimeValor(valor: Int) {
        Log.d("IMPRIME", "$valor")
    }


    fun getMoviesImperativo(): List<MovieNetwork> {
        return listOf(
            "Star wars",
            "Batman",
            "Love Actually",
            "Star wars",
            "Batman",
            "Love Actually",
            "Star wars",
            "Batman",
            "Love Actually",
            "Star wars",
            "Batman",
            "Love Actually",
            "Star wars",
            "Batman",
            "Love Actually",
            "Star wars",
            "Batman",
            "Love Actually"
        ).map { MovieNetwork(it) }
    }

    fun deleteLocalDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteMovies()
            }

            getViewModelPopularMovies()
        }
    }

    fun deleteFirstMovie() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteMovie(data.value?.first())
            }

            getViewModelPopularMovies()
        }
    }
}