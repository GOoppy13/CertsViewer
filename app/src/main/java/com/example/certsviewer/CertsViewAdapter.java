package com.example.certsviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CertsViewAdapter extends ArrayAdapter<Cert> {
    private Context _context;
    private List<Cert> certs;

    public CertsViewAdapter(Context context, List<Cert> certificates) {
        super(context, R.layout.list_item_cert, certificates);
        _context = context;
        certs = certificates;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(R.layout.list_item_cert, parent, false);
        }

        TextView textViewCN = convertView.findViewById(R.id.textViewCN);
        TextView textViewO = convertView.findViewById(R.id.textViewO);

        Cert cert = certs.get(position);

        String commonName = cert.getName();
        String organization = cert.getOrganization();

        textViewCN.setText(commonName);
        textViewO.setText(organization);

        return convertView;
    }
}
