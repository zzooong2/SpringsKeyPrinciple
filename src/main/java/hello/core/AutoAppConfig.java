package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 설정 정보가 없어도 자동으로 스프링 빈을 등록해줌
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,
                                               classes= Configuration.class)
)
public class AutoAppConfig {
}
