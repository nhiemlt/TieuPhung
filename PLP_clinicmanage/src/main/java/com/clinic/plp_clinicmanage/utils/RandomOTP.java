/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.utils;

import java.util.Random;

/**
 *
 * @author DELL
 */
public class RandomOTP {
    public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
            otp.append(digit);
        }
        return otp.toString();
    }

}
