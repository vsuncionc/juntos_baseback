package base.juntos.base_back.config.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final SecurityProperties securityProperties;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // Permite solicitudes desde un origen específico
        corsConfig.setAllowedOriginPatterns(securityProperties.getAllowedUrlsOrigin());

        // Habilita otros encabezados y métodos CORS según sea necesario
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");

        // Habilita el envío de credenciales (cookies y encabezados de autenticación)
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
