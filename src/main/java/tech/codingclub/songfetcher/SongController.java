package tech.codingclub.songfetcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SongController {

    @GetMapping("/fetch/{name}")
    public String getSongsInfo(@PathVariable(value = "name") String name, Model model){
        WikiFetcher wikiFetcher = new WikiFetcher(name);
        WikiResult wikiResult = wikiFetcher.Fetch();
        model.addAttribute("IMAGE",wikiResult.getImg_src());
        model.addAttribute("DESCRIPTION",wikiResult.getTxt_result());
        return "songsHTML";
    }
}
