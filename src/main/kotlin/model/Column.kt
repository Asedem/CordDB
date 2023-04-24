package model

abstract class Column<T> {

    abstract fun toColumnEntry(value: T): String

    abstract fun toColumnValue(entry: String): T
}