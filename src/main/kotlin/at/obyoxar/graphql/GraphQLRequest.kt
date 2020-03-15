package at.obyoxar.graphql

class GraphQLRequest() {
    lateinit var query: String
    var operationName: String? = null
}