package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${spring.mail.to}")
    private String mailTo;

    @Value("${spring.mail.from}")
    private String mailFrom;

    @Autowired
    private MailerService mailerService;

    @Autowired
    private DBService dbService;

    @RequestMapping("/greeting")
    public Map<String, Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        Greeting greeting =  new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
        HashMap<String, Greeting> hashMap = new HashMap<>();
        hashMap.put("test", greeting);
        return hashMap;
    }

    @RequestMapping("/dbContents")

    public List<String> dbContents() throws Exception {
        dbService.runSqls();
        mailerService.sendEmail("test" , "Test Email");
        return null;
    }

    @RequestMapping(value = "/testpost", method = RequestMethod.POST)
    @ResponseBody()
    public String WriteSome(@RequestBody String str){
        System.out.println(str);
        return "Hello there";
    }

}
