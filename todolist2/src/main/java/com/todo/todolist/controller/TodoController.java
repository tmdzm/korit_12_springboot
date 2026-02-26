package com.todo.todolist.controller;

import com.todo.todolist.dto.TodoRequest;
import com.todo.todolist.dto.TodoResponse;
import com.todo.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 컨트롤러임을 명시
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    // todo 등록 API
    @PostMapping("/{userId}") // @RequestMapping의 주소랑 이어진다.
    public ResponseEntity<TodoResponse> addTodo(@PathVariable Long userId, @RequestBody TodoRequest request){
        TodoResponse response = todoService.createTodo(userId, request);// TodoResponse를 새로 만들어준다. 메소드를 호출해서 대입하는 용
        // 정의되는 영역(TodoService)과 호출되는 부분이 다르다.
    return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // todo 조회 api
    //프론트 엔드와 포스트맨과 연결되는 interface 니까
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAlltodos(){
        return ResponseEntity.ok(todoService.getTodoList()); // todoList를 리턴

    }// 다수의 Todo를 엘리먼트로 가지는 리스트 '를' 엘리먼트로 가지는 객체
}
