package com.todolist.tl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    private boolean isCompleted = false;

    @ManyToOne (fetch = FetchType.LAZY) // 이건 뭘까
    @JsonIgnore
    @JoinColumn(name = "user_id")// 여기 name은 새 컬럼의 이름을 뭐로 할거냐를 묻는것이다.
    private User user;

    public Todo(String content, User user) {
        this.content = content;
        this.user = user;
    }
}
