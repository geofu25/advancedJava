package hello.advance.java.trace.logtrace.impl;

import hello.advance.java.trace.TraceId;
import hello.advance.java.trace.TraceStatus;
import hello.advance.java.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX = "<X-";

  private TraceId traceIdHolder; // treaceId 동기화

  @Override
  public TraceStatus begin(String message) {
    syncTraceId();
    TraceId traceId = traceIdHolder;
    return new TraceStatus(traceId, beginLog(traceId, message), message);
  }

  @Override
  public void end(TraceStatus status) { complete(status, null); }

  @Override
  public void exception(TraceStatus status, Exception e) { complete(status, e); }

  private void syncTraceId() {
    if (traceIdHolder == null) {
      traceIdHolder = new TraceId();
    } else {
      traceIdHolder = traceIdHolder.createNextId();
    }
  }

  private Long beginLog(TraceId traceId, String message) {
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return System.currentTimeMillis();
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
    releaseTransId();
  }

  private void releaseTransId(){
      if(traceIdHolder.isFirstLevel()) {
        traceIdHolder = null;
      } else{
        traceIdHolder = traceIdHolder.createPrevId();
      }
  }

  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<level; i++) {
      sb.append((i==level-1) ? "|" + prefix : "|    ");
    }
    return sb.toString();
  }
}
