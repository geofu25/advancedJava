package hello.advance.java.trace.threadlocal;

import hello.advance.java.trace.threadlocal.code.ThreadLocalFieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalFieldServiceTest {

  private ThreadLocalFieldService fieldService = new ThreadLocalFieldService();

  @Test
  void Field() {
    log.info("main start");
    Runnable userA = () -> {
      fieldService.logic("userA");
    };
    Runnable userB = () -> {
      fieldService.logic("userB");
    };

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(100);
    threadB.start();
    sleep(2000);
  }

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
