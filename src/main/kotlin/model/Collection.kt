package model

class Collection(
    private val name: String
) {

    private lateinit var dataSets: Array<DataSet>
}