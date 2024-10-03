package vivien.doingthigns.fantasyworldgenerator.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Log request details
        logger.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
        logger.info("Request Headers: {}", request.getHeaderNames());

        // Proceed with the rest of the filter chain
        filterChain.doFilter(request, response);

        // Log response details
        logger.info("Outgoing response status: {}", response.getStatus());
    }
}
