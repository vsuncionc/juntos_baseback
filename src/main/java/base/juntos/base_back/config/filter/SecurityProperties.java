package base.juntos.base_back.config.filter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class SecurityProperties {
    @Value("${security.enabled:false}")
    private Boolean enabled;
    @Value("#{'${security.exclude-path-patterns}'.split(',')}")
    private List<String> excludePathPatterns;
    @Value("#{'${security.allowed-urls-origin}'.split(',')}")
    private List<String> allowedUrlsOrigin;
}
