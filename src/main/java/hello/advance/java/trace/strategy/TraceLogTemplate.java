package hello.advance.java.trace.strategy;

import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceLogTemplate<T> {

  private final LogTrace trace;

  public TraceLogTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public <T> T execute(Callback<T> callback, String message) {

    TraceStatus status = null;
    try {
      status = trace.begin(message);
      // 비즈니스 로직 실행
      T result = callback.call();
      // 비즈니스 로직 종료
      trace.end(status);
      return result;
    } catch(Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}
