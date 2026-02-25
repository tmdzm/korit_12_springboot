package com.todolist.tl;

import com.todolist.tl.domain.Todo;
import com.todolist.tl.domain.TodoRepository;
import com.todolist.tl.domain.User;
import com.todolist.tl.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class TlApplication implements CommandLineRunner {

    public TlApplication(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(TlApplication.class, args);
	}

	private final TodoRepository todoRepository;
	private final UserRepository userRepository;

	public void run(String... args) throws Exception {
		//userRepository.save(new User("user","user","User"));
		User user1 = new User("김일","5a697c6768fcb4f363874b4d73c517a6e7f8932d23c31b6ea52bebd2c3f4aa05","USER"); //1234
		User user2 = new User("김이","7890","ADMIN");
		userRepository.saveAll(Arrays.asList(user1,user2));

		Todo todo1 = new Todo("SQLD",user1);
		Todo todo2 = new Todo("자습",user2);
		todoRepository.save(todo1);
		todoRepository.save(todo2);
	}
}
