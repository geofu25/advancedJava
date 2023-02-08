package hello.advance.java.trace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  public TraceStatus begin(String message) {
    TraceId traceId = new TraceId();
    return new TraceStatus(traceId, beginLog(traceId, message), message);
  }

  public TraceStatus beginSync(TraceId beforeTraceId, String message) {
    TraceId nextId = beforeTraceId.createNextId();
    return new TraceStatus(nextId, beginLog(nextId, message), message);
  }

  private Long beginLog(TraceId traceId, String message) {
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return System.currentTimeMillis();
  }


  public void end(TraceStatus status) {
    complete(status, null);
  }

  public void exception(TraceStatus status, Exception e) {
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e) {
    Long stopTimeMs = System.currentTimeMillis();
    long resultTimeMS = stopTimeMs - status.getStartTimeMs();
    TraceId traceId = status.getTraceId();
    if (e == null) {
      log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMS);
    } else {
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMS, e.toString());
    }
  }

  /* e.g)
  * level=0
  * level=1 | -->
  * level=2 |  |-->
  */
  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<level; i++) {
      sb.append((i==level-1) ? "|" + prefix : "|    ");
    }
    return sb.toString();
  }

}
