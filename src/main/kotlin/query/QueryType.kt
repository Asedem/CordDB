enum class QueryType(
    private val token: String
) {
    INSERT("INSERT"),
    DELETE("DELETE"),
    UPDATE("UPDATE"),
    SELECT("SELECT");

    companion object {
        fun getByStatement(statement: String): QueryType = values().first { statement.startsWith(it.token) }
    }
}