fun interface Operacion {
    fun operar(x: Int, y: Int): Int
}

class Sumador : Operacion {
    override fun operar(x: Int, y: Int): Int {
        return x + y
    }
}

class Multiplicador : Operacion {
    override fun operar(x: Int, y: Int): Int {
        return x * y
    }
}


val sumar = { x: Int, y: Int -> x + y }
val multiplicar = { x: Int, y: Int -> x * y }

fun main() {


    // Variable para guardar operacion
    var operacion: Operacion

    //Con una instancia de un objeto cuya clase implementa el interfaz
    operacion = Sumador()
    operacion = Multiplicador()

    var resultado = operacion.operar(4, 5)
    println(resultado)

    //Con un objeto de tipo anonimo
    operacion = object : Operacion {
        override fun operar(x: Int, y: Int): Int {
            return x - y
        }
    }
    resultado = operacion.operar(70, 67)
    println(resultado)

    // Con una lambda
    operacion = Operacion { x, y -> x / y }
    resultado = operacion.operar(32, 4)
    println(resultado)

    // Con una variable de tipo funcion lambda
    operacion = Operacion(sumar)
    resultado = operacion.operar(4, 6)
    println(resultado)



    realizarOperacion(7, 5, sumar)
    realizarOperacion(4, 3, multiplicar)
    realizarOperacion(10, 5) { x, y -> x - y }
    operaSobre10y5 { x, y -> x / y }

    operaSobre10 { n -> n * n }
    operaSobre10 { imprimir(it) }
    operaSobre10 { num -> imprimir(num) }
}


fun imprimir(x: Int): Int {
    println(x)
    return x
}

fun operaSobre10(operacion: (Int) -> Int): Int {
    return operacion(10)
}


fun operaSobre10y5(operacion: (Int, Int) -> Int): Int {
    return operacion(10, 5)
}


fun realizarOperacion(x: Int, y: Int, operacion: (Int, Int) -> Int) {
    println(x)
    println(y)
    val r = operacion(x, y)
    println(r)
}

fun realizarOperacion2(operacion: Operacion): Int {
    return operacion.operar(3, 4)
}