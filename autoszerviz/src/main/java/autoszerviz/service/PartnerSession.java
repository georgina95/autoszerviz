package autoszerviz.service;

import autoszerviz.model.Partner;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Service
@SessionScope
public class PartnerSession {
    private Partner partner;
}