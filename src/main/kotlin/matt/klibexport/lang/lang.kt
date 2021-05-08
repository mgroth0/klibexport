package matt.klibexport.lang


infix fun <A, B, C> Pair<A, B>.trip(third: C): Triple<A, B, C> {
  return Triple(first, second, third)
}

fun <T> Iterable<T>.applyEach(op: T.()->Unit) = forEach { it.apply(op) }

fun <T> Sequence<T>.onEachApply(op: T.()->Unit) = onEach { it.apply(op) }