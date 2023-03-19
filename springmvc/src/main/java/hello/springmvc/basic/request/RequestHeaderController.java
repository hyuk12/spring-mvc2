package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletRequest response,
                          HttpMethod httpMethod, // HTTP 메서드를 조회한다.
                          Locale locale, // Locale 정보를 조회한다
                          @RequestHeader MultiValueMap<String, String> headerMap, // 모든 HTTP 헤더를 MultivalueMap 형식으로 조회한다
                          // Map 과 유사한데 하나의 키에 여러값을 받을 수 있다.
                          // HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
                          @RequestHeader("host") String host, // 특정 HTTP 헤더를 조회한다. 속성 필수값 여부 기본값 속성은 default value
                          @RequestHeader(value = "myCookie", required = false) String cookie
                          // 특정 쿠키를 조회한다 속성은 필수값 여부는 required 기본 값은 default value
    ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }
}