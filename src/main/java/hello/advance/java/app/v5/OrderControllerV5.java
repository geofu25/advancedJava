package hello.advance.java.app.v5;

import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.strategy.TraceLogTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderService;
  private final TraceLogTemplate template;

  public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
    this.orderService = orderService;
    this.template = new TraceLogTemplate(trace);
  }

  @GetMapping("/v5/request")
  public String request(String itemId) {
    return (String) template.execute(() -> {
      orderService.orderItem(itemId);
      return "ok";
    }, "OrderController.request()");
  }
}
