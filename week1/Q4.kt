package Homework


open class Human(var name: String) {
    open fun attack() {
        println("$name use Fist Attack!")
    }
}

fun main() {

    val human = Human("Mark")
    human.attack()
}