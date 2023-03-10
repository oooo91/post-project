package com.portfolio.postproject.user.dto.oauth;

import com.portfolio.postproject.user.entity.DiaryUser;
import com.portfolio.postproject.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userId;
    private String userName;
    private String userEmail;

    //어떤 소셜인지, pk값, 유저의 속성
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .userId((String) attributes.get("sub"))
                .userEmail((String) attributes.get("email"))
                .userName((String) attributes.get("name"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public DiaryUser toEntity() {
        return DiaryUser.builder()
                .id(userId)
                .userEmail(userEmail)
                .userName(userName)
                .createdAt(LocalDateTime.now())
                .level(false)
                .userStatus(UserStatus.STATUS_ACTIVE.toString())
                .build();
    }
}
