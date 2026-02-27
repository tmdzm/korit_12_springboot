import lombok.Builder;
import lombok.ToString;

// TODO: Lombok의 @Builder와 @ToString 애너테이션을 추가하시오.
@Builder
@ToString // 왜 작동 안하지,  annotationProcessor 'org.projectlombok:lombok:1.18.42'를 안넣어서
class User {
    private String username;
    private String email;
    private int age;

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

public class UserFactory {
    public static void main(String[] args) {
        // TODO: 빌더 패턴을 사용하여 username="user1", email="user1@example.com", age=25 인 User 객체를 생성하고 출력하시오.
        User user1 = new User("user1", "user1@example.com", 25);
        System.out.println(user1);

        // TODO: 빌더 패턴을 사용하여 username="user2", email="user2@example.com" 인 User 객체를 생성하고 출력하시오.
        User user2 = new User("user2", "user2@example.com");
        System.out.println(user2);
    }
}