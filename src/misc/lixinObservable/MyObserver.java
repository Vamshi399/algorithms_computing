package misc.lixinObservable;
// Interface MyObserver simulates interface java.util.Observer for better understanding
// the logic of the event-driven programming paradigm.

public interface MyObserver {
  public void update(MyObservable o, Object message);
}