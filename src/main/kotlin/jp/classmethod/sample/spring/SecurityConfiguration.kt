package jp.classmethod.sample.spring

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver
import org.springframework.security.web.SecurityFilterChain

// open にしないと怒られる. fun も
@EnableWebSecurity
open class SecurityConfiguration {

    // 4. 新しい Security Configuration の記載（大体変わらない）
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val customAuthenticationManagerResolver = JwtIssuerAuthenticationManagerResolver(
            "https://cognito-idp.ap-northeast-1.amazonaws.com/ap-northeast-1_mIdVAFBRG"
        )

        http.authorizeHttpRequests {
            it.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .mvcMatchers("/health").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        }.oauth2ResourceServer {
            run {
                it.authenticationManagerResolver(customAuthenticationManagerResolver)
            }
        }

        return http.build()
    }

    @Bean
    open fun mapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
        return mapper
    }
}