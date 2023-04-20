package com.gmail.kulacholeg.canon.connect.service;

import com.gmail.kulacholeg.canon.connect.dto.OperationSaveDto;
import com.gmail.kulacholeg.canon.connect.entity.DepartmentEntity;
import com.gmail.kulacholeg.canon.connect.entity.OperationEntity;
import com.gmail.kulacholeg.canon.connect.repository.DepartmentRepository;
import com.gmail.kulacholeg.canon.connect.repository.OperationRepository;
import com.gmail.kulacholeg.canon.connect.util.OperationDtoConverter;
import com.gmail.kulacholeg.canon.connect.util.OperationsParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class SaveOperationsService {

    private final RestTemplate template;
    private final DepartmentRepository departmentRepository;
    private final OperationRepository operationRepository;
    private final String ip = "192.168.1.205";

    @Autowired
    public SaveOperationsService(DepartmentRepository departmentRepository, OperationRepository operationRepository) {
        this.departmentRepository = departmentRepository;
        this.operationRepository = operationRepository;
        template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    //@Scheduled(cron = "0 0 13 * * MON-FRI")
    @Scheduled(fixedRate = 300000)
    @Retryable(
            maxAttempts = 10,
            exceptionExpression = "'Connection timed out: connect'.equals(#root.cause.message)",
            backoff = @Backoff(delay = 1800000)
    )
    public void getOperationsAndSave() {
        String cookie = this.login();
        String url = "http://" + ip + "/m_departmentid.html";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookie);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
        List<OperationSaveDto> operationSaveDtos = OperationsParser.parse(response.getBody());
        this.save(operationSaveDtos);
    }

    private void save(List<OperationSaveDto> operations) {
        for (OperationSaveDto dto : operations) {
            OperationEntity entity = OperationDtoConverter.dtoToEntity(dto, departmentRepository);
            OperationEntity oldEntity = operationRepository.getOperationEntityByDepartmentAndDate(
                    entity.getDepartment(),
                    entity.getDate()
            );
            if (oldEntity == null) operationRepository.save(entity);
            else {
                oldEntity.setAllOperations(entity.getAllOperations());
                oldEntity.setPrintBW(entity.getPrintBW());
                oldEntity.setCopyBW(entity.getCopyBW());
                oldEntity.setScanBW(entity.getScanBW());
                oldEntity.setScanColor(entity.getScanColor());

                operationRepository.save(oldEntity);
            }
        }
    }

    private String login() {
        String result = "";
        String url = "http://" + ip + "/tryLogin.cgi";
        String body = "loginM=&0005=7654321&0006=1357246";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> req = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, req, String.class);

        HttpHeaders responseHeaders = response.getHeaders();
        List<String> cookies = responseHeaders.get("Set-Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                result = cookie;
            }
        }
        List<String> temp = List.of(result.split(";"));
        result = temp.get(0);
        return result;
    }
}
