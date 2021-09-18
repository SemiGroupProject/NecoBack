package com.trade.project.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageRepository  extends JpaRepository<ItemImage, Long> {
    void deleteAllByItemId(Long id);
}
