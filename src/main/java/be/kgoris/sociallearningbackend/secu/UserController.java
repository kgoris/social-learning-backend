package be.kgoris.sociallearningbackend.secu;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import be.kgoris.sociallearningbackend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping( method = GET, value= "all")
    //@PreAuthorize("hasRole('USER')")
    public List<StudentDto> loadAll() {
        List<StudentDto> users = this.studentService.findAll();
/*        for(User user : users){
            user.setSelected(false);
        }*/
        return users;
    }

    @PostMapping
    public void newUser(@RequestBody StudentDto student){
        this.studentService.create(student);
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
    /*
     *  We are not using userService.findByUsername here(we could),
     *  so it is good that we are making sure that the user has role "ROLE_USER"
     *  to access this endpoint.
     */
    @RequestMapping("/whoami")
    public StudentDto user() {
        return studentService.whoIamI();
    }

}
