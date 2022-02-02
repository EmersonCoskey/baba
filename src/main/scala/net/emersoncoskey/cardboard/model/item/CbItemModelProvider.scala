package net.emersoncoskey.cardboard.model.item

import net.emersoncoskey.cardboard.model.CbModel
import net.emersoncoskey.cardboard.CbMod
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.{ItemModelBuilder, ItemModelProvider}
import net.minecraftforge.common.data.ExistingFileHelper

case class CbItemModelProvider(
	mod   : CbMod,
	gen   : DataGenerator,
	helper: ExistingFileHelper,
	models: Seq[CbModel[ItemModelBuilder]]
) extends ItemModelProvider(gen, mod.ModId, helper) {
	override def registerModels(): Unit = models.foreach(m => m.act.runS(getBuilder(m.name)))
}
