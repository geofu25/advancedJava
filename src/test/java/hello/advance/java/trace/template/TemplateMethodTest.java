package hello.advance.java.trace.template;

import hello.advance.java.trace.template.code.AbstractTemplateTest;
import hello.advance.java.trace.template.code.SubClassLogic1;
import hello.advance.java.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }

  private void logic1() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비지니스 로직1 실행");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void logic2() {
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info("비지니스 로직2 실행");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }


  /**
   * 템프릿 매서드 패턴 적용
   */
  @Test
  void templateMethodV1() {

    AbstractTemplateTest template1 = new SubClassLogic1();
    AbstractTemplateTest template2 = new SubClassLogic2();
    template1.execute();
    template2.execute();
  }

  @Test
  void templateMethodV2() {

    AbstractTemplateTest template1 = new AbstractTemplateTest() {
      @Override
      protected void call() {
        log.info("비즈니스 로직1 실행");
      }
    };
    AbstractTemplateTest template2 = new AbstractTemplateTest() {
      @Override
      protected void call() {
        log.info("비즈니스 로직2 실행");
      }
    };

    template1.execute();
    template2.execute();

  }

}
