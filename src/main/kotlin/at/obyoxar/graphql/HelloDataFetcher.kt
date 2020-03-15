package at.obyoxar.graphql

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import io.quarkus.security.Authenticated
import io.quarkus.security.identity.SecurityIdentity
import io.smallrye.jwt.auth.principal.DefaultJWTCallerPrincipal
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.SecurityContext

@ApplicationScoped
class HelloDataFetcher: DataFetcher<String> {

    @Inject
    lateinit var securityIdentity: SecurityIdentity

    @Authenticated
    override fun get(environment: DataFetchingEnvironment): String {
        return "Hello ${securityIdentity.principal.name}"
    }
}