package matt.klibexport.lang

import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract


infix fun <A, B, C> Pair<A, B>.trip(third: C): Triple<A, B, C> {
  return Triple(first, second, third)
}

fun <T> Iterable<T>.applyEach(op: T.()->Unit) = forEach { it.apply(op) }

fun <T> Sequence<T>.onEachApply(op: T.()->Unit) = onEach { it.apply(op) }

inline fun <T: Any> T.alsoPrintln(op: T.()->String): T {
  contract {
	callsInPlace(op, EXACTLY_ONCE)
  }
  println(op.invoke(this))
  return this
}