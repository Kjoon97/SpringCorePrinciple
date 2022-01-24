package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider; //MyLogger를 찾아볼 수 있는, DL을 할 수 있는 애가 주입됨.

    @RequestMapping("log-demo")
    @ResponseBody   //뷰 화면 없이 문자열을 반환하고 싶을 때는 @ResponseBody 사용.
    public String logDemo(HttpServletRequest request){   //HttpServletRequest(자바에서 제공하는 표준 서블릿 규약)로 고객 요청 정보를 받을 수 있음.
        String requestURL = request.getRequestURI().toString();   //고객이 어떤 URL로 요청했는지 알 수 있음.
        MyLogger myLogger = myLoggerProvider.getObject();    //필요한 시점에 myLogger를 받음.(HTTP리퀘스트가 발생한 이후여서 괜찮음)
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";
    }
}
