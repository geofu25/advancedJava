package hello.advance.java.trace.hellotrace;

import hello.advance.java.trace.TraceStatus;
import org.junit.jupiter.api.Test;


public class HelloTraceV1Test {

  @Test
  void begin_end(){
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
  }

  @Test
  void begin_exception() {
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");
    trace.exception(status, new IllegalStateException());
  }
}