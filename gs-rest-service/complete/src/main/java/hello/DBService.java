package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class DBService {

    @Autowired
    @Qualifier("jdbcOasis")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SQLProperties sqlProperties;

    @Autowired
    private ReportWriter reportWriter;

    @Autowired
    private MailerService mailerService;

    @Autowired
    private JmsTemplate jmsTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Scheduled(initialDelay=1000, fixedRateString = "${interval}" )
    public void runSqls() throws Exception {

//        for (Object sqlEntry : sqlProperties.getSqls().values()) {
//            LinkedHashMap entry = (LinkedHashMap) sqlEntry;
//            try {
//                List<Map<String, Object>> lst = jdbcTemplate.queryForList((String) entry.get("sql"));
//                reportWriter.write(lst, entry.get("reportFile"), entry.get("name"));
//                jmsTemplate.send("mailbox-destination", new MigrationMesaage<MailMessage>((String)entry.get("name"), getMerchants(lst)));
//            }
//            catch (Exception ex){
//            }
//        }
    }

    private String getMerchants(List<Map<String, Object>> lst){
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> m : lst) {
            sb.append(m.get("ACCOUNTNUMBER")).append("\n");
        }
        return sb.toString();
    }

    private void sendEmail(String subject, String body1, String body2){
        if (!StringUtils.isEmpty(body2))
            mailerService.sendEmail(subject, body1 + "\n" + body2);

    }
}
