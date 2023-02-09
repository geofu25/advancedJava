package hello.advance.java.app.v2;

import hello.advance.java.trace.hellotrace.HelloTraceV2;
import hello.advance.java.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final HelloTraceV2 trace;


  public void orderItem(String itemId, TraceStatus status) {

    try {
      status = trace.beginSync(status.getTraceId(), "OrderService.request()");
      orderRepository.save(itemId, status);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
