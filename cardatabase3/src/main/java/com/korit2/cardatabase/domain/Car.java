package com.korit2.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Schema(description = "자동차 엔티티 모델")
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "자동 생성되는 고유 ID",example = "1")
    private Long id;

    @NonNull
    @Schema(description = "제조사 브랜드",example = "현대")
    private String brand;

    @NonNull
    @Schema(description = "자동차 모델명",example = "펠리세이드")
    private String model;

    @NonNull
    @Schema(description = "자동차 생상",example = "빨강")
    private String color;

    @NonNull
    @Schema(description = "자동차 등록번호",example = "123가4567")
    private String registrationNumber;

    @NonNull
    @Schema(description = "자동차 연식",example = "2026")
    private int modelYear;

    @NonNull
    @Schema(description = "자동차 가격",example = "100000")
    private int price;

    @ManyToOne
    @JoinColumn(name = "owner")     // 컬럼명을 제가 지었습니다.
    @NonNull
    @JsonIgnore
    private Owner owner;            // @NonNull이 없으니까 얘는 옵셔널이라고 봐야겠네요.
}