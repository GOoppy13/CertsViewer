package com.example.certsviewer;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class CertsManager {
    private static CertsManager Instance;
    public static CertsManager getInstance() {
        if (Instance == null) {
            Instance = new CertsManager();
        }

        return Instance;
    }
    private KeyStore keyStore;
    private List<Cert> certs;

    private CertsManager() {
        try {
            keyStore = KeyStore.getInstance("AndroidCAStore");
            keyStore.load(null);
            certs = new ArrayList<>();
            loadCerts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cert> getCerts() {
        return certs;
    }

    public Cert getCertBySerial(BigInteger serial) {
        return certs.stream().filter(cert -> Objects.equals(cert.getSerial(), serial)).findFirst().get();
    }

    private void loadCerts() {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                Certificate cert = keyStore.getCertificate(alias);
                if (cert instanceof X509Certificate) {
                    certs.add(new Cert((X509Certificate) cert, alias.contains("system")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
