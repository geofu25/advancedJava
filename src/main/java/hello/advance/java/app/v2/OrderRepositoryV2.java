package hello.advance.java.app.v2;

import hello.advance.java.trace.hellotrace.HelloTraceV2;
import hello.advance.java.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

  private final HelloTraceV2 trace;

    @SneakyThrows
    public void save(String itemId, TraceStatus status) {

      try {
        status = trace.beginSync(status.getTraceId(), "OrderRepository.request()");
        // 조정 로직
        if (itemId.equals("ex")) {
          throw new IllegalAccessException("예외 발생!");
        }
        sleep(1000);
        trace.end(status);

      } catch (Exception e) {
        trace.exception(status, e);
        throw e;
      }

    }

    private void sleep(int millis) {
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
