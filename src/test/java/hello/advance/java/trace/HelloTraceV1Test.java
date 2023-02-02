package hello.advance.java.trace;

import static org.junit.jupiter.api.Assertions.*;

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