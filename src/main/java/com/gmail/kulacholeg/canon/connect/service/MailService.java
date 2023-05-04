package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationGetDto;
import com.gmail.kulacholeg.canon.connect.repository.SettingsRepository;
import com.gmail.kulacholeg.canon.connect.util.PDFCreator;
import com.itextpdf.text.Document;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    private final GetOperationsService operationsService;
    private final JavaMailSender sender;
    private final SettingsRepository repository;

    @Scheduled(fixedRate = 999999999)
    public void sendStatistic(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date start = new Date(calendar.getTimeInMillis());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = new Date(calendar.getTimeInMillis());

        List<OperationGetDto> operations = operationsService.getOperationsBetweenDates(start.toString(), end.toString());

        Document pdf = PDFCreator.createFromList(operations);

        this.send(pdf);
    }

    private void send(Document pdf){
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("se492367@gmail.com");
            helper.setTo(repository.getByName("receiver_email").getValue());
            helper.setSubject("Витрати паперу за місяць. МФУ Canon. TEST");
            helper.setText("Витрати паперу у доданому файлі. З повагою, Кулачинський О.В. TEST-MESSAGE");

            FileSystemResource file = new FileSystemResource(new File("table.pdf"));
            helper.addAttachment("витрати.pdf", file);

            sender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
