package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Model.Emails;
import org.example.shoesshopbe.Repo.EmailRepo;
import org.example.shoesshopbe.Request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class PaymentService {
    @Autowired
    private EmailRepo emailRepo;

    public void saveEmail(Emails emails) {
        emails.setEmail(emails.getEmail());
        emails.setCreatedAt(LocalDateTime.now());
        emailRepo.save(emails);
    }
}
