package com.trade.project.member.domain;

import com.trade.project.item.domain.Item;
import com.trade.project.item.application.ItemRequest;
import com.trade.project.store.domain.Store;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Embedded
    private AddressInfo addressInfo;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Store store;

    public Member(String accountId, String password, String name, String phoneNumber,
                  AddressInfo addressInfo) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressInfo = addressInfo;
        createStore();
    }

    public Member(Long id, String accountId, String password, String name, String phoneNumber,
                  AddressInfo addressInfo) {
        this.id=id;
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addressInfo = addressInfo;
        createStore();
    }

    // 패스워드 변경
    public void updatePassword(String updatePassword) {
        if(!hasText(updatePassword)) {
            throw new IllegalArgumentException("There is no password to change");
        }

        password = updatePassword;
    }

    // 핸드폰번호 변경
    public void updatePhoneNumber(String updatePhoneNumber) {
        if(!hasText(updatePhoneNumber)) {
            throw new IllegalArgumentException("There is no phone number to change");
        }

        phoneNumber = updatePhoneNumber;
    }

    // 주소정보 변경
    public void updateAddressInfo(AddressInfo updateAddressInfo) {
        if(updateAddressInfo == null) {
            throw new IllegalArgumentException("There is no address info to change");
        }

        addressInfo = updateAddressInfo;
    }

    // 회원 자신의 스토어 생성
    private void createStore() {
        this.store = Store.createStore(this);
    }

    // Item 등록
    public Item createItem(ItemRequest item){
        return Item.createItem(item,this);
    }
}
