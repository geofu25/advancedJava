package hello.advance.java.app.v1;

import hello.advance.java.trace.HelloTraceV1;
import hello.advance.java.trace.HelloTraceV2;
import hello.advance.java.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

  private final OrderRepositoryV1 orderRepository;
  private final HelloTraceV1 trace;


  public void orderItem(String itemId) {

    TraceStatus status = null;
    try {
      status = trace.begin("OrderService.request()");
      orderRepository.save(itemId);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
