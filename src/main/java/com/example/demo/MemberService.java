package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public String signup(MemberResDto dto){

        Member member = new Member(dto.getEmail(), dto.getPassword(), dto.getAge());

        memberRepository.save(member);

        return "저장 완료";
    }

    List<MemberResDto> getMember(){
        List<MemberResDto> list= memberRepository.findAll()
                .stream()
                .map(o->new MemberResDto(o.getEmail(),o.getPassword(),o.getAge()))
                .collect(Collectors.toList());

        return list;
    }

    //아이디 별로 유저 조회
     Optional<Member> getUserById(Long id) {

        Optional <Member> memberData=memberRepository.findById(id);

        return memberData;


    }

    //수정
    public Member updateMember(Long id, Member member) {
        Optional <Member> memberData=memberRepository.findById(id);

        Member newMember=memberData.get();

        //정보 수정: 더 단순화 할 수 있는 방법?
        newMember.setEmail(member.getEmail());
        newMember.setPassword(member.getPassword());
        newMember.setAge(member.getAge());

        memberRepository.save(newMember);

        return newMember;
    }

    //삭제
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
