package machine
//stages that are completed and the code wasn't used in the next stage


//private const val ADD_WATER = 200
//private const val ADD_MILK = 50
//private const val ADD_BEANS= 15

//stage 1
//    println("Starting to make a coffee")
//    println("Grinding coffee beans")
//    println("Boiling water")
//    println("Mixing boiled water with crushed coffee beans")
//    println("Pouring coffee into the cup")
//    println("Pouring some milk into the cup")
//    println("Coffee is ready!")

//stage 2
//    val numOfCups = scanner.nextInt()
//    println("For $numOfCups cups of coffee you will need:")
//    println("${ADD_WATER*numOfCups} ml of water")
//    println("${ADD_MILK*numOfCups} ml of milk")
//    println("${ADD_BEANS*numOfCups} g of coffee beans")

//stage 3
//    println("Write how many ml of water the coffee machine has:")
//    available_water = scanner.nextInt()
//
//    println("Write how many ml of milk the coffee machine has:")
//    available_milk = scanner.nextInt()
//
//    println("Write how many grams of coffee beans the coffee machine has:")
//    available_beans = scanner.nextInt()
//
//    println("Write how many cups of coffee you will need:")
//    val numOfCups = scanner.nextInt()
//
//    println(canWeMakeThatAmount(numOfCups))

//fun canWeMakeThatAmount(numOfCups: Int) : String {
//    val possibleCups = minOf(available_water/ ADD_WATER, available_milk/ ADD_MILK, available_beans/ ADD_BEANS)
//
//    return when {
//        possibleCups == numOfCups -> {
//            "Yes, I can make that amount of coffee"
//        }
//        possibleCups < numOfCups -> {
//            "No, I can make only $possibleCups cups of coffee"
//        }
//        else -> {
//            "Yes, I can make that amount of coffee (and even ${possibleCups-numOfCups} more than that)"
//        }
//    }
//}