package com.k3c1.fitFood.domain.member.service;

import com.k3c1.fitFood.domain.member.dto.CustomOAuth2User;
import com.k3c1.fitFood.domain.member.dto.GoogleResponse;
import com.k3c1.fitFood.domain.member.dto.NaverResponse;
import com.k3c1.fitFood.domain.member.dto.OAuth2Response;
import com.k3c1.fitFood.domain.member.entity.Member;
import com.k3c1.fitFood.domain.member.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());


        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        //Naver, Google, Kakao 선택 후 강제 로그인 로직 구현
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            System.out.println("지원하지 않음");
        }
        String name = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        Member existData = memberRepository.findByName(name);
        String role = "ROLE_USER";
        if (existData == null) {
            Member member = new Member();
            member.update(name, oAuth2Response.getEmail(), role);
            memberRepository.save(member);
        }
        else {
            existData.setName(name);
            existData.setEmail(oAuth2Response.getEmail());
            role = existData.getRole();
            memberRepository.save(existData);
        }
        //나머지 구현
        return new CustomOAuth2User(oAuth2Response, role);
    }
}

