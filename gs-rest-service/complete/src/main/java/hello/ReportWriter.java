package hello;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Service
public class ReportWriter {

    public void write(List<Map<String, Object>> lst, Object fileName, Object sqlName) throws IOException {
        if (lst.size() > 0) {
            PrintWriter pw = new PrintWriter((String) fileName);
            for (Map<String, Object> m : lst) {
                pw.println(m.get("ACCOUNTNUMBER"));
            }
            pw.println("--Duplicates found for --" + sqlName);
            pw.close();
        }
    }
}
