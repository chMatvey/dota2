package ru.pipDota2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.pipDota2.domain.Mem;
import ru.pipDota2.service.MemServiceImpl;
import ru.pipDota2.service.UserService;

import java.io.IOException;

@RestController
public class MemController {
    private final MemServiceImpl memService;
    private final UserService userService;

    @Autowired
    public MemController(final MemServiceImpl memService, final UserService userService){
        this.memService = memService;
        this.userService = userService;
    }

    @RequestMapping("/add/mem")
    @PreAuthorize("hasRole('ADMIN')")
    public Mem loadMem(@RequestParam("user_id") int userId, @RequestParam("file") MultipartFile file) throws IOException {
        return memService.saveMem(Mem.of(file.getBytes(),
                userService.getUserById(userId).orElseThrow(() ->
                new UsernameNotFoundException("user with id: " + userId + " was not found"))));
    }

    @RequestMapping("/get/memes/jpg")
    public ResponseEntity<byte[]> getMemes(){
        byte[] img = memService.getMemById(1).getImg();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
    }

    @RequestMapping("/get/memes")
    public Iterable<Mem> getAllMemes(){
        return memService.getAllMem();
    }
}
