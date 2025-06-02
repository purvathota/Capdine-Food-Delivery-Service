package com.example.product.service;

//import javax.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage;

//import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.product.entity.Customers;
import com.example.product.entity.OtpVerify;
import com.example.product.repository.CustomersRepository;
import com.example.product.repository.OtpVerifyRepository;

@Service
public class OtpVerifyServiceImpl implements OtpVerifyService {

    private final String otpValidityTime = "5m"; // Adjust OTP validity time
    
    @Autowired
    OtpVerifyRepository repo;
    
    @Autowired
    CustomersRepository customerRepo;

    @Autowired
    private JavaMailSender emailSender; // Inject JavaMailSender bean

    public String generateOTP(String email) {
        String otp = generateRandomNumber(6);

        sendEmail(email, otp);

        return otp;
    }

    private String generateRandomNumber(int length) {
        // Use a secure random number generator
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }
        return sb.toString();
    }

    public void sendEmail(String email, String otp) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            

            helper.setFrom("agarwal.shrishti2014@gmail.com"); // Replace with your sender email
            helper.setTo(email);
            helper.setSubject("Your OTP for Application Name");
            helper.setText("Your OTP is: " + otp, true); // Set HTML content if needed

            emailSender.send(message);
            OtpVerify otpverify = new OtpVerify();
            otpverify.setEmail_id(email);
            otpverify.setOtp(otp);
            repo.save(otpverify);
        } catch (Exception e) {
            // Handle email sending exceptions
            e.printStackTrace();
        }
    }

    public boolean verifyOTP(String email, String otp) {
        // Retrieve OTP from secure storage (e.g., Redis) if stored
        //String storedOTP = // logic to retrieve OTP from storage
        String storedOTP = repo.findByEmailIdAndOtp(email, otp);// Optional
        
        if (storedOTP != null && otp.equals(storedOTP)) {
            // Delete OTP from storage after verification
            // userRepository.save(user.setOtp(null)); // Optional
        	repo.deleteByEmail(email);
            return true;
            
        }
        return false;
    }
    
    public boolean customerExists(String customer_email) {
    	if(customerRepo.findCustomerByEmailCount(customer_email)>0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public Customers getCustomer(String customer_email) {
    	return customerRepo.findCustomerByEmail(customer_email);  
    }
}