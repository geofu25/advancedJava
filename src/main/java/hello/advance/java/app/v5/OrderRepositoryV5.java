package hello.advance.java.app.v5;

import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.strategy.Callback;
import hello.advance.java.trace.strategy.TraceLogTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

  private final TraceLogTemplate template;

  public OrderRepositoryV5(LogTrace trace) {
    this.template = new TraceLogTemplate(trace);
  }

    @SneakyThrows
    public void save(String itemId) {
      template.execute(() -> {
        if (itemId.equals("ex")) {
          try {
            throw new IllegalAccessException("예외 발생!");
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        }
        sleep(1000);
        return null;
      } ,"OrderRepository.request()");
    }

    private void sleep(int millis) {
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
