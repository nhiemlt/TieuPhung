package com.clinic.plp_clinicmanage.utils;

import com.clinic.plp_clinicmanage.models.NguoiDung;

public class Auth {
    public static NguoiDung user = null;
    public static String passwordTemp = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isStaff() {
        return Auth.isLogin() ? (user.getVaiTro() == 1) : false;
    }

    public static boolean isDoctor() {
        return Auth.isLogin() ? (user.getVaiTro() == 2) : false;
    }

    public static boolean isManager() {
        return Auth.isLogin() ? (user.getVaiTro() == 0) : false;
    }
}