package com.acko.htlmgenerator.service;

import com.acko.htlmgenerator.entities.Attributes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    private Map<String, String> iconsMap;
    private Map<String, List<Attributes>> attributesMap;

    public GeneratorServiceImpl() {
        iconsMap = new HashMap<>();
        iconsMap.put("hospital_daily_allowance", "https://www.acko.com/static/images/rapido/hospitalization_daily_allowance_1x.png");
        iconsMap.put("personal_accident", "https://www.acko.com/static/images/abhibus/personal_accident_1x.png");
        iconsMap.put("emi_protection", "https://www.acko.com/static/images/aubank/emi_protection_1x.png");
        iconsMap.put("critical_illness", "https://www.acko.com/static/images/aubank/critical_illness_1x.png");

        attributesMap = new HashMap<>();
        List<Attributes> attributesList = new ArrayList<>();
        attributesList.add(new Attributes("name", "Name", "insured.name"));
        attributesList.add(new Attributes("gender", "Gender", "insured.gender"));
        attributesList.add(new Attributes("email", "Email", "insured.email"));
        attributesList.add(new Attributes("age", "Age", "insured.age"));
        attributesList.add(new Attributes("account_number", "Loan A/C Number", "loan.account_number"));
        attributesList.add(new Attributes("loan_tenure", "Loan Tenure", "loan.tenure_in_months"));
        attributesList.add(new Attributes("emi", "Loan Emi", "loan.emi"));
        attributesList.add(new Attributes("loan_amount", "Loan Amount", "loan.amount"));
        attributesList.add(new Attributes("loan_disbursement_date", "Loan Disbursement Date", "loan.disbursement_date"));
        attributesList.add(new Attributes("nominee_name", "Nominee Name", "nominee.name"));
        attributesList.add(new Attributes("nominee_relationship", "Nominee Relationship", "nominee.relationship"));
        attributesMap.put("loan", attributesList);
    }

    @Override
    public String getIconForCoverId(String coverId) {
        return this.iconsMap.get(coverId);
    }

    @Override
    public List<Attributes> getValuesForLob(String lob) {
        System.out.println("Getting values for Lob");
        return this.attributesMap.get(lob);

    }
}
