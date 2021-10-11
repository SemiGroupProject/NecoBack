package com.trade.project.item.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    private String categoryName;

    private int level;

    private int parent;

    public Category(Long id, String categoryName, int level, int parent) {
        this.id = id;
        this.categoryName = categoryName;
        this.level = level;
        this.parent = parent;
    }
}
