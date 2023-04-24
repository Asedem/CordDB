class QueryReader(
    private val statement: String,
    private val inserts: Array<out Any>
) {

    fun evaluateQueryType(): QueryType {
        return QueryType.getByStatement(statement)
    }
}