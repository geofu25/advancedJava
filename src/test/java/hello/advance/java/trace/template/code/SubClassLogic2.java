package hello.advance.java.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplateTest {

  @Override
  protected void call() {
    log.info("비즈니스 로직2 실행");
  }

}
