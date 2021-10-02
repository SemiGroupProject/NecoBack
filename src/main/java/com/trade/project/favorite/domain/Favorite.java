package com.trade.project.favorite.domain;

import com.trade.project.common.domain.BaseTimeEntity;
import com.trade.project.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Favorite extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAVORITE_ID")
    private Long id;

}
