package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody   //뷰 화면 없이 문자열을 반환하고 싶을 때는 @ResponseBody 사용.
    public String logDemo(HttpServletRequest request){   //HttpServletRequest(자바에서 제공하는 표준 서블릿 규약)로 고객 요청 정보를 받을 수 있음.
        String requestURL = request.getRequestURI().toString();   //고객이 어떤 URL로 요청했는지 알 수 있음.
        myLogger.setRequestURL(requestURL);
        return "ok";
    }
}