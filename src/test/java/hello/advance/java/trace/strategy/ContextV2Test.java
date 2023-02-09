package hello.advance.java.trace.strategy;

import hello.advance.java.trace.strategy.code.strategy.ContextV1;
import hello.advance.java.trace.strategy.code.strategy.ContextV2;
import hello.advance.java.trace.strategy.code.strategy.Strategy;
import hello.advance.java.trace.strategy.code.strategy.StrategyLogic1;
import hello.advance.java.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  @Test
  void strategyV1() {
    ContextV2 context = new ContextV2();
    context.execute(new StrategyLogic1());
    context.execute(new StrategyLogic2());
  }

  @Test
  void strategyV2() {
    ContextV2 context = new ContextV2();
    context.execute(new Strategy(){

      @Override
      public void call() {
        log.info("비지니스 로직1 실행");
      }
    });
    context.execute(new Strategy(){

      @Override
      public void call() {
        log.info("비지니스 로직2 실행");
      }
    });
  }

  @Test
  void strategyV3() {
    ContextV2 context = new ContextV2();
    context.execute(() ->  log.info("비지니스 로직1 실행") );
    context.execute(() ->  log.info("비지니스 로직2 실행") );
  }

}
