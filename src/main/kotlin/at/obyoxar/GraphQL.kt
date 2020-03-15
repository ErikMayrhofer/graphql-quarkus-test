package at.obyoxar

import graphql.GraphQL
import graphql.GraphQLError
import io.quarkus.security.identity.SecurityIdentity
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

class GraphQLRequest() {
    lateinit var query: String
}

class GraphQLResponse(data: Map<String, Any>?, val errors: List<GraphQLError> = listOf()) {
    val data: Map<String, Any> = data ?: mapOf()
}

@Path("/graphql")
class GraphQL {

    @Inject
    lateinit var graphQL: GraphQL

    @Inject
    lateinit var jsonWebToken: JsonWebToken

    @Inject
    lateinit var securityIdentity: SecurityIdentity

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun graphQL(request: GraphQLRequest): Response{
        val result = graphQL.execute(request.query)
        println("Query by: ${securityIdentity.principal} ${jsonWebToken}")

        return Response.ok(GraphQLResponse(result.toSpecification(), result.errors)).build()
    }
}