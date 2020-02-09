package texas.sbv.car.social;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import texas.sbv.car.proxy.Box;
import texas.sbv.car.proxy.PageProxy;
import texas.sbv.car.user.User;
import texas.sbv.car.user.UserRepository;
import texas.sbv.car.util.PathEnum;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SocialController {
    @Autowired SocialRepository socialRepository;
    @Autowired UserRepository userRepository;
    @Autowired SocialService socialService;
    @Autowired PageProxy pager;
    @Autowired ThumbRepository thumbRepository;
    @Autowired Box box;


    @GetMapping("/viewList/{pageNo}/{userid}")
    public Map<String, Object> viewList(@PathVariable String pageNo, @PathVariable String userid){
        Map<String, Object> map = new HashMap<>();
        pager.setPageNum(Integer.parseInt(pageNo));
        pager.setPageSize(12);
        pager.paging(socialService.allList());
        int thisPageSize = (pager.getEndRow()+1)-((pager.getPageNum()-1)*pager.getPageSize());
        SocialListDTO[] list = new SocialListDTO[thisPageSize];
        List<SocialListDTO> param = socialService.allList();
        for(int i=0; i<thisPageSize; i++){
            list[i]=param.get(pager.getStartRow()+i);
        }
        map.put("boardList", list);
        if(!userid.equals("guest")){
            map.put("thumbedboard", socialService.thumbed(userid));
        }
        return map;
    }

    @PostMapping("/uploadImg")
    public String uploadImg(MultipartHttpServletRequest uploadFile) {
        Iterator<String> itr =uploadFile.getFileNames();
        String filename = itr.next();
        MultipartFile mfile = uploadFile.getFile(filename);
        String origName=mfile.getOriginalFilename();
        String directory=new SimpleDateFormat("yy-MM-dd").format(new Date()).replace("-", File.separator);
        File serverPath = socialService.makeDir(PathEnum.UPLOAD_PATH.toString()+"\\img", directory);
        serverPath.mkdirs();
        String extension = origName.substring(origName.lastIndexOf(".")+1);
        filename = UUID.randomUUID().toString() +"."+extension;
        File serverFile = socialService.makeFile(serverPath, filename);
        box.add(directory);
        box.add(filename);
        System.out.println(box.get());
        try {
            mfile.transferTo(serverFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "uploadImg";
    }

    @DeleteMapping("/uploadImg")
    public String deleteUploadImg(HttpServletRequest uploadFile){
        Path file= Paths.get(PathEnum.UPLOAD_PATH.toString()+"\\img\\"
                +box.get().get(0)+File.separator+box.get().get(1));
        try {
            Files.delete(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        box.clear();
        return "";
    }

    @PostMapping("/writeContent")
    public Boolean writeContent (@RequestBody SocialWriteDTO param){
        socialService.writeContent(param);
        return true;
    }

    @GetMapping("/loadBoard/{boardSeq}")
    public SocialDetailDTO loadBoard(@PathVariable String boardSeq){
        return socialService.loadBoard(boardSeq);
    }

    @PostMapping("/updateContent/{boardSeq}")
    public Boolean updateContent (@PathVariable String boardSeq, @RequestBody SocialWriteDTO socialWriteDto){
        socialService.updateContent(boardSeq, socialWriteDto);
        return true;
    }
    @GetMapping("/deleteContent/{boardSeq}")
    public Boolean deleteContent(@PathVariable String boardSeq){
        socialService.deleteContent(boardSeq);
        return true;
    }

    @GetMapping("/thumbUp/{boardSeq}/{userid}")
    public Boolean thumbUp(@PathVariable String boardSeq, @PathVariable String userid){
        socialService.thumbUp(boardSeq, userid);
        return true;
    }
    @GetMapping("/thumbDown/{boardSeq}/{userid}")
    public Boolean thumbDown(@PathVariable String boardSeq, @PathVariable String userid){
        socialService.thumbDown(boardSeq, userid);
        System.out.println(userid);
        return true;
    }

    @GetMapping("/thumbed/{boardSeq}/{userid}")
    public Boolean thumbed(@PathVariable String boardSeq, @PathVariable String userid){
        Boolean result = false;
        Social social = socialRepository.findById(Long.parseLong(boardSeq)).get();
        User user = userRepository.findByUserid(userid);
        Thumb thumb = thumbRepository.findByBoardSeqAndUserSeq(social, user);
        if(thumb != null){result = true;}
        System.out.println(userid);
        return result;
    }

}
