package com.aslljboilerplate.oauth2example.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Value("${claim.aud}")
    private String claimAud;

    @Value("${jwkSetUri}")
    private String urlJwk;

    private OAuth2ResourceServerProperties resource;

    /**
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.tokenStore(tokenStore());
        resources.resourceId(claimAud);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwkTokenStore(urlJwk);
    }**/


    //AccessTokenConverter is the object that converts the token to an AUthentication
    //This is the object we need to change so that it also takes into consideration the custom details in the token
    //We create a custom implementation of JwtAccessTokenConverter, which also takes into consideration our new details on the token.
    // The simplest way is to extend this class and override the extractAuthentication() method. This method converts the token in an Authentication object
    /**
     @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();

    }

    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE,"/**").hasAuthority("fitnessadmin")
                .anyRequest().authenticated();
    }**/


    //is able to append resource-specific roles to the authorities collection.
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE,"/**").hasAuthority("ROLE_fitnessadmin")
                .anyRequest().authenticated()
                .and()
                //indicate the type of token we want to support
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        //set a custom converter that add add authorities to jwt token on
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                ));
    }

}
