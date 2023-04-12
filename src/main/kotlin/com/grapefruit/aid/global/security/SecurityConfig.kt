package com.grapefruit.aid.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.grapefruit.aid.global.config.FilterConfig
import com.grapefruit.aid.global.filter.JwtRequestFilter
import com.grapefruit.aid.global.security.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val jwtRequestFilter: JwtRequestFilter,
    private val tokenProvider: TokenProvider
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            // user
            .antMatchers(HttpMethod.POST, "/auth/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

            // store
            .antMatchers(HttpMethod.POST, "/store").authenticated()
            .antMatchers(HttpMethod.GET, "/store").authenticated()
            .antMatchers(HttpMethod.PATCH, "/store/{store_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/store/{store_id}").authenticated()

            // seat
            .antMatchers(HttpMethod.POST, "/seat/{store_id}").authenticated()
            .antMatchers(HttpMethod.PATCH, "/seat/{seat_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/seat/{seat_id}").authenticated()
            .antMatchers(HttpMethod.GET, "/seat/{store_id}").authenticated()

            // menu
            .antMatchers(HttpMethod.POST, "/menu/{store_id}").authenticated()
            .antMatchers(HttpMethod.PATCH, "/menu/{menu_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "menu/{menu_id}").authenticated()

            // purchase
            .antMatchers(HttpMethod.POST, "/purchase/{seat_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/purchase/{seat_id}").authenticated()
            .antMatchers(HttpMethod.DELETE, "/purchase/food/{seat_id}").authenticated()

            // image
            .antMatchers(HttpMethod.POST, "/image").authenticated()
            .anyRequest().denyAll()

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))

            .and()
            .apply(FilterConfig(tokenProvider))

            .and().build()



    }
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()
}