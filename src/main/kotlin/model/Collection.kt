package model

class Collection(
    private val name: String
) {

    private lateinit var rows: Array<Row>
}