package com.atorizepoc.miniautorizador.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionMapperTest {

    @InjectMocks
    private ExceptionMapper exceptionMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When Return Root Cause Exception Message")
    void testShouldPassWhenReturnRootCauseExceptionMessage() {
        Exception exception = new Exception("test");
        String rootCauseMessage = exceptionMapper.getRootCauseMessage(exception);
        assertEquals(exception.getMessage(), rootCauseMessage,
                "Assertion fail, value message invalid");
    }

    @Test
    @DisplayName("Should Pass When Return is Instance Of Excepition")
    void testShouldPassWhenReturnIsInstanceOfExcepition() {
        RuntimeException exception = new RuntimeException("test");
        Exception instanceOfExcepition = exceptionMapper.isInstanceOfExcepition(exception);
        assertEquals(exception.getMessage(), instanceOfExcepition.getMessage(),
                "Assertion fail, value message invalid");
    }

}