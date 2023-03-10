package hello.advance.java.trace.strategy;

import hello.advance.java.trace.strategy.code.template.Callback;
import hello.advance.java.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TeamplateCallbackTest {

  @Test
  void callbackV1() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비즈니스 로직 1 실행");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("비즈니스 로직 2 실행");
      }
    });
  }

  @Test
  void callbackV2() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("비즈니스 로직 1 실행"));
    template.execute(() -> log.info("비즈니스 로직 2 실행"));
  }


}
