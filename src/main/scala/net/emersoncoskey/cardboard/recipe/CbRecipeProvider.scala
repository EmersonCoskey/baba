package net.emersoncoskey.cardboard.recipe

import net.emersoncoskey.cardboard.CbMod
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.{FinishedRecipe, RecipeProvider}

import java.util.function.Consumer

case class CbRecipeProvider(mod: CbMod,gen: DataGenerator, recipes: Seq[CbRecipe]) extends RecipeProvider(gen) {
	override def buildCraftingRecipes(finishedRecipeConsumer: Consumer[FinishedRecipe]): Unit =
		recipes.foreach(_.save(finishedRecipeConsumer, mod))
}
