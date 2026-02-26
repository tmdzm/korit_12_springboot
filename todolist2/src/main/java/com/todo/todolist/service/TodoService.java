package com.todo.todolist.service;

import com.todo.todolist.dto.TodoRequest;
import com.todo.todolist.dto.TodoResponse;
import com.todo.todolist.entity.Todo;
import com.todo.todolist.entity.User;
import com.todo.todolist.repository.TodoRepository;
import com.todo.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 여러개의 리포지토리를 조립해서 원하는 결과를 뽑아내게 만들어준다.
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    //post 요청을 하는 비지니스 로직 작성
    @Transactional // springframework
    public TodoResponse createTodo(Long userId, TodoRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없다."));
                //orElseThrow 없으면 에러로 던짐

        // DTO -> entity 변환 , 근데 왜 미리 entity가 아니라 record로 시작일까
        Todo todo = new Todo(request.content(),user); // 새로운 Todo 객체를 만들었다, 만들어지면 isCompleted = false가 적용된다.
        Todo savedTodo = todoRepository.save(todo); // 레파지토리에 저장 = db에 저장
        // 이 시점까지 todo의 id는 없다. 저장하고 난 뒤 생긴다.

        //저장 완료됐다는 표시를 해주는 것
        // Entity -> DTO로 변호나해서 Controller로 넘겨줄것이다.
        return new TodoResponse(savedTodo.getId(),savedTodo.getContent(),savedTodo.isCompleted());
    }


    // GET 요청을 하는 비지니스 로직 작성

    @Transactional(readOnly = true) // jakarta 아님
    public List<TodoResponse> getTodoList(){
        return todoRepository.findAll().stream()
                .map(todo -> new TodoResponse(todo.getId(),todo.getContent(),todo.isCompleted()))
                                                // stream api를 쓰는 이유는 map함수를 적용하기
                                                //map() 리스트의 요소 하나하나에 함수를 적용하게 하는 함수... 라고 한다.
                .collect(Collectors.toList());
    }

}
