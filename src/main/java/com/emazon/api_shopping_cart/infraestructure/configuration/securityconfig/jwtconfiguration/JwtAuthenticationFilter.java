package com.emazon.api_shopping_cart.infraestructure.configuration.securityconfig.jwtconfiguration;

import com.emazon.api_shopping_cart.infraestructure.configuration.securityconfig.UserDetailService;
import com.emazon.api_shopping_cart.infraestructure.exceptionhandler.ExceptionResponse;
import com.emazon.api_shopping_cart.infraestructure.util.ConstantsConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(ConstantsConfig.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith(ConstantsConfig.BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authHeader.substring(ConstantsConfig.SEVEN_LETTERS);
            UserDetails user = userDetailService.loadUserByUsername(jwt);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, jwt,
                    user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }  catch (Exception ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(ConstantsConfig.APPLICATION_JSON);
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(
                    ConstantsConfig.TOKEN_INVALID,
                    HttpStatus.UNAUTHORIZED.toString()
            )));
            return;
        }
        filterChain.doFilter(request, response);
    }
}


