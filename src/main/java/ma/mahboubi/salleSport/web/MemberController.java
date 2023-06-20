package ma.mahboubi.salleSport.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.mahboubi.salleSport.entities.Member;
import ma.mahboubi.salleSport.repository.MemberRepository;
import ma.mahboubi.salleSport.service.MemberService;
import ma.mahboubi.salleSport.util.FileUploadUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberRepository memberRepository;
    private MemberService memberService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "kw", defaultValue = "") String keyword,
                        @RequestParam(name = "p", defaultValue = "0") int page,
                        @RequestParam(name = "s", defaultValue = "4") int size) {

        //Page<Member> memberPage=memberRepository.findAll(PageRequest.of(page,size));
        Page<Member> memberPage = memberRepository.findByLastNameContaining(keyword, PageRequest.of(page, size));
        model.addAttribute("MembersList", memberPage.getContent());
        model.addAttribute("Pages", new int[memberPage.getTotalPages()]);
        model.addAttribute("TotalPages", memberPage.getTotalPages());
        model.addAttribute("TotalItems", memberPage.getTotalElements());
        model.addAttribute("CurrentPage", page);
        model.addAttribute("keyword", keyword);
        return "members";
    }
    //@GetMapping("/index2")
    public String index2(Model model,
                         @RequestParam(name = "kw", defaultValue = "") String keyword,
                         @RequestParam(name = "p", defaultValue = "0") int page,
                         @RequestParam(name = "s", defaultValue = "6") int size,
                         @RequestParam(name = "sortField", defaultValue = "id") String field,
                         @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir) {
        Page<Member> pages = memberService.findMemberWithSort(field, sortDir, page);
        List<Member> membersList = pages.getContent();
        //Page<Member> memberPage = memberRepository.findByLastNameContaining(keyword, PageRequest.of(page, size));
        model.addAttribute("MembersList", membersList);
        model.addAttribute("Pages", new int[pages.getTotalPages()]);
        model.addAttribute("TotalPages", pages.getTotalPages());
        model.addAttribute("TotalItems", pages.getTotalElements());
        model.addAttribute("CurrentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "members";
    }

    @GetMapping("/deleteMember")
    public String deleteMember(Long id,
                               @RequestParam(name = "kw", defaultValue = "") String keyword,
                               @RequestParam(name = "p", defaultValue = "0") int page
    ) {
        memberRepository.deleteById(id);
        return "redirect:/index?p=" + page + "&kw=" + keyword;
    }
    @GetMapping("/formMember")
    public String formMember(Model model){
        model.addAttribute("member",new Member());
        return "formMember";
    }
    @PostMapping("/saveMember")
    //@RequestMapping(value = "/saveMember", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveMember( @Valid @ModelAttribute("member")Member member, BindingResult bindingResult, @RequestParam(value = "photo",required = false)MultipartFile multipartFile) throws IOException {
       if (bindingResult.hasFieldErrors()) return "formMember";
        if (!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            member.setPhoto(fileName);
           Member saveMember=memberRepository.save(member);
            String upload= "src/main/resources/static/images/" + saveMember.getFirstName();

            FileUploadUtil.SaveFile(upload, fileName, multipartFile);
        }  else{
                member.setPhoto(null);
                memberRepository.save(member);
            }
        //memberRepository.save(member);
        return "redirect:/index?kw=" + member.getLastName();
    }
    @GetMapping("/profile")
    public String profile(){
        return "takePicture";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder){
        binder.setDisallowedFields("photo");
    }


}
