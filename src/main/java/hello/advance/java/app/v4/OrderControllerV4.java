package hello.advance.java.app.v4;

import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.logtrace.TreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

  private final OrderServiceV4 orderService;

  private final LogTrace trace;

  @GetMapping("/v4/request")
  public String request(String itemId) {

    TraceStatus status = null;

    try {
      status = trace.begin("OrderController.request()");
      orderService.orderItem(itemId);
      trace.end(status);
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}