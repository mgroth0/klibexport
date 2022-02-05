package matt.klibexport.klibexport

import matt.klib.log.warn
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

val DO_NOT_EXPORT = "until YouTrack issue is fully resolved"

interface Identified {
  val id: Int
}

interface Named {
  var name: String

}

open class Unique(
  open var name: String,
  override var id: Int
): Identified {
  override fun toString() =
	"${this::class.simpleName} $id: $name"
}

fun new_id(
  vararg against: List<Identified>
): Int {
  warn("what if i delete the highest? i need to keep a record of nextID instead of this")
  return against.flatMap { it }.maxOf { it.id } + 1
}


@ExperimentalContracts
inline fun <T: Any> T.go(block: (T)->Unit) {
  contract {
	callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  block(this)
}


@ExperimentalContracts
inline fun <T: Any> T.applyIt(block: T.(T)->Unit): T {
  contract {
	callsInPlace(block, InvocationKind.EXACTLY_ONCE)
  }
  block(this)
  return this
}

@ExperimentalContracts
fun <T: Any, R> T.search(
  getTarget: T.()->R?,
  getNext: T.()->T?
): R? {
  contract {
	callsInPlace(getTarget, InvocationKind.AT_LEAST_ONCE)
	callsInPlace(getNext, InvocationKind.UNKNOWN)
  }
  var next: T? = this
  do {
	next!!.getTarget()?.let {
	  return it
	}
	next = next.getNext()
  } while (next != null)
  return null
}


@ExperimentalContracts
fun <T: Any> T.searchDepth(
  getNext: T.()->T?
): Int {
  contract {
	callsInPlace(getNext, InvocationKind.AT_LEAST_ONCE)
  }
  var i = -1
  var next: T? = this
  do {
	next = next!!.getNext()
	i++
  } while (next != null)
  return i
}


fun <T: Any> T.ifIs(other: Any?) = when {
  this != other -> null
  else          -> this
}

fun <T: Any> T.ifIsNot(other: Any?) = when (other) {
  this -> null
  else -> this
}
typealias B = Boolean
typealias S = String
typealias I = Int
typealias D = Double


infix fun <T> MutableCollection<T>.setAll(c: Collection<T>) {
  /*when (this) {
    is ObservableList<T>
  }*/
  clear()
  c.forEach { add(it) }
}


fun <E> Collection<E>.allUnique(): Boolean {
  when (this) {
	is List<E> -> {
	  forEachIndexed { index1, t1 ->
		for (t2 in subList(index1 + 1, size)) {
		  if (t1 == t2) {
			return false
		  }
		}
	  }
	  return true
	}
	is Set<E>  -> {
	  return true
	}
	else       -> return toList().allUnique()
  }
}

interface DSL

fun <E> MutableCollection<E>.addIfNotIn(e: E): Boolean {
  return if (e in this) {
	false
  } else {
	add(e)
	true
  }
}