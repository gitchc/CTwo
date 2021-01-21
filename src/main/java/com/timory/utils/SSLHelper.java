package com.timory.utils;

import javax.net.SocketFactory;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SSLHelper {

    public static String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 5.0)";

    public static SSLSocketFactory init() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            SSLSocketFactory socketFactory = context.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(socketFactory);
            return socketFactory;
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyManagementException e) {
        }
        return null;
    }

}