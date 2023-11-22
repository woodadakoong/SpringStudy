package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class BoardController {
    private final MemberService memberService;


    @PostMapping("/user")
    public String signup(@RequestBody MemberResDto dto){
        return memberService.signup(dto);
    }

    @GetMapping("/user")
    public List<MemberResDto> getMember(){
        return memberService.getMember();
    }


    //아이디 별로 회원 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id){
        Optional <Member> memberOptional=memberService.getUserById(id);

        if (memberOptional.isPresent())
            return ResponseEntity.ok(memberOptional.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 회원이 존재하지 않습니다");
    }

    //수정
    @PutMapping("/user/{id}")
    public String updateMember(@PathVariable Long id,@RequestBody Member member){
        try{
            ResponseEntity.status(HttpStatus.CREATED).body(memberService.updateMember(id,member));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //삭제
    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable("id") long id){
        try{
            memberService.deleteMember(id);
            ResponseEntity.noContent();
        }catch(Exception e){
            e.printStackTrace();
        }


        return "삭제 완료";
    }

}
