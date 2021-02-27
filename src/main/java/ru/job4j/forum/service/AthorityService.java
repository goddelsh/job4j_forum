package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Athority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AthorityService {
    List<Athority> athorityList = new ArrayList<>();

    public AthorityService() {
        this.athorityList.add(new Athority(1, "ROLE_ADMIN"));
        this.athorityList.add(new Athority(2, "ROLE_USER"));
    }

    public Athority getAthorityByRole(String role) {
        Athority result = null;
        for (Athority athority : this.athorityList) {
            if (athority.getRole().equals(role)) {
                result = athority;
                break;
            }
        }
        return result;
    }
}
