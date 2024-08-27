package sparta.AIBusinessProject.domain.complain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.AIBusinessProject.domain.complain.service.ComplainService;

@RestController
@RequestMapping("/api/complain")
@RequiredArgsConstructor
public class ComplainController {

    private final ComplainService complainService;
}
