package lv.javaguru.java2.oddJobs.core.services.exit;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExitMenuService {

    public void execute() {

        System.out.println("See you later, by!");
        System.exit(0);
    }
}
