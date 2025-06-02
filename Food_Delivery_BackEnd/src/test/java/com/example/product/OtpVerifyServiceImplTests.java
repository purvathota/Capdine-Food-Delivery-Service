package com.example.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.product.entity.Customers;
import com.example.product.entity.OtpVerify;
import com.example.product.repository.CustomersRepository;
import com.example.product.repository.OtpVerifyRepository;
import com.example.product.service.OtpVerifyServiceImpl;

public class OtpVerifyServiceImplTests {

    @Mock
    private OtpVerifyRepository otpVerifyRepository;

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private OtpVerifyServiceImpl otpVerifyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateOTP() {
        String email = "test@example.com";
        String generatedOTP = otpVerifyService.generateOTP(email);
        assertNotNull(generatedOTP);
    }

    @Test
    public void testVerifyOTP_CorrectOTP() {
        String email = "test@example.com";
        String otp = "123456";
        OtpVerify otpVerify = new OtpVerify();
        otpVerify.setEmail_id(email);
        otpVerify.setOtp(otp);
        when(otpVerifyRepository.findByEmailIdAndOtp(email, otp)).thenReturn(otp);
        boolean result = otpVerifyService.verifyOTP(email, otp);
        assertTrue(result);
    }

    @Test
    public void testVerifyOTP_IncorrectOTP() {
        String email = "test@example.com";
        String correctOTP = "123456";
        String incorrectOTP = "654321";
        OtpVerify otpVerify = new OtpVerify();
        otpVerify.setEmail_id(email);
        otpVerify.setOtp(correctOTP);
        when(otpVerifyRepository.findByEmailIdAndOtp(email, correctOTP)).thenReturn(correctOTP);
        boolean result = otpVerifyService.verifyOTP(email, incorrectOTP);
        assertFalse(result);
    }

    @Test
    public void testCustomerExist() {
        String email = "test@example.com";
        // Mock repository behavior
        when(customersRepository.findCustomerByEmailCount(email)).thenReturn(1);
        boolean result = otpVerifyService.customerExists(email);
        assertTrue(result);
    }

    @Test
    public void testGetCustomer() {
        String email = "test@example.com";
        Customers customer = new Customers(); // Create a mock Customer object
        // Mock repository behavior
        when(customersRepository.findCustomerByEmail(email)).thenReturn(customer);
        Customers result = otpVerifyService.getCustomer(email);
        assertNotNull(result);
        assertEquals(customer, result);
    }
}