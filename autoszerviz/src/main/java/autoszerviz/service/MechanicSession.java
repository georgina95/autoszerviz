package autoszerviz.service;

import autoszerviz.model.Mechanic;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Service
@SessionScope
public class MechanicSession {
    private Mechanic mechanic;
}