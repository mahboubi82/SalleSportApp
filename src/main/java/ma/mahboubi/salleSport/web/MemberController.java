package ma.mahboubi.salleSport.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ma.mahboubi.salleSport.entities.Gender;
import ma.mahboubi.salleSport.entities.Member;
import ma.mahboubi.salleSport.repository.MemberRepository;
import ma.mahboubi.salleSport.service.MemberService;
import ma.mahboubi.salleSport.util.FileUploadUtil;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        //Page<Member> memberPage1 = memberRepository.findByLastNameContaining(keyword, PageRequest.of(page, size));
        Page<Member> memberPage = memberRepository.findAllByIs_Active(Boolean.TRUE, keyword, PageRequest.of(page, size));
        model.addAttribute("MembersList", memberPage.getContent());
        model.addAttribute("Pages", new int[memberPage.getTotalPages()]);
        model.addAttribute("TotalPages", memberPage.getTotalPages());
        model.addAttribute("TotalItems", memberPage.getTotalElements());
        model.addAttribute("CurrentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        return "members";
    }

    @SneakyThrows
    @GetMapping("/indexAlert")
    public String indexAlert(Model model,
                             @RequestParam(name = "kw", defaultValue = "") String keyword,
                             @RequestParam(name = "p", defaultValue = "0") int page,
                             @RequestParam(name = "s", defaultValue = "4") int size) {
        //Page<Member> memberPage=memberRepository.findAll(PageRequest.of(page,size));
        //Page<Member> memberPageAlert = memberRepository.findByLastNameContaining(keyword, PageRequest.of(page, size));
        //Page<Member> memberPageAlert = memberRepository.findAllByPayementDateBefore(new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-29"),keyword, PageRequest.of(page,size));
        Page<Member> memberPageAlert = memberRepository.findAllByExpiryDateBetweenAndLastNameContaining(
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01")
                , new Date()
                , keyword
                , PageRequest.of(page, size));

        model.addAttribute("MembersList", memberPageAlert.getContent());
        model.addAttribute("Pages", new int[memberPageAlert.getTotalPages()]);
        model.addAttribute("TotalPages", memberPageAlert.getTotalPages());
        model.addAttribute("TotalItems", memberPageAlert.getTotalElements());
        model.addAttribute("CurrentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());
        return "cardAlert";
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
    public String formMember(Model model) {
        model.addAttribute("member", new Member());
        return "formMember";
    }

    @GetMapping("/cardAlert")
    public String cardAlert() {
        return "cardAlert";
    }

    @GetMapping("/editMember")
    public String editMember(Model model, @RequestParam(name = "id") Long id) {
        Member member = memberRepository.findById(id).get();
        model.addAttribute("member", member);
        return "editMember";
    }

    @PostMapping("/saveMember")
    //@RequestMapping(value = "/saveMember", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveMember(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
                             @RequestParam(value = "photo", required = false) MultipartFile multipartFile,
                             String photos) throws IOException, ParseException {
        if (bindingResult.hasFieldErrors()) return "formMember";
       /*     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date past = format.parse("2023-05-29");
            Date now = new Date();
            System.out.println(TimeUnit.MILLISECONDS.toDays(Long.parseLong(now.getTime() - member.getPayementDate().getTime() + " days ago")));*/
        Calendar calendar = Calendar.getInstance();

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            member.setPhoto(fileName);

            calendar.setTime(member.getPayementDate());
            calendar.add(Calendar.MONTH, 1);
            member.setExpiryDate(calendar.getTime());

            Member saveMember = memberRepository.save(member);
            String upload = "src/main/resources/static/images/" + saveMember.getId();
            FileUploadUtil.SaveFile(upload, fileName, multipartFile);
        } else {
            member.setPhoto(photos);

            calendar.setTime(member.getPayementDate());
            calendar.add(Calendar.MONTH, 1);
            member.setExpiryDate(calendar.getTime());

            memberRepository.save(member);
        }
        //memberRepository.save(member);
        return "redirect:/index?kw=" + member.getLastName();
    }

    @PostMapping("/updateMember")
    @Query("UPDATE Member SET firstName=COALESCE(:firstName, firstName),lastName=COALESCE(:lastName, lastName),cine=COALESCE(:cine, cine),payementDate=COALESCE(:payementDate, payementDate)," +
            "subscriptionDate=COALESCE(:subscriptionDate, subscriptionDate) , phone=COALESCE(:phone, phone),is_Active=COALESCE(:is_Active, is_Active),gender=COALESCE(:gender, gender)," +
            "photo=COALESCE(:photo, photo) WHERE id=:id")
    public String updateMember(@Valid @Param("id") @ModelAttribute("member") Member member, BindingResult bindingResult, @RequestParam(value = "photo", required = false) MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasFieldErrors()) return "formMember";
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            member.setPhoto(fileName);
            Member saveMember = memberRepository.save(member);
            String upload = "src/main/resources/static/images/" + saveMember.getId();

            FileUploadUtil.SaveFile(upload, fileName, multipartFile);
        } else {
            member.setPhoto(null);
            memberRepository.save(member);
        }
        //memberRepository.save(member);
        return "redirect:/index?kw=" + member.getLastName();
    }

    @GetMapping("/profile")
    public String profile() {
        return "takePicture";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder) {
        binder.setDisallowedFields("photo");
    }


}
