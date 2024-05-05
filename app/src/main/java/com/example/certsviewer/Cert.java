package com.example.certsviewer;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.util.Date;

public class Cert {
    private X509Certificate cert;
    private boolean sysCert;

    public Cert(X509Certificate certificate, boolean system) {
        sysCert = system;
        cert = certificate;
    }

    public boolean isSystem() {
        return sysCert;
    }

    public String getName() {
        String[] parts = cert.getSubjectX500Principal().getName("RFC1779").split(",");
        for (String part : parts) {
            if (part.trim().startsWith("CN=")) {
                return part.trim().substring(3);
            }
        }
        return "";
    }

    public String getOrganization() {
        String[] parts = cert.getSubjectX500Principal().getName("RFC1779").split(",");
        for (String part : parts) {
            if (part.trim().startsWith("O=")) {
                return part.trim().substring(2);
            }
        }
        return "";
    }

    public Date getStartDate() {
        return cert.getNotBefore();
    }

    public Date getEndDate() {
        return cert.getNotAfter();
    }

    public BigInteger getSerial() {
        return cert.getSerialNumber();
    }
}
