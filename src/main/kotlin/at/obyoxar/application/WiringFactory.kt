package at.obyoxar.application

import graphql.schema.idl.RuntimeWiring
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces
import javax.inject.Inject

@ApplicationScoped
class WiringFactory {

    @Inject
    lateinit var helloDataFetcher: HelloDataFetcher

    @Produces
    fun wiring(): RuntimeWiring{
        return RuntimeWiring.newRuntimeWiring()
                .type("Query") { typeWiring -> typeWiring
                        .dataFetcher("hello", helloDataFetcher) }
                .build()
    }
}