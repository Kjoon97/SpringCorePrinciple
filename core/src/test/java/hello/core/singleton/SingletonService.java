package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    //자기 자신을 내부에 private으로 static으로 가지고있음. static으로 선언하면 -> 클래스 레벨에 올라가기 때문에 하나만 존재하게 됨.
    //static이라고 되어있으면 static영역에 하나만 딱 올라간다.

    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
