package my.app.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.app.models.User;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ThemeResolver;

import java.util.concurrent.CompletableFuture;

@Component
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            String access_token = authorizationHeader.substring(7);

            AsyncHttpClient client = Dsl.asyncHttpClient();

            CompletableFuture<Response> futureResponse = client.prepareGet("https://www.googleapis.com/oauth2/v3/userinfo")
                    .setHeader("Authorization", "Bearer " + access_token)
                    .execute()
                    .toCompletableFuture();

            Response res = futureResponse.get();
            int statusCode = res.getStatusCode();
            String responseBody = res.getResponseBody();

            if (statusCode >= 200 && statusCode < 300) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                String email =  jsonNode.get("email").asText();

                request.setAttribute("email", email);

                return true;
            } else {
                throw new Exception("no token found");
            }
        } catch (Exception e) {
            System.out.println(e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }

        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("extraInfo", "Additional data added in postHandle");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

    }
}
