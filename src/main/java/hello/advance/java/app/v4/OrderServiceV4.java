package hello.advance.java.app.v4;

import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.logtrace.TreadLocalLogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

  private final OrderRepositoryV4 orderRepository;
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
