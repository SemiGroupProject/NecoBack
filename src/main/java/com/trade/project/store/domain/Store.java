package com.trade.project.store.domain;

import com.trade.project.common.vo.ImageInfo;
import com.trade.project.member.domain.Member;
import com.trade.project.store.application.StoreNameGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    // 상점 이름
    @Column(nullable = false)
    private String name;

    // 상점 소개글
    @Column(length = 1023)
    private String introduction;

    @Embedded
    private ImageInfo imageInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Store(String name, String introduction, ImageInfo imageInfo, Member member) {
        this.name = name;
        this.introduction = introduction;
        this.imageInfo = imageInfo;
        this.member = member;
    }

    // 스토어 생성(정적 팩토리 메서드)
    public static Store createStore(Member member) {
        String name = StoreNameGenerator.createName();
        return new Store(name, "", null, member);
    }

    // 스토어 이름 변경
    public void updateName(String updateName) {
        if(!hasText(updateName)) {
            throw new IllegalArgumentException("There is no name to change");
        }

        name = updateName;
    }

    // 스토어 소개글 변경
    public void updateIntroduction(String updateIntroduction) {
        if(updateIntroduction == null) {
            throw new IllegalArgumentException("There is no introduction to change");
        }
        introduction = updateIntroduction;
    }

    // 스토어 이미지 정보 변경
    public void updateImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }
}
