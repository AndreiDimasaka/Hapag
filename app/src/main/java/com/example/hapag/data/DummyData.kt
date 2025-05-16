package com.example.hapag.data

import androidx.lifecycle.ViewModel
import com.example.hapag.R

class DummyDataViewModel: ViewModel() {


    val sweetFilipinoFoods =
        listOf(
            Recipe(
                image = ImageData.DrawableRes(R.drawable.halohalo),
                title = "Halo-Halo",
                description = "A popular cold dessert with mixed sweets.",
                servingSize = "1 glass",
                cookTime = "10 mins",
                category = "Merienda",
                ingredients = listOf("Shaved ice", "Evaporated milk", "Leche flan", "Ube", "Sweet beans"),
                instructions = listOf("Prepare ingredients", "Layer in a glass", "Add ice and milk", "Top with leche flan and ube")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.lecheflan),
                title = "Leche Flan",
                description = "Rich and creamy Filipino custard.",
                servingSize = "8 slices",
                cookTime = "1 hour",
                category = "Dessert",
                ingredients = listOf("Egg yolks", "Condensed milk", "Evaporated milk", "Sugar"),
                instructions = listOf("Mix yolks and milk", "Caramelize sugar", "Pour into mold", "Steam for 45 mins")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.ubehalaya),
                title = "Ube Halaya",
                description = "Sweet purple yam dessert.",
                servingSize = "6 servings",
                cookTime = "45 mins",
                category = "Dessert",
                ingredients = listOf("Grated ube", "Condensed milk", "Butter", "Coconut milk"),
                instructions = listOf("Combine ingredients", "Cook until thick", "Cool and serve")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bibingka),
                title = "Bibingka",
                description = "Rice cake often served during Christmas.",
                servingSize = "1 piece",
                cookTime = "30 mins",
                category = "Breakfast",
                ingredients = listOf("Rice flour", "Coconut milk", "Egg", "Sugar", "Banana leaf"),
                instructions = listOf("Mix ingredients", "Pour in banana-leaf lined mold", "Bake until set")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bumbong),
                title = "Puto Bumbong",
                description = "Steamed purple rice cake cooked in bamboo tubes.",
                servingSize = "3 sticks",
                cookTime = "20 mins",
                category = "Breakfast",
                ingredients = listOf("Purple rice", "Grated coconut", "Brown sugar", "Butter"),
                instructions = listOf("Steam rice in bamboo tubes", "Brush with butter", "Serve with coconut and sugar")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.kutsinta),
                title = "Kutsinta",
                description = "Sticky steamed rice cake.",
                servingSize = "10 pieces",
                cookTime = "30 mins",
                category = "Merienda",
                ingredients = listOf("Rice flour", "Brown sugar", "Lye water", "Annatto"),
                instructions = listOf("Mix ingredients", "Steam in molds", "Serve with grated coconut")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.suman),
                title = "Suman",
                description = "Sticky rice wrapped in banana leaves.",
                servingSize = "4 rolls",
                cookTime = "1 hour",
                category = "Breakfast",
                ingredients = listOf("Glutinous rice", "Coconut milk", "Banana leaves", "Sugar"),
                instructions = listOf("Soak and cook rice", "Wrap in banana leaves", "Steam until done")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bukopandan),
                title = "Buko Pandan",
                description = "Chilled dessert with young coconut and pandan jelly.",
                servingSize = "6 cups",
                cookTime = "30 mins + chilling",
                category = "Dessert",
                ingredients = listOf("Young coconut", "Pandan-flavored gelatin", "Cream", "Condensed milk"),
                instructions = listOf("Prepare gelatin", "Mix with coconut and cream", "Chill before serving")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.turon),
                title = "Turon",
                description = "Caramelized banana spring roll.",
                servingSize = "6 rolls",
                cookTime = "15 mins",
                category = "Merienda",
                ingredients = listOf("Saba banana", "Brown sugar", "Lumpia wrapper", "Jackfruit"),
                instructions = listOf("Wrap banana and jackfruit", "Roll in sugar", "Fry until golden")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bananacue),
                title = "Banana Cue",
                description = "Deep-fried caramelized bananas on skewers.",
                servingSize = "5 sticks",
                cookTime = "10 mins",
                category = "Merienda",
                ingredients = listOf("Saba banana", "Brown sugar", "Cooking oil", "Skewers"),
                instructions = listOf("Fry bananas", "Add sugar to caramelize", "Skewer and serve")
            )
        )
    val savoryFilipinoFoods =
        listOf(
            Recipe(
                image = ImageData.DrawableRes(R.drawable.adobo),
                title = "Pork Adobo",
                description = "Savory pork stew simmered in soy sauce, vinegar, and garlic.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = "Lunch",
                ingredients = listOf("Pork belly", "Soy sauce", "Vinegar", "Garlic", "Bay leaves", "Peppercorns"),
                instructions = listOf("Marinate pork in soy sauce and garlic", "Sauté and simmer with vinegar", "Cook until tender")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.sinigang),
                title = "Pork Sinigang",
                description = "A sour tamarind-based soup with pork and vegetables.",
                servingSize = "5 servings",
                cookTime = "1 hour",
                category = "Dinner",
                ingredients = listOf("Pork ribs", "Tamarind soup base", "Tomatoes", "Onion", "Radish", "Kangkong"),
                instructions = listOf("Boil pork until tender", "Add vegetables and sinigang mix", "Simmer until done")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.torta),
                title = "Tortang Talong",
                description = "Grilled eggplant omelette.",
                servingSize = "2 servings",
                cookTime = "20 mins",
                category = "Dinner",
                ingredients = listOf("Eggplant", "Eggs", "Garlic", "Salt", "Pepper"),
                instructions = listOf("Grill eggplant", "Peel and flatten", "Dip in egg mixture", "Fry until golden")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.karekare),
                title = "Kare-Kare",
                description = "Peanut-based oxtail stew served with vegetables and bagoong.",
                servingSize = "6 servings",
                cookTime = "1.5 hours",
                category = "Lunch",
                ingredients = listOf("Oxtail", "Peanut butter", "Banana heart", "Eggplant", "String beans", "Annatto"),
                instructions = listOf("Cook oxtail until tender", "Add vegetables", "Simmer with peanut sauce")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.pinakbet),
                title = "Pinakbet",
                description = "Vegetable stew flavored with bagoong.",
                servingSize = "4 servings",
                cookTime = "30 mins",
                category = "Lunch",
                ingredients = listOf("Bitter melon", "Squash", "Eggplant", "String beans", "Bagoong"),
                instructions = listOf("Sauté vegetables", "Add bagoong and cook until tender")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.laing),
                title = "Laing",
                description = "Dried taro leaves cooked in coconut milk.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = "Dinner",
                ingredients = listOf("Dried taro leaves", "Coconut milk", "Chili", "Ginger", "Shrimp paste"),
                instructions = listOf("Simmer taro leaves in coconut milk", "Add seasonings and cook until soft")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.pancit),
                title = "Pancit Bihon",
                description = "Stir-fried rice noodles with vegetables and meat.",
                servingSize = "5 servings",
                cookTime = "40 mins",
                category = "Merienda",
                ingredients = listOf("Rice noodles", "Carrots", "Cabbage", "Chicken", "Soy sauce"),
                instructions = listOf("Soak noodles", "Stir-fry vegetables and meat", "Add noodles and mix")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.lumpia),
                title = "Lumpia",
                description = "Filipino spring rolls filled with vegetables or meat.",
                servingSize = "10 rolls",
                cookTime = "30 mins",
                category = "Merienda",
                ingredients = listOf("Lumpia wrapper", "Ground pork", "Carrots", "Garlic", "Onion"),
                instructions = listOf("Prepare filling", "Wrap tightly", "Fry until golden brown")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.sisig),
                title = "Sisig",
                description = "Crispy chopped pork dish served on a sizzling plate.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = "Lunch",
                ingredients = listOf("Pork face", "Liver", "Onion", "Chili", "Calamansi"),
                instructions = listOf("Boil and fry pork", "Chop and sauté with spices", "Serve sizzling")
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bulalo),
                title = "Bulalo",
                description = "Beef shank soup with bone marrow and vegetables.",
                servingSize = "5 servings",
                cookTime = "2 hours",
                category = "Dinner",
                ingredients = listOf("Beef shank", "Corn", "Cabbage", "Potatoes", "Onion"),
                instructions = listOf("Simmer beef until tender", "Add vegetables", "Cook until ready")
            )
        )
}