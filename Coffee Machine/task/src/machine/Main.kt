package machine

import java.util.*

val scanner = Scanner(System.`in`)



fun showMainMenu(){
//    println("Write action (buy, fill, take, remaining, exit):")
//    val userChoice = scanner.next()
//
//    when(userChoice) {
//        "buy" -> buyCoffee()
//        "fill" -> fillCoffeeMachine()
//        "take" -> takeMoney()
//        "remaining" -> printMachineState()
//        "exit" -> return
//    }
}

fun main() {
    showMainMenu()
}

class CoffeeMachine{

    private var availableWater = 400
    private var availableMilk = 540
    private var availableBeans = 120
    private var income = 550
    private var disposableCups = 9



    fun printMachineState() {
        println("$availableWater of water")
        println("$availableMilk of milk")
        println("$availableBeans of coffee beans")
        println("$disposableCups of disposable cups")
        println("$income of money")

        showMainMenu()
    }

    fun buyCoffee(){
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        val coffeeChoice = scanner.next()

        if (!haveEnoughResources(coffeeChoice)){
            showMainMenu()
        }
        else{
            when(coffeeChoice) {
                "1" -> {
                    availableWater -= 250
                    availableBeans -= 16
                    income += 4
                    disposableCups--
                    showMainMenu()
                }
                "2" -> {
                    availableWater -= 350
                    availableMilk -= 75
                    availableBeans -= 20
                    income += 7
                    disposableCups--
                    showMainMenu()
                }
                "3" -> {
                    availableWater -= 200
                    availableMilk -= 100
                    availableBeans -= 12
                    income += 6
                    disposableCups--
                    showMainMenu()
                }
                "back" -> {
                    showMainMenu()
                }
            }
        }

    }

    fun fillCoffeeMachine(){
        println("Write how many ml of water do you want to add:")
        availableWater += scanner.nextInt()

        println("Write how many ml of milk do you want to add:")
        availableMilk += scanner.nextInt()

        println("Write how many grams of coffee beans do you want to add:")
        availableBeans += scanner.nextInt()

        println("Write how many disposable cups of coffee do you want to add:")
        disposableCups += scanner.nextInt()

        showMainMenu()
    }

    fun takeMoney(){
        println("I gave you $$income")

        income = 0

        showMainMenu()
    }
    fun haveEnoughResources(coffeeChoice: String) : Boolean {
//    var possibleCups = 0
//    when(coffeeChoice) {
//        "1" -> possibleCups = minOf(available_water/ 250, available_beans/ 16)
//        "2" -> possibleCups = minOf(available_water/ 350, available_milk/ 75, available_beans/ 20)
//        "3" -> possibleCups = minOf(available_water/ 200, available_milk/ 100, available_beans/ 12)
//    }

        var possible = true
        var lowResource = ""

        when(coffeeChoice){
            "1" -> {
                if(availableWater/250 <= 0){
                    lowResource ="water"
                    possible = false
                }
                else if(availableBeans/16 <= 0){
                    lowResource = "coffee beans"
                    possible = false
                }
            }
            "2" -> {
                when {
                    availableWater/350 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    availableMilk/ 75 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    availableBeans/ 20 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    else -> ""
                }
            }
            "3" -> {
                when {
                    availableWater/200 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    availableMilk/ 100 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    availableBeans/ 12 <= 0 -> {
                        lowResource ="water"
                        possible = false
                    }
                    else -> ""
                }
            }
            else -> possible = false
        }

        return when {
            possible -> {
                println("I have enough resources, making you a coffee!")
                true
            }
            !possible -> {
                println("Sorry, not enough $lowResource!")
                false
            }
            else -> false
        }
    }
}





