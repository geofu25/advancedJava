package hello.advance.java.app.v3;

import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

  private final OrderRepositoryV3 orderRepository;
  private final LogTrace trace;

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
