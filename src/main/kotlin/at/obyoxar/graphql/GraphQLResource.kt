package at.obyoxar.graphql

import graphql.ExecutionInput
import graphql.GraphQL
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/graphql")
class GraphQLResource {

    @Inject
    lateinit var graphQL: GraphQL

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun graphQL(request: GraphQLRequest): Response{
        val result = graphQL.execute(ExecutionInput.newExecutionInput().query(request.query).operationName(request.operationName))

        return Response.ok(result.toSpecification()).build()
    }
}