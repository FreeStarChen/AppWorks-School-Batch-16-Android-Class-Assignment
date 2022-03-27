package Homework

class Mage(name: String,var mana: Int) : Human(name) {
    override fun attack() {
        if (mana >= 10) {
            println("$name use Fireball")
            mana -= 10
        } else {
            println("Have no mana to attack")
        }
    }
}

    fun main() {

        val mage = Mage("Star", 100)
        mage.attack()
        println(mage.mana)

        mage.attack()
        println(mage.mana)
    }