package hello.advance.java.trace;

import java.util.UUID;

public class TraceId {

    private String id;
    private int level;

    public TraceId() {
      this.id = createId();
      this.level = 0;
    }

    private TraceId(String id, int level) {
      this.id = createId();
      this.level = 0;
    }

    private  String createId() {
      return UUID.randomUUID().toString().substring(0, 8);
    }

    private TraceId createNextId(){
      return new TraceId(id, level+1);
    }

    private TraceId createPrevId(){
      return new TraceId(id, level-1);
    }

    public boolean isFirstLevel(){
      return level==0;
    }

    public String getId(){
      return id;
    }

    public int getLevel() {
      return level;
    }

}