package hello.advance.java.app.v0;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    @SneakyThrows
    public void save(String itemId) {
      // 조정 로직
      if (itemId.equals("ex")) {
        throw new IllegalAccessException("예외 발생!");
      }
      sleep(1000);
    }

    private void sleep(int millis) {
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
}
