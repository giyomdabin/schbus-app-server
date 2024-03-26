package com.schbus.pay;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayReadyVO {
    
    //response
    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private Date created_at;
    
}
 