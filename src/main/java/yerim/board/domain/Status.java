package yerim.board.domain;

import lombok.Getter;

@Getter
public enum Status {   // 모든 물품에 대한 판매 상태
    HAVING("보유중"), NORMAL_SELLING("일반 판매중")
    , KEEP_SELLING("보관 판매중"), SOLD("판매 완료");

    public String description;

    Status(String description) {
        this.description = description;
    }
}
