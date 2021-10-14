package be.kgoris.sociallearningbackend.secu;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping( value = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE )
@RequiredArgsConstructor
public class UserController {


    private final StudentService studentService;

    @RequestMapping( method = GET, value = "{userId}" )
    public StudentDto loadById(@PathVariable Integer userId ) {
        return this.studentService.findById( userId );
    }

    @GetMapping("/findbylogin/{login}")
    public StudentDto findByUsername(@PathVariable(name="login") String login){
        return studentService.findDtoByUsername(login);
    }

    @GetMapping("/linkObserved")
    public void linkObervedUserOnAUser(@RequestParam(name="currentUsername") String currentUsername,
                                       @RequestParam(name="observedUsername") String observedUsername){
        studentService.linkObervedUserOnAUser(currentUsername, observedUsername);
    }

    @RequestMapping( method = GET, value= "all")
    //@PreAuthorize("hasRole('USER')")
    public List<StudentDto> loadAll() {
        List<StudentDto> users = this.studentService.findAll();
/*        for(User user : users){
            user.setSelected(false);
        }*/
        return users;
    }

    @PutMapping
    public void updateUser(@RequestBody StudentDto student){
        this.studentService.update(student);
    }

    @RequestMapping( method = GET, value= "/reset-credentials")
    public ResponseEntity<Map> resetCredentials() {
        //this.userService.resetCredentials();
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }

}
