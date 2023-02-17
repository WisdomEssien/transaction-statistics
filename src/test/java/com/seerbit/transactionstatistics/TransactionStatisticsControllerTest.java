package com.seerbit.transactionstatistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit.transactionstatistics.dto.request.TransactionRequest;
import com.seerbit.transactionstatistics.model.Transaction;
import com.seerbit.transactionstatistics.service.TransactionStatisticsService;
import com.seerbit.transactionstatistics.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionStatisticsControllerTest.class)
public class TransactionStatisticsControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    ObjectMapper mapper = new ObjectMapper();
    MockMvc mockMvc;

    @MockBean
    TransactionStatisticsService transactionStatisticsService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    TransactionRequest transactionRequest1 = TransactionRequest.builder().amount("12.3343").timestamp("2018-07-17T09:59:51.312Z").build();
    TransactionRequest transactionRequest2 = TransactionRequest.builder().amount("5.22").timestamp("2023-02-17T19:59:51.312Z").build();
    TransactionRequest transactionRequest3 = TransactionRequest.builder().amount("67.575").timestamp("2023-02-17T19:59:51.312Z").build();

    Transaction transaction1 = Transaction.builder().id(2L).amount(new BigDecimal("12.3343")).timestamp(AppUtil.convertToLocalDatetime("2018-07-17T09:59:51.312Z")).build();
    Transaction transaction2 = Transaction.builder().id(3L).amount(new BigDecimal("5.22")).timestamp(AppUtil.convertToLocalDatetime("2023-02-17T19:59:51.312Z")).build();
    Transaction transaction3 = Transaction.builder().id(4L).amount(new BigDecimal("67.575")).timestamp(AppUtil.convertToLocalDatetime("2023-02-17T19:59:51.312Z")).build();

    @Test
    public void createTransaction_success() throws Exception {

//        when(transactionStatisticsService.addTransaction(transactionRequest1)).thenReturn(transaction1);
//
//        MockHttpServletRequestBuilder mockRequest1 = MockMvcRequestBuilders.post("/v1/transactions")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.mapper.writeValueAsString(transactionRequest1));
//
//        mockMvc.perform(mockRequest1).andExpect(status().isNoContent());
    }

}
