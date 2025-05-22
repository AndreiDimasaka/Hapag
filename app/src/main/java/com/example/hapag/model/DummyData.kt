package com.example.hapag.model

import androidx.lifecycle.ViewModel
import com.example.hapag.R
import com.example.hapag.model.Item.Text

class DummyDataViewModel: ViewModel() {
    val recipesDummy =
        listOf(
            Recipe(
                image = ImageData.DrawableRes(R.drawable.adobo),
                title = "Pork Adobo",
                description = "Savory pork stew simmered in soy sauce, vinegar, and garlic.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = listOf("Lunch", "Savory"),
                ingredients = listOf(
                    Text("Pork belly"), Text("Soy sauce"), Text("Vinegar"), Text("Garlic"), Text("Bay leaves"), Text("Peppercorns")
                ),
                instructions = listOf(
                    Text("Marinate pork in soy sauce and garlic"),
                    Text("Sauté and simmer with vinegar"),
                    Text("Cook until tender")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.sinigang),
                title = "Pork Sinigang",
                description = "A sour tamarind-based soup with pork and vegetables.",
                servingSize = "5 servings",
                cookTime = "1 hour",
                category = listOf("Dinner", "Savory"),
                ingredients = listOf(
                    Text("Pork ribs"), Text("Tamarind soup base"), Text("Tomatoes"), Text("Onion"), Text("Radish"), Text("Kangkong")
                ),
                instructions = listOf(
                    Text("Boil pork until tender"),
                    Text("Add vegetables and sinigang mix"),
                    Text("Simmer until done")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.torta),
                title = "Tortang Talong",
                description = "Grilled eggplant omelette.",
                servingSize = "2 servings",
                cookTime = "20 mins",
                category = listOf("Dinner", "Savory"),
                ingredients = listOf(
                    Text("Eggplant"), Text("Eggs"), Text("Garlic"), Text("Salt"), Text("Pepper")
                ),
                instructions = listOf(
                    Text("Grill eggplant"),
                    Text("Peel and flatten"),
                    Text("Dip in egg mixture"),
                    Text("Fry until golden")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.karekare),
                title = "Kare-Kare",
                description = "Peanut-based oxtail stew served with vegetables and bagoong.",
                servingSize = "6 servings",
                cookTime = "1.5 hours",
                category = listOf("Lunch", "Savory"),
                ingredients = listOf(
                    Text("Oxtail"), Text("Peanut butter"), Text("Banana heart"), Text("Eggplant"), Text("String beans"), Text("Annatto")
                ),
                instructions = listOf(
                    Text("Cook oxtail until tender"),
                    Text("Add vegetables"),
                    Text("Simmer with peanut sauce")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.pinakbet),
                title = "Pinakbet",
                description = "Vegetable stew flavored with bagoong.",
                servingSize = "4 servings",
                cookTime = "30 mins",
                category = listOf("Lunch", "Savory"),
                ingredients = listOf(
                    Text("Bitter melon"), Text("Squash"), Text("Eggplant"), Text("String beans"), Text("Bagoong")
                ),
                instructions = listOf(
                    Text("Sauté vegetables"),
                    Text("Add bagoong and cook until tender")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.laing),
                title = "Laing",
                description = "Dried taro leaves cooked in coconut milk.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = listOf("Dinner", "Savory"),
                ingredients = listOf(
                    Text("Dried taro leaves"), Text("Coconut milk"), Text("Chili"), Text("Ginger"), Text("Shrimp paste")
                ),
                instructions = listOf(
                    Text("Simmer taro leaves in coconut milk"),
                    Text("Add seasonings and cook until soft")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.pancit),
                title = "Pancit Bihon",
                description = "Stir-fried rice noodles with vegetables and meat.",
                servingSize = "5 servings",
                cookTime = "40 mins",
                category = listOf("Merienda", "Savory"),
                ingredients = listOf(
                    Text("Rice noodles"), Text("Carrots"), Text("Cabbage"), Text("Chicken"), Text("Soy sauce")
                ),
                instructions = listOf(
                    Text("Soak noodles"),
                    Text("Stir-fry vegetables and meat"),
                    Text("Add noodles and mix")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.lumpia),
                title = "Lumpia",
                description = "Filipino spring rolls filled with vegetables or meat.",
                servingSize = "10 rolls",
                cookTime = "30 mins",
                category = listOf("Merienda", "Savory"),
                ingredients = listOf(
                    Text("Lumpia wrapper"), Text("Ground pork"), Text("Carrots"), Text("Garlic"), Text("Onion")
                ),
                instructions = listOf(
                    Text("Prepare filling"),
                    Text("Wrap tightly"),
                    Text("Fry until golden brown")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.sisig),
                title = "Sisig",
                description = "Crispy chopped pork dish served on a sizzling plate.",
                servingSize = "4 servings",
                cookTime = "1 hour",
                category = listOf("Lunch", "Savory"),
                ingredients = listOf(
                    Text("Pork face"), Text("Liver"), Text("Onion"), Text("Chili"), Text("Calamansi")
                ),
                instructions = listOf(
                    Text("Boil and fry pork"),
                    Text("Chop and sauté with spices"),
                    Text("Serve sizzling")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bulalo),
                title = "Bulalo",
                description = "Beef shank soup with bone marrow and vegetables.",
                servingSize = "5 servings",
                cookTime = "2 hours",
                category = listOf("Dinner", "Savory"),
                ingredients = listOf(
                    Text("Beef shank"), Text("Corn"), Text("Cabbage"), Text("Potatoes"), Text("Onion")
                ),
                instructions = listOf(
                    Text("Simmer beef until tender"),
                    Text("Add vegetables"),
                    Text("Cook until ready")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.halohalo),
                title = "Halo-Halo",
                description = "A popular cold dessert with mixed sweets.",
                servingSize = "1 glass",
                cookTime = "10 mins",
                category = listOf("Merienda", "Sweet"),
                ingredients = listOf(
                    Text("Shaved ice"), Text("Evaporated milk"), Text("Leche flan"), Text("Ube"), Text("Sweet beans")
                ),
                instructions = listOf(
                    Text("Prepare ingredients"), Text("Layer in a glass"), Text("Add ice and milk"), Text("Top with leche flan and ube")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.lecheflan),
                title = "Leche Flan",
                description = "Rich and creamy Filipino custard.",
                servingSize = "8 slices",
                cookTime = "1 hour",
                category = listOf("Lunch", "Sweet"),
                ingredients = listOf(
                    Text("Egg yolks"), Text("Condensed milk"), Text("Evaporated milk"), Text("Sugar")
                ),
                instructions = listOf(
                    Text("Mix yolks and milk"), Text("Caramelize sugar"), Text("Pour into mold"), Text("Steam for 45 mins")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.ubehalaya),
                title = "Ube Halaya",
                description = "Sweet purple yam dessert.",
                servingSize = "6 servings",
                cookTime = "45 mins",
                category = listOf("Dinner", "Sweet"),
                ingredients = listOf(
                    Text("Grated ube"), Text("Condensed milk"), Text("Butter"), Text("Coconut milk")
                ),
                instructions = listOf(
                    Text("Combine ingredients"), Text("Cook until thick"), Text("Cool and serve")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bibingka),
                title = "Bibingka",
                description = "Rice cake often served during Christmas.",
                servingSize = "1 piece",
                cookTime = "30 mins",
                category = listOf("Breakfast", "Sweet"),
                ingredients = listOf(
                    Text("Rice flour"), Text("Coconut milk"), Text("Egg"), Text("Sugar"), Text("Banana leaf")
                ),
                instructions = listOf(
                    Text("Mix ingredients"), Text("Pour in banana-leaf lined mold"), Text("Bake until set")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bumbong),
                title = "Puto Bumbong",
                description = "Steamed purple rice cake cooked in bamboo tubes.",
                servingSize = "3 sticks",
                cookTime = "20 mins",
                category = listOf("Breakfast", "Sweet"),
                ingredients = listOf(
                    Text("Purple rice"), Text("Grated coconut"), Text("Brown sugar"), Text("Butter")
                ),
                instructions = listOf(
                    Text("Steam rice in bamboo tubes"), Text("Brush with butter"), Text("Serve with coconut and sugar")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.kutsinta),
                title = "Kutsinta",
                description = "Sticky steamed rice cake.",
                servingSize = "10 pieces",
                cookTime = "30 mins",
                category = listOf("Merienda", "Sweet"),
                ingredients = listOf(
                    Text("Rice flour"), Text("Brown sugar"), Text("Lye water"), Text("Annatto")
                ),
                instructions = listOf(
                    Text("Mix ingredients"), Text("Steam in molds"), Text("Serve with grated coconut")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.suman),
                title = "Suman",
                description = "Sticky rice wrapped in banana leaves.",
                servingSize = "4 rolls",
                cookTime = "1 hour",
                category = listOf("Breakfast", "Sweet"),
                ingredients = listOf(
                    Text("Glutinous rice"), Text("Coconut milk"), Text("Banana leaves"), Text("Sugar")
                ),
                instructions = listOf(
                    Text("Soak and cook rice"), Text("Wrap in banana leaves"), Text("Steam until done")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bukopandan),
                title = "Buko Pandan",
                description = "Chilled dessert with young coconut and pandan jelly.",
                servingSize = "6 cups",
                cookTime = "30 mins + chilling",
                category = listOf("Lunch", "Sweet"),
                ingredients = listOf(
                    Text("Young coconut"), Text("Pandan-flavored gelatin"), Text("Cream"), Text("Condensed milk")
                ),
                instructions = listOf(
                    Text("Prepare gelatin"), Text("Mix with coconut and cream"), Text("Chill before serving")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.turon),
                title = "Turon",
                description = "Caramelized banana spring roll.",
                servingSize = "6 rolls",
                cookTime = "15 mins",
                category = listOf("Merienda", "Sweet"),
                ingredients = listOf(
                    Text("Saba banana"), Text("Brown sugar"), Text("Lumpia wrapper"), Text("Jackfruit")
                ),
                instructions = listOf(
                    Text("Wrap banana and jackfruit"), Text("Roll in sugar"), Text("Fry until golden")
                )
            ),
            Recipe(
                image = ImageData.DrawableRes(R.drawable.bananacue),
                title = "Banana Cue",
                description = "Deep-fried caramelized bananas on skewers.",
                servingSize = "5 sticks",
                cookTime = "10 mins",
                category = listOf("Merienda", "Sweet"),
                ingredients = listOf(
                    Text("Saba banana"), Text("Brown sugar"), Text("Cooking oil"), Text("Skewers")
                ),
                instructions = listOf(
                    Text("Fry bananas"), Text("Add sugar to caramelize"), Text("Skewer and serve")
                )
            )
        )
}
