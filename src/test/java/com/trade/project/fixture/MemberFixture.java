package com.trade.project.fixture;

import com.trade.project.member.application.LoginRequest;
import com.trade.project.member.application.LoginResponse;
import com.trade.project.member.application.ProfileRequest;
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

    public static ProfileRequest PROFILE_REQUEST = new ProfileRequest(
            "010-3212-3242",
            new AddressInfo(24241,
                    "서울시 송파구")
    );

    public static LoginRequest LOGIN_REQUEST = new LoginRequest("user1", "1234");

    public static String JWT_TOKEN = "jwt-data";

    public static LoginResponse LOGIN_RESPONSE = new LoginResponse(JWT_TOKEN);

    /**
     *  회원 LOGIN MVC TEST JSON
     */
    public static String MEMBER_JOIN_ACCOUNT_ID = "junco";
    public static String MEMBER_JOIN_PASSWORD = "1234";

    public static String MEMBER_LOGIN_JSON_1 = "{\n" +
            "\"accountId\"" + ":" + "\"" + MEMBER_JOIN_ACCOUNT_ID + "\""
            + "," + "\"password\"" + ":" + "\"" + MEMBER_JOIN_PASSWORD + "\"" + "}";

    public static String MEMBER_LOGIN_JSON_2 = "{\n" +
            "\"accountId\"" + ":" + "\"" + "xorals2" + "\""
            + "," + "\"password\"" + ":" + "\"" + "1234" + "\"" + "}";

    public static String MEMBER_LOGIN_JSON_3 = "{\n" +
            "\"accountId\"" + ":" + "\"" + "xorals3" + "\""
            + "," + "\"password\"" + ":" + "\"" + "1234" + "\"" + "}";


    public static String MEMBER_JOIN_JSON = "{\n" +
            "    \"accountId\" : \"junco\",\n" +
            "    \"password\" : \"1234\",\n" +
            "    \"name\" : \"이택민\",\n" +
            "    \"phoneNumber\" : \"010-1111-1111\",\n" +
            "    \"addressInfo\" : {\n" +
            "        \"zipNo\" : 1242,\n" +
            "        \"street\" : \"서울시\"\n" +
            "    }\n" +
            "}";

    public static String MEMBER_JOIN_JSON_2 = "{\n" +
            "    \"accountId\" : \"xorals2\",\n" +
            "    \"password\" : \"1234\",\n" +
            "    \"name\" : \"이택민2\",\n" +
            "    \"phoneNumber\" : \"010-2222-2222\",\n" +
            "    \"addressInfo\" : {\n" +
            "        \"zipNo\" : 1242,\n" +
            "        \"street\" : \"서울시\"\n" +
            "    }\n" +
            "}";


    public static String MEMBER_JOIN_JSON_3 = "{\n" +
            "    \"accountId\" : \"xorals3\",\n" +
            "    \"password\" : \"1234\",\n" +
            "    \"name\" : \"이택민3\",\n" +
            "    \"phoneNumber\" : \"010-2222-2222\",\n" +
            "    \"addressInfo\" : {\n" +
            "        \"zipNo\" : 1242,\n" +
            "        \"street\" : \"서울시\"\n" +
            "    }\n" +
            "}";

    public static String MEMBER_PROFILE_UPDATE_JSON = "{\n" +
            "    \"phoneNumber\" : \"010-2222-2222\",\n" +
            "    \"addressInfo\" : {\n" +
            "        \"zipNo\" : 42423,\n" +
            "        \"street\" : \"경기도\"\n" +
            "    }\n" +
            "}";
}
