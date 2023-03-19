package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void rquestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username: {}, age: {}", username, age);

        response.getWriter().write("success");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username: {}, age: {}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username: {}, age: {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username: {}, age: {}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
            // 이 경우에는 int 는 기본적으로 null 을 담을 수 없기 때문에 500 에러가 난다
            // 그래서 Integer 라는 객체 형식으로 받아주면 가능하다.
            // required 라도 null 값이 아니면 통과가 된다 기억할 것
        log.info("username: {}, age: {}", username, age);

        return "ok";
    }

    /*
        defaultValue 는 빈 문자라도 통한다.
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {

        log.info("username: {}, age: {}", username, age);

        return "ok";
    }

    /*
        @RequestParam Map, MultiValueMap
        Map(Key=value)
        MultiValueMap(Key=[value1, value2, ....]) ex (key = userIds, value = [id1, id2])

        만약 파라미터의 값이 1개가 확실하다면 Map 을 쓰지만 그렇지 않다면 MultiValueMap 을 사용한다.
        하지만 대개 파라미터의 값은 하나다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username: {}, age: {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("username: {}, age: {}", helloData.getUsername(), helloData.getAge());


        return "ok";
    }

    /*
        @ModelAttribute 는 생략 가능하다.
        하지만 @RequestParam 도 생략이 가능하기 때문에 혼동할 수 있다.

        스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
        만약 String, int, Integer 와 같은 단순 타입일 경우 @RequestParam
        나머지는 @ModelAttribute(argument resolver 로 지정해둔 타입 외)
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("username: {}, age: {}", helloData.getUsername(), helloData.getAge());


        return "ok";
    }
}
