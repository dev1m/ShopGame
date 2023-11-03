package net.dev1m.shopgame.service;

import net.dev1m.shopgame.dto.UserDTO;
import net.dev1m.shopgame.entity.Users;
import net.dev1m.shopgame.reponsitory.AuthReponsitory;
import net.dev1m.shopgame.service.imp.AuthServiceImp;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements AuthServiceImp {
    @Autowired
    AuthReponsitory authReponsitory;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String checkLogin(String username, String password) {
        Users users = authReponsitory.findByUsername(username);
        if (users == null) {
            return "UserNotFound"; // Trả về chuỗi hoặc giá trị đặc biệt để biểu thị tên người dùng không tồn tại
        }

        // So sánh mật khẩu đã mã hóa với mật khẩu đầu vào
        boolean passwordMatch = passwordEncoder.matches(password, users.getPassword());

        if (passwordMatch) {
            return "LoginSuccess"; // Trả về chuỗi hoặc giá trị đặc biệt để biểu thị đăng nhập thành công
        } else {
            return "PasswordMismatch"; // Trả về chuỗi hoặc giá trị đặc biệt để biểu thị mật khẩu không khớp
        }
    }
    @Override
    public String register(UserDTO userDTO) {
        if (authReponsitory.existsByUsername(userDTO.getUsername())) {
            return "UserExists";
        }
        if (!isValidGmailEmail(userDTO.getEmail())) {
            return "EmailInvalid";
        }
        Users users = new Users();
        users.setUsername(userDTO.getUsername());
        users.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        users.setEmail(userDTO.getEmail());
        users.setBanned(0);
        users.setMoney(0);
        try {
            authReponsitory.save(users);
            return "RegisterSuccess";
        } catch (Exception e) {
            return "Fail";
        }
    }

    @Override
    public boolean changePassword(String username,String newPassword) {
        boolean issSuccess = false;
        Users users = authReponsitory.findByUsername(username);
        if (users != null) {
            users.setPassword(this.passwordEncoder.encode(newPassword));
            authReponsitory.save(users);
            issSuccess = true;
        }

        return issSuccess;
    }



    private boolean isValidGmailEmail(String email) {
        // Sử dụng EmailValidator để kiểm tra tính hợp lệ của địa chỉ email
        EmailValidator validator = EmailValidator.getInstance();
        // Kiểm tra xem địa chỉ email có hợp lệ và có thuộc Gmail không
        return validator.isValid(email);
    }

    @Override
    public boolean resetPasswordAndSendEmail(String email) {
        String newPassword = generateRandomPassword();
        Users users = authReponsitory.findByEmail(email);
        if(users != null){
            users.setPassword(this.passwordEncoder.encode(newPassword));
            authReponsitory.save(users);
            sendPasswordResetEmail(email, newPassword);
            return true;
        }else {
            return false;
        }

    }
    private static String generateRandomPassword() {
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String DIGIT = "0123456789";
        String SPECIAL_CHAR = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        String PASSWORD_ALLOW = CHAR_LOWER + CHAR_UPPER + DIGIT + SPECIAL_CHAR;
        int passwordLength = 12; // Độ dài mật khẩu mong muốn

        StringBuilder password = new StringBuilder(passwordLength);
        Random random = new Random();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW.length());
            char randomChar = PASSWORD_ALLOW.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
    private void sendPasswordResetEmail(String email, String newPassword) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo(email);
            helper.setSubject("KHÔI PHỤC MẬT KHẨU!");
            helper.setText("Mật khẩu mới của bạn là: " + newPassword);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("Error send mail "+e.getMessage());
        }
    }
}
