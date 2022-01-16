package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)  //AppConfig를 제외하기 위해서.
)
public class AutoAppConfig {
}
//@Component가 붙은 애들을 컨테이너에 다 빈으로 자동등록해줌. -의존관계는 어떻게 설정하나?
//@Autowired를 통해서 타입을 보고 의존관계를 주입해준다.