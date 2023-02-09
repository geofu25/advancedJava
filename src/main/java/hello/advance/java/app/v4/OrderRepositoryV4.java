package hello.advance.java.app.v4;

import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private final LogTrace trace;

    @SneakyThrows
    public void save(String itemId) {

      AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
        @Override
        protected Void call()  {
          // 조정 로직
          if (itemId.equals("ex")) {
            try {
              throw new IllegalAccessException("예외 발생!");
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e);
            }
          }
          sleep(1000);
          return null;
        }
      };

      template.execute("OrderRepository.request()");
    }

    private void sleep(int millis) {
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
