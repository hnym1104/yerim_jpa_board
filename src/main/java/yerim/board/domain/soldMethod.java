package yerim.board.domain;

import lombok.Getter;

@Getter
public enum soldMethod {   // 판매 방식
    NORMAL_SOLD("일반 판매"), KEEP_SOLD("보관 판매");

    private String description;

    soldMethod(String description) {
        this.description = description;
    }
}
