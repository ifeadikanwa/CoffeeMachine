package machine

import java.util.*



fun showToUser(query: String, coffeeMachine: CoffeeMachine){
    val scanner = Scanner(System.`in`)

    println(query)
    val userChoice = scanner.next()

    val response = coffeeMachine.perform(userChoice)
    if (response != null){
        showToUser(response, coffeeMachine)
    }

}

fun main() {
    val coffeeMachine = CoffeeMachine()
    showToUser(CoffeeMachine.MAIN_MENU_QUERY, coffeeMachine)
}

class CoffeeMachine{
    private var availableWater = 400
    private var availableMilk = 540
    private var availableBeans = 120
    private var income = 550
    private var disposableCups = 9

    var currentStatus: Status = Status.NO_JOB

    companion object {
        const val COFFEE_TYPE_QUERY = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"
        const val MAIN_MENU_QUERY = "Write action (buy, fill, take, remaining, exit):"
        const val ADD_WATER_QUERY = "Write how many ml of water do you want to add:"
        const val ADD_MILK_QUERY = "Write how many ml of milk do you want to add:"
        const val ADD_BEANS_QUERY = "Write how many grams of coffee beans do you want to add:"
        const val ADD_CUPS_QUERY = "Write how many disposable cups of coffee do you want to add:"
    }

    enum class Status{
        NO_JOB, CHOOSING_COFFEE_TYPE, ADDING_WATER, ADDING_MILK, ADDING_BEANS, ADDING_CUPS
    }


    fun perform(instruction: String) : String?{
        when(instruction) {
            "buy" -> {
                currentStatus = Status.CHOOSING_COFFEE_TYPE
                return COFFEE_TYPE_QUERY
            }
            "fill" -> {
                currentStatus = Status.ADDING_WATER
                return ADD_WATER_QUERY
            }
            "take" -> {
                takeMoney()
                return MAIN_MENU_QUERY
            }
            "remaining" -> {
                printMachineState()
                return MAIN_MENU_QUERY
            }
            "exit" -> return null
            else -> {
                if (currentStatus == Status.CHOOSING_COFFEE_TYPE){
                    return buyCoffee(instruction)
                }
                else if(currentStatus == Status.ADDING_WATER || currentStatus == Status.ADDING_BEANS
                    || currentStatus == Status.ADDING_MILK || currentStatus == Status.ADDING_CUPS) {
                    return fillCoffeeMachine(instruction)
                }

            }
        }
        return null
    }

    private fun printMachineState() {
        println("$availableWater of water")
        println("$availableMilk of milk")
        println("$availableBeans of coffee beans")
        println("$disposableCups of disposable cups")
        println("$income of money")
    }

    private fun buyCoffee(coffeeChoice: String) : String{
        if (haveEnoughResources(coffeeChoice)) {
            when (coffeeChoice) {
                "1" -> {
                    availableWater -= 250
                    availableBeans -= 16
                    income += 4
                    disposableCups--
                }
                "2" -> {
                    availableWater -= 350
                    availableMilk -= 75
                    availableBeans -= 20
                    income += 7
                    disposableCups--
                }
                "3" -> {
                    availableWater -= 200
                    availableMilk -= 100
                    availableBeans -= 12
                    income += 6
                    disposableCups--
                }
            }
        }

        currentStatus = Status.NO_JOB
        return MAIN_MENU_QUERY
    }

    fun fillCoffeeMachine(amount: String) : String{
        when(currentStatus){
            Status.ADDING_WATER -> {
                availableWater += amount.toInt()
                currentStatus = Status.ADDING_MILK
                return ADD_MILK_QUERY
            }
            Status.ADDING_MILK -> {
                availableMilk += amount.toInt()
                currentStatus = Status.ADDING_BEANS
                return ADD_BEANS_QUERY
            }
            Status.ADDING_BEANS -> {
                availableBeans += amount.toInt()
                currentStatus = Status.ADDING_CUPS
                return ADD_CUPS_QUERY
            }
            Status.ADDING_CUPS -> {
                disposableCups += amount.toInt()
                currentStatus = Status.NO_JOB
                return MAIN_MENU_QUERY
            }
            else -> {
                currentStatus = Status.NO_JOB
                return MAIN_MENU_QUERY
            }
        }
    }

    private fun takeMoney(){
        println("I gave you $$income")
        income = 0

    }
    private fun haveEnoughResources(coffeeChoice: String) : Boolean {
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
            else -> return false
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





