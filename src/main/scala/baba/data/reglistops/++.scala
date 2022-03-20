package baba.data.reglistops

import baba.data.{#:, RegList, RNil}
import baba.syntax.reglist._
import net.minecraftforge.registries.IForgeRegistryEntry

trait ++[L <: RegList, O <: RegList] {
	type Result <: RegList

	def apply(l: L, o: O): Result
}

object ++ {
	def apply[L <: RegList, O <: RegList](implicit plus: ++[L, O]): Aux[L, O, plus.Result] = plus

	type Aux[L <: RegList, O <: RegList, R <: RegList] = ++[L, O] { type Result = R }

	implicit def basic[A <: IForgeRegistryEntry[A], O <: RegList]: Aux[A #: RNil, O, A #: O] =
		new ++[A #: RNil, O] {
			type Result = A #: O

			override def apply(l: A #: RNil, o: O): Result = l.head #: o
		}

	implicit def inductive[A <: IForgeRegistryEntry[A], L <: RegList, O <: RegList](implicit plus: L ++ O): Aux[A #: L, O, A #: plus.Result] =
		new ++[A #: L, O] {
			override type Result = A #: plus.Result

			override def apply(l: A #: L, o: O): Result = l.head #: plus(l.tail, o)
		}
}
