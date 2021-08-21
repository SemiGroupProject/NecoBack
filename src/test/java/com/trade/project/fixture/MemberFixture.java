package com.trade.project.fixture;

import com.trade.project.member.domain.AddressInfo;
import com.trade.project.member.domain.Member;

public class MemberFixture {
    public static Member MEMBER1 =
            new Member(

                    "user1",
                    "1234",
                    "user1",
                    "010-1111-1111",
                    new AddressInfo(1234, "서울시 도봉로")
            );

    public static Member MEMBER2 =
            new Member(
                    "user2",
                    "5324",
                    "user2",
                    "010-2222-2222",
                    new AddressInfo(42321, "서울시 송파구")
            );

    public static String CHANGE_PASSWORD = "3532gg32r";
 }
