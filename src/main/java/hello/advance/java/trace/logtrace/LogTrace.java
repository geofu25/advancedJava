package hello.advance.java.trace.logtrace;

import hello.advance.java.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);
}
