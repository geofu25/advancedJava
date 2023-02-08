package hello.advance.java;

import hello.advance.java.trace.logtrace.LogTrace;
import hello.advance.java.trace.logtrace.TreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new TreadLocalLogTrace();
  }
}
