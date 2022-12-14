package com.acko.htmlgenerator.service;

import com.acko.htmlgenerator.dto.HeaderRequestDTO;
import com.acko.htmlgenerator.entities.TemplateHistoryWithHtmlContent;
import com.acko.htmlgenerator.models.CoverageIcon;
import com.acko.htmlgenerator.models.GeneratedCoi;
import com.acko.htmlgenerator.models.LobAttributes;
import com.acko.htmlgenerator.models.TemplateHistory;
import com.acko.htmlgenerator.pojo.CoverageDetails;
import com.acko.htmlgenerator.pojo.InsuredDetails;
import com.acko.htmlgenerator.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    private final String staticOne = "<!DOCTYPE html><html><head><title></title><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,600,700,800\" rel=\"stylesheet\"><style>body,html{font-family:Montserrat,sans-serif!important;margin:0 auto!important;height:100%!important;width:100%!important}.wrapper{width:909px;display:block}p{font-size:14px}</style></head><body style=\"padding:0;margin:0\"><div class=\"wrapper\"><div><table style=\"border-collapse:collapse;width:100%;border:0;margin-bottom:20px\"><tbody><tr><td style=\"width:65%;vertical-align:top\"><img src=\"https://www.acko.com/static/images/Acko_Logo_Pale.png\" style=\"height:30px;margin:0\"><p style=\"margin:0;font-size:11px;line-height:1.3;color:#4a4a4a\">Acko Group Health<br>Insurance Policy</p></td>";
    private final String staticTwo = "</tr></tbody></table><div style=\"min-height:940px\"><div style=\"width:100%;display:block;margin-top:40px\"><h1 style=\"font-weight:600;margin:5px 0;font-size:35px\">Acko Group Health Insurance Policy</h1></div><div style=\"width:100%;display:block\"><h2 style=\"font-weight:400;margin:10px 0;font-size:25px;line-height:1.5\">Your insurance policy is valid from {{policy_start_date}} to {{policy_end_date}}.</h2></div><div style=\"width:100%;display:block;margin:10px 0 40px 0\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"width:70%;vertical-align:top;padding-right:30px\"><div style=\"display:block\"><table style=\"border-collapse:collapse;width:100%;border:0;margin-top:0\"><thead><tr style=\"border-bottom:1px solid #ddd\"><th style=\"font-weight:600;font-size:12px;text-align:left;text-transform:uppercase;padding:15px 0\" colspan=\"2\">INSURED DETAILS</th></tr></thead><tbody>{% for insured in insureds %}";
    private final String staticThree = "</tbody>{% endfor %}</table></div></td><td style=\"width:30%;vertical-align:top;padding-left:30px\"><div style=\"background:rgba(229,227,227,.3);border-radius:6px\"><p style=\"margin:0;padding:12px 12px;text-align:center;text-transform:uppercase;font-size:14px;border-bottom:1px solid #ddd\">PREMIUM BREAKUP</p><div style=\"padding:12px 12px;border-bottom:1px solid #ddd\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"font-size:14px;padding:5px 0\">Premium</td><td style=\"font-size:18px;text-align:right;padding:5px 0\">??? {{premium.premium_without_gst}}</td></tr><tr><td style=\"font-size:14px;padding:8px 0 5px 0;color:#8a8a8a\">GST @ 18%</td><td style=\"font-size:14px;text-align:right;padding:5px 0;color:#8a8a8a\">+ ??? {{premium.gst}}</td></tr></tbody></table></div><div style=\"padding:12px 12px\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"font-size:14px;padding:5px 0\">Total Premium</td><td style=\"font-size:18px;text-align:right;padding:5px 0\">??? {{premium.amount}}</td></tr></tbody></table></div></div></td></tr></tbody></table></div><div style=\"width:100%;display:block;margin-top:20px\"><h1 style=\"font-weight:600;margin:5px 0;font-size:40px\">What???s Included</h1><p style=\"margin:5px 0 15px 0;color:#8a8a8a;font-size:12px\">A snapshot of all the coverages in this policy.</p></div><div style=\"width:100%;display:block;margin-top:20px\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr>";
//    private final String staticFour = "<td style=\"width:25%\"></td></tr></tbody></table></div></div><div style=\"width:100%;display:block\"><p style=\"margin:20px 0;font-style:italic;font-size:12px;color:#4a4a4a;background:#eee;display:inline-block;padding:12px 15px;border-radius:5px\">*Please visit<a href=\"https://acko.com\" style=\"text-decoration:none;font-weight:600;color:#4a4a4a\" target=\"_blank\">www.acko.com</a>to add Nominee details by logging in with your registered mobile number.</p></div><div style=\"width:100%;display:block;margin-top:10px;page-break-after:always\"><table style=\"border-collapse:collapse;width:100%;border:0;margin-bottom:10px\"><tbody><tr><td style=\"width:80%;vertical-align:top\"><p style=\"margin:0 0 5px 0;font-weight:600\">ACKO GENERAL INSURANCE LTD.</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">36/5, Hustlehub One East, Somasandrapalya, 27th Main Rd, Sector 2, HSR Layout, Bengaluru, 560102</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">Email: loanfrontcare@acko.com | Toll Free: 1800 266 2256 | www.acko.com</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">Product Name : Acko Group Health Insurance Policy | CIN: U66000KA2016PLC138288 | IRDAI Reg No. 157</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">GSTIN: 27AAOCA9055C1ZJ | UIN: ACKHLGP21469V022021</p></td><td style=\"width:20%;text-align:center;vertical-align:top\"><img src=\"https://www.acko.com/static/images/sign.png\" style=\"height:60px;margin:auto;display:block\"><p style=\"margin:5px 0 0 0;padding-top:5px;color:#8a8a8a;font-size:10px;text-align:center;border-top:1px solid #000\">For ACKO General Insurance Ltd.</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;text-align:center\">Duly Constituted Attorney</p></td></tr></tbody></table><p style=\"margin:0;color:#8a8a8a;font-size:8px\">This certificate is issued by Acko General Insurance Limited for the mentioned Insured(s) under the Acko Group Health Insurance Policy. IN WITNESS WHEREOF, this Certificate of Insurance has been signed on {{policy_start_date}}.</p></div></div><div><div style=\"width:100%;display:block;text-align:right\"><img src=\"https://www.acko.com/static/images/Acko_Logo_Pale.png\" style=\"height:30px;margin:0 5px 0 0;display:inline-block;vertical-align:middle\"><p style=\"margin:0;font-size:12px;display:inline-block;text-align:left;vertical-align:middle\">Acko Group Health<br>Insurance Policy</p></div><div style=\"width:100%;display:block;margin:40px 0 50px 0\"><h1 style=\"font-weight:600;margin:0 0 15px 0;font-size:40px\">What???s covered?</h1><p style=\"color:#4a4a4a;font-size:14px;margin:0;line-height:1.5;width:80%\">Acko Group Health Insurance Policy provides extensive coverage for the insured person against unfortunate incidents during your policy coverage period. The following benefits will be applicable whenthe policy is active:</p></div><div style=\"width:100%;display:block;margin:30px 0 50px 0\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"width:10%;vertical-align:top\"><img src=\"https://www.acko.com/static/images/aubank/personal_accident_1x.png\" style=\"height:80px\"></td><td style=\"vertical-align:top;padding:0 0 0 30px\"><div style=\"width:70%;display:block\"><h2 style=\"margin:0 0 20px 0;font-weight:400;color:#865cff\">Accidental Death / PPD / PTD</h2><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">In case you met with an accident during the coverage period, which results in your Death or Permanent Total/Partial disability, we will pay the applicable lump-sum benefit up to your principal loan amount of up to Rs. 50,000/- The outstanding principal amount will be settled with Kreditbee first and the rest will be settled (if any) with you or your nominee (in case of death).</p></div></td></tr></tbody></table></div><div style=\"width:100%;display:block;margin:50px 0\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"width:10%;vertical-align:top\"><img src=\"https://www.acko.com/static/images/aubank/emi_protection_1x.png\" style=\"height:80px\"></td><td style=\"vertical-align:top;padding:0 0 0 30px\"><div style=\"width:70%;display:block\"><h2 style=\"margin:0 0 20px 0;font-weight:400;color:#865cff\">EMI Protection</h2><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">We will pay a maximum of 3 monthly EMI installments maximum up to Rs. 50,000/- each. The Benefit payable will be accrued on the basis of continuous hospitalization due to accident or illness provided that the person is hospitalized for continuous and completed period as per the below grid.</p><ul style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5;padding:0 0 0 22px;margin:0 0 20px 0\"><li>1st EMI payment: After 7 days of continuous hospitalization</li><li>2nd EMI payment: After 10 days of continuous hospitalization</li><li>3rd EMI payment: After 15 days of continuous hospitalization</li></ul><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">Kindly note : There is an initial waiting period of 15 Days (except for accidents).</p></div></td></tr></tbody></table></div><div style=\"width:100%;display:block;margin:50px 0\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"width:10%;vertical-align:top\"><img src=\"https://www.acko.com/static/images/aubank/critical_illness_1x.png\" style=\"height:80px\"></td><td style=\"vertical-align:top;padding:0 0 0 30px\"><div style=\"width:70%;display:block\"><h2 style=\"margin:0 0 20px 0;font-weight:400;color:#865cff\">Critical Illness</h2><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">In case of any Critical illness (List of 15 critical illnesses are covered as mentioned in the T&Cs), we will pay a lumpsum benefit upto the principal loan amount of maximum upto Rs. 50,000/- . The outstanding principal amount will be settled with Kreditbee first and the rest will be settled (if any) with you or your nominee (in case of death).</p><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">Waiting Period 30 days. Survival period 7 days</p></div></td></tr></tbody></table></div><div style=\"width:100%;display:block;margin:90px 0 10px 0\"><h1 style=\"font-weight:400;margin:0 0 20px 0;font-size:25px;color:#4a4a4a\">You have declared the following:</h1><ul style=\"margin:10px 0 20px 0;padding:0 0 0 22px\"><li style=\"color:#4a4a4a;font-size:14px;padding:0;line-height:1.4;width:80%\">I hereby agree to buy Acko Group Health Insurance Policy and provide my express consent to the terms and conditions.</li><li style=\"color:#4a4a4a;font-size:14px;padding:0;line-height:1.4;width:80%\">I hereby declare that I am in good health and do not suffer from any Pre-Existing medical conditions.</li><li style=\"color:#4a4a4a;font-size:14px;padding:0;line-height:1.4;width:80%\">I, hereby assign and authorize Acko General Insurance Ltd. to pay any claim made by me under Acko Group Health Insurance Policy in favour of the loan provider, for and up to the extent of the principal outstanding. I confirm that the aforesaid shall be construed as complete discharge of liability of Acko and I shall not have any right to such amount from Acko.</li></ul></div><div style=\"width:100%;display:block;page-break-after:always\"></div></div><div><div style=\"width:100%;display:block;text-align:right\"><img src=\"https://www.acko.com/static/images/Acko_Logo_Pale.png\" style=\"height:30px;margin:0 5px 0 0;display:inline-block;vertical-align:middle\"><p style=\"margin:0;font-size:12px;display:inline-block;text-align:left;vertical-align:middle\">Acko Group Health<br>Insurance Policy</p></div><div style=\"min-height:1200px\"><div style=\"width:100%;display:block;margin:30px 0 50px 0\"><h1 style=\"font-weight:600;margin:5px 0;font-size:40px\">How To Claim</h1><p style=\"color:#4a4a4a;font-size:14px;margin:15px 0;line-height:1.5;width:80%\">Claiming with Acko is as easy as buying a poiicy. You can go oniine and fiie a claim in a few simple clicks.</p></div><div style=\"width:100%;display:block;margin:20px 0\"><p style=\"margin:0 0 20px 0;font-size:30px;font-weight:400\">On Acko.com:</p><table style=\"border-collapse:collapse;width:80%;border:0;margin-top:30px\"><tbody><tr><td style=\"width:8%;vertical-align:middle;text-align:center;padding-bottom:20px\"><label style=\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\">1</label></td><td style=\"width:92%;vertical-align:middle;padding-bottom:20px;padding-left:20px\"><p style=\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\">Go to<a href=\"https://acko.com\" style=\"text-decoration:none;font-weight:600;color:#865cff\">www.acko.com</a>and Login with your registered mobile number and enter the OTP you receive.</p></td></tr><tr><td style=\"vertical-align:middle;text-align:center;padding-bottom:20px\"><label style=\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\">2</label></td><td style=\"vertical-align:middle;padding-bottom:20px;padding-left:20px\"><p style=\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\">Select your particular policy from ???My Policies??? section.</p></td></tr><tr><td style=\"vertical-align:middle;text-align:center;padding-bottom:20px\"><label style=\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\">3</label></td><td style=\"vertical-align:middle;padding-bottom:20px;padding-left:20px\"><p style=\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\">Click on the ???Make a Claim??? button below the Policy details section.</p></td></tr><tr><td style=\"vertical-align:middle;text-align:center;padding-bottom:20px\"><label style=\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\">4</label></td><td style=\"vertical-align:middle;padding-bottom:20px;padding-left:20px\"><p style=\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\">Select the claim type, Follow the next steps and upload the required documents.</p></td></tr><tr><td style=\"vertical-align:middle;text-align:center;padding-bottom:20px\"><label style=\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\">5</label></td><td style=\"vertical-align:middle;padding-bottom:20px;padding-left:20px\"><p style=\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\">Your claim has been submitted; Our claims team will get back to you!</p></td></tr></tbody></table></div></div><div style=\"width:100%;margin-top:10px;display:block\"><p style=\"margin:0;font-size:12px\">For further details about the cover and policy refer to the<a href=\"{{tnc_url}}\" style=\"color:#865cff;text-decoration:none\">Terms & Conditions</a>document.</p></div></div></div></body></html>";

    private final String staticFour = "<td style=\"width:25%\"></td></tr></tbody></table></div></div><div style=\"width:100%;display:block\"><p style=\"margin:20px 0;font-style:italic;font-size:12px;color:#4a4a4a;background:#eee;display:inline-block;padding:12px 15px;border-radius:5px\">*Please visit<a href=\"https://acko.com\" style=\"text-decoration:none;font-weight:600;color:#4a4a4a\" target=\"_blank\">www.acko.com</a>to add Nominee details by logging in with your registered mobile number.</p></div><div style=\"width:100%;display:block;margin-top:10px;page-break-after:always\"><table style=\"border-collapse:collapse;width:100%;border:0;margin-bottom:10px\"><tbody><tr><td style=\"width:80%;vertical-align:top\"><p style=\"margin:0 0 5px 0;font-weight:600\">ACKO GENERAL INSURANCE LTD.</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">36/5, Hustlehub One East, Somasandrapalya, 27th Main Rd, Sector 2, HSR Layout, Bengaluru, 560102</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">Email: loanfrontcare@acko.com | Toll Free: 1800 266 2256 | www.acko.com</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">Product Name : Acko Group Health Insurance Policy | CIN: U66000KA2016PLC138288 | IRDAI Reg No. 157</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;line-height:1.5\">GSTIN: 27AAOCA9055C1ZJ | UIN: ACKHLGP21469V022021</p></td><td style=\"width:20%;text-align:center;vertical-align:top\"><img src=\"https://www.acko.com/static/images/sign.png\" style=\"height:60px;margin:auto;display:block\"><p style=\"margin:5px 0 0 0;padding-top:5px;color:#8a8a8a;font-size:10px;text-align:center;border-top:1px solid #000\">For ACKO General Insurance Ltd.</p><p style=\"margin:0;color:#8a8a8a;font-size:10px;text-align:center\">Duly Constituted Attorney</p></td></tr></tbody></table><p style=\"margin:0;color:#8a8a8a;font-size:8px\">This certificate is issued by Acko General Insurance Limited for the mentioned Insured(s) under the Acko Group Health Insurance Policy. IN WITNESS WHEREOF, this Certificate of Insurance has been signed on {{policy_start_date}}.</p></div></div><div><div style=\"width:100%;display:block;text-align:right\"><img src=\"https://www.acko.com/static/images/Acko_Logo_Pale.png\" style=\"height:30px;margin:0 5px 0 0;display:inline-block;vertical-align:middle\"><p style=\"margin:0;font-size:12px;display:inline-block;text-align:left;vertical-align:middle\">Acko Group Health<br>Insurance Policy</p></div><div style=\"width:100%;display:block;margin:40px 0 50px 0\"><h1 style=\"font-weight:600;margin:0 0 15px 0;font-size:40px\">What???s covered?</h1><p style=\"color:#4a4a4a;font-size:14px;margin:0;line-height:1.5;width:80%\">Acko Group Health Insurance Policy provides extensive coverage for the insured person against unfortunate incidents during your policy coverage period. The following benefits will be applicable whenthe policy is active:</p></div>";

    private final String staticFive = "<div style=\\\"width:100%;display:block;page-break-after:always\\\"></div></div><div><div style=\\\"width:100%;display:block;text-align:right\\\"><img src=\\\"https://www.acko.com/static/images/Acko_Logo_Pale.png\\\" style=\\\"height:30px;margin:0 5px 0 0;display:inline-block;vertical-align:middle\\\"><p style=\\\"margin:0;font-size:12px;display:inline-block;text-align:left;vertical-align:middle\\\">Acko Group Health<br>Insurance Policy</p></div><div style=\\\"min-height:1200px\\\"><div style=\\\"width:100%;display:block;margin:30px 0 50px 0\\\"><h1 style=\\\"font-weight:600;margin:5px 0;font-size:40px\\\">How To Claim</h1><p style=\\\"color:#4a4a4a;font-size:14px;margin:15px 0;line-height:1.5;width:80%\\\">Claiming with Acko is as easy as buying a poiicy. You can go oniine and fiie a claim in a few simple clicks.</p></div><div style=\\\"width:100%;display:block;margin:20px 0\\\"><p style=\\\"margin:0 0 20px 0;font-size:30px;font-weight:400\\\">On Acko.com:</p><table style=\\\"border-collapse:collapse;width:80%;border:0;margin-top:30px\\\"><tbody><tr><td style=\\\"width:8%;vertical-align:middle;text-align:center;padding-bottom:20px\\\"><label style=\\\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\\\">1</label></td><td style=\\\"width:92%;vertical-align:middle;padding-bottom:20px;padding-left:20px\\\"><p style=\\\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\\\">Go to<a href=\\\"https://acko.com\\\" style=\\\"text-decoration:none;font-weight:600;color:#865cff\\\">www.acko.com</a>and Login with your registered mobile number and enter the OTP you receive.</p></td></tr><tr><td style=\\\"vertical-align:middle;text-align:center;padding-bottom:20px\\\"><label style=\\\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\\\">2</label></td><td style=\\\"vertical-align:middle;padding-bottom:20px;padding-left:20px\\\"><p style=\\\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\\\">Select your particular policy from ???My Policies??? section.</p></td></tr><tr><td style=\\\"vertical-align:middle;text-align:center;padding-bottom:20px\\\"><label style=\\\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\\\">3</label></td><td style=\\\"vertical-align:middle;padding-bottom:20px;padding-left:20px\\\"><p style=\\\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\\\">Click on the ???Make a Claim??? button below the Policy details section.</p></td></tr><tr><td style=\\\"vertical-align:middle;text-align:center;padding-bottom:20px\\\"><label style=\\\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\\\">4</label></td><td style=\\\"vertical-align:middle;padding-bottom:20px;padding-left:20px\\\"><p style=\\\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\\\">Select the claim type, Follow the next steps and upload the required documents.</p></td></tr><tr><td style=\\\"vertical-align:middle;text-align:center;padding-bottom:20px\\\"><label style=\\\"display:inline-block;color:#000;font-size:16px;font-weight:600;padding:10px 16px;border:1px solid #ddd;border-radius:50%\\\">5</label></td><td style=\\\"vertical-align:middle;padding-bottom:20px;padding-left:20px\\\"><p style=\\\"margin:0;font-size:16px;line-height:1.5;color:#4a4a4a\\\">Your claim has been submitted; Our claims team will get back to you!</p></td></tr></tbody></table></div></div><div style=\\\"width:100%;margin-top:10px;display:block\\\"><p style=\\\"margin:0;font-size:12px\\\">For further details about the cover and policy refer to the<a href=\\\"{{tnc_url}}\\\" style=\\\"color:#865cff;text-decoration:none\\\">Terms & Conditions</a>document.</p></div></div></div></body></html>";

    private final CoiRepository coiRepository;
    private final GeneratedCoiRepository generatedCoiRepository;

    private final LobAttributesRepository lobAttributesRepository;

    private final CoverageIconRepository coverageIconRepository;

    private final TemplateHistoryRepository templateHistoryRepository;

    @Override
    public List<CoverageIcon> getCovers() {
        return this.coverageIconRepository.findAll();
    }

    @Override
    public List<LobAttributes> getValuesForLob(String lob) {
        return this.lobAttributesRepository.findAllByLob(lob);
    }

    @Override
    public String createInsuredDetails(HeaderRequestDTO request) {
        String insuredDetails = createInsuredDetailsUtil(request);
        GeneratedCoi generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob()).orElseThrow(() -> new IllegalArgumentException("No record for this template name!"));
        generatedCoi = this.generatedCoiRepository.save(generatedCoi.toBuilder()
                        .insuredDetails(insuredDetails)
                .build());
//        return insuredDetails+"\n";
        return this.getHtml(generatedCoi);
//        return staticOne+generatedCoi.getPartnerDetails()+staticTwo+generatedCoi.getInsuredDetails()+staticThree+("".equals(generatedCoi.getCoverageDetails()) ? staticFour+staticFive : generatedCoi.getCoverageDetails());
    }

    public String createInsuredDetailsUtil(HeaderRequestDTO request) {
        Optional<TemplateHistory> previousTemplateHistory = this.templateHistoryRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());

        ObjectMapper mapper = new ObjectMapper();
        String requestJsonString = "";
        try {
            requestJsonString = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (previousTemplateHistory.isPresent()) {
            this.templateHistoryRepository.save(previousTemplateHistory.get().toBuilder()
                            .insuredDetailsRequest(requestJsonString.toString())
                            .build());
        } else {
            this.templateHistoryRepository.save(TemplateHistory.builder()
                            .lob(request.getLob())
                            .templateName(request.getTemplateName())
                            .insuredDetailsRequest(requestJsonString.toString())
                            .build());
        }

        List<InsuredDetails> attributesList = request.getInsuredDetailsList();

        String basicInsuredDetails = "<tr><td style=\"padding:15px 0 4px 0;vertical-align:top\"><p style=\"margin:0;font-weight:400;font-size:14px;color:#8a8a8a\">display_name</p></td><td style=\"padding:15px 0 4px 0;vertical-align:top\"><p style=\"margin:0;font-weight:400;color:#000;font-size:14px\">{{attribute_value}}</p></td></tr>";
        String insuredDetails = "";
        for(InsuredDetails attributes: attributesList) {
            String updatedInsuredDetails = basicInsuredDetails;

            updatedInsuredDetails = updatedInsuredDetails.replace("display_name", attributes.getDisplayName());
            updatedInsuredDetails = updatedInsuredDetails.replace("attribute_value", attributes.getValue());

            insuredDetails += updatedInsuredDetails;
        }

        return insuredDetails;
    }

    @Override
    public String getHeaderTemplate(HeaderRequestDTO request) {
        System.out.println("REQEUST: "+ request.toString());
        String header = getHeaderTemplateUtil(request);
        GeneratedCoi generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob()).orElseThrow(() -> new IllegalArgumentException("No record for this template name!"));
        generatedCoi = this.generatedCoiRepository.save(generatedCoi.toBuilder()
                .partnerDetails(header)
                .build());

//        return staticOne+generatedCoi.getPartnerDetails()+staticTwo+generatedCoi.getInsuredDetails()+staticThree+("".equals(generatedCoi.getCoverageDetails()) ? staticFour+staticFive : generatedCoi.getCoverageDetails());
        return this.getHtml(generatedCoi);
//        return header;
    }

    public String getHeaderTemplateUtil(HeaderRequestDTO request) {

        Optional<TemplateHistory> previousTemplateHistory = this.templateHistoryRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());
        ObjectMapper mapper = new ObjectMapper();
        String requestJsonString = "";
        try {
            requestJsonString = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (previousTemplateHistory.isPresent()) {
            this.templateHistoryRepository.save(previousTemplateHistory.get().toBuilder()
                    .partnerDetailsRequest(requestJsonString.toString())
                    .build());
        } else {
            this.templateHistoryRepository.save(TemplateHistory.builder()
                    .lob(request.getLob())
                    .templateName(request.getTemplateName())
                    .partnerDetailsRequest(requestJsonString.toString())
                    .build());
        }

        String header = "<td style=\"width:35%;vertical-align:top\"><p style=\"margin:0 0 5px 0;font-size:12px\"><b>Group Administrator :<br>partner_name</b></p><p style=\"margin:0 0 10px 0;font-size:12px;line-height:1.5;color:#8a8a8a\">partner_address</p><div><label style=\"margin:0;font-size:12px;color:#8a8a8a;border-top:2px solid #ddd;padding:5px 0 2px 0;display:block\">Master Policy No.:<span style=\"color:#000\">{{master_policy.number}}</span></label></div></td>";
        header = header.replace("partner_address", request.getPartnerDetails().getPartnerAddress());
        header = header.replace("partner_name", request.getPartnerDetails().getPartnerName());
        return header;
    }

    @Override
    public String getCoverageTemplate(HeaderRequestDTO request) {
        String coverageDetails = getCoverageTemplateUtil(request);
        GeneratedCoi generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob()).orElseThrow(() -> new IllegalArgumentException("No record for this template name!"));
        generatedCoi = this.generatedCoiRepository.save(generatedCoi.toBuilder()
                .coverageDetails(coverageDetails)
                .build());
        return this.getHtml(generatedCoi);
//        return staticOne+generatedCoi.getPartnerDetails()+staticTwo+generatedCoi.getInsuredDetails()+staticThree+("".equals(generatedCoi.getCoverageDetails()) ? staticFour+staticFive : generatedCoi.getCoverageDetails());
    }

    public String getCoverageTemplateUtil(HeaderRequestDTO request) {

        Optional<TemplateHistory> previousTemplateHistory = this.templateHistoryRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());
        ObjectMapper mapper = new ObjectMapper();
        String requestJsonString = "";
        try {
            requestJsonString = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (previousTemplateHistory.isPresent()) {
            this.templateHistoryRepository.save(previousTemplateHistory.get().toBuilder()
                    .coverageDetailsRequest(requestJsonString.toString())
                    .build());
        } else {
            this.templateHistoryRepository.save(TemplateHistory.builder()
                            .lob(request.getLob())
                            .templateName(request.getTemplateName())
                            .coverageDetailsRequest(requestJsonString.toString())
                            .build());
        }

        List<CoverageDetails> attributesList = request.getCoverageDetailsList();

//        String basicCoverageDetails = "<tr><td style=\"padding:15px 0 4px 0;vertical-align:top\"><p style=\"margin:0;font-weight:400;font-size:14px;color:#8a8a8a\">display_name</p></td><td style=\"padding:15px 0 4px 0;vertical-align:top\"><p style=\"margin:0;font-weight:400;color:#000;font-size:14px\">{{attribute_value}}</p></td></tr>";
        String basicWhatsIncludedDetails = "<td style=\"text-align:left;width:25%;vertical-align:top;padding-bottom:40px\"><img src=icon_link style=\"height:60px\"><p style=\"margin:2px 0;color:#000;font-size:10px\">name</p><p style=\"margin:0;color:#8a8a8a;font-size:10px\">sub_description</p></td>";
        String basicWhatsCoveredDetails = "<div style=\"width:100%;display:block;margin:50px 0\"><table style=\"border-collapse:collapse;width:100%;border:0\"><tbody><tr><td style=\"width:10%;vertical-align:top\"><img src=link style=\"height:80px\"></td><td style=\"vertical-align:top;padding:0 0 0 30px\"><div style=\"width:70%;display:block\"><h2 style=\"margin:0 0 20px 0;font-weight:400;color:#865cff\">name</h2><p style=\"color:#4a4a4a;font-size:14px;margin:0 0 20px 0;line-height:1.5\">description</p></div></td></tr></tbody></table></div>";
        String whatsIncludedDetails = "";
        String whatsCoveredDetails = "";
        for(CoverageDetails attributes: attributesList) {
            String updatedCoverageDetails = basicWhatsIncludedDetails;

            updatedCoverageDetails = updatedCoverageDetails.replace("name", attributes.getDisplayName());
            updatedCoverageDetails = updatedCoverageDetails.replace("icon_link", attributes.getLink());
            updatedCoverageDetails = updatedCoverageDetails.replace("sub_description", attributes.getSubDescription());

            whatsIncludedDetails += updatedCoverageDetails;
        }
        for(CoverageDetails attributes: attributesList) {
            String updatedCoverageDetails = basicWhatsCoveredDetails;

            updatedCoverageDetails = updatedCoverageDetails.replace("name", attributes.getDisplayName());
            updatedCoverageDetails = updatedCoverageDetails.replace("link", attributes.getLink());
            updatedCoverageDetails = updatedCoverageDetails.replace("description", attributes.getDescription());
//            updatedCoverageDetails = updatedCoverageDetails.replace("description", attributes.getDescription());
//            updatedCoverageDetails = updatedCoverageDetails.replace("sub_description", attributes.getSubDescription());

            whatsCoveredDetails += updatedCoverageDetails;
        }

        return whatsIncludedDetails+staticFour+whatsCoveredDetails+staticFive;

    }

    @Override
    public String createTemplate(HeaderRequestDTO request) {
        Optional<GeneratedCoi> generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());
        if (!generatedCoi.isPresent()) {
            this.generatedCoiRepository.save(GeneratedCoi.builder()
                    .partnerDetails("")
                        .insuredDetails("")
                        .coverageDetails("")
                        .userId("123")
                        .lob(request.getLob())
                        .templateName(request.getTemplateName())
                .build());
            return staticOne+staticTwo+staticThree+staticFour;
        }
        return this.getHtml(generatedCoi.get());
//        return staticOne+generatedCoi.get().getPartnerDetails()+staticTwo+generatedCoi.get().getInsuredDetails()+staticThree+("".equals(generatedCoi.get().getCoverageDetails()) ? staticFour+staticFive : generatedCoi.get().getCoverageDetails());
    }

    @Override
    public String saveGeneratedHtml(HeaderRequestDTO request) {
        Optional<GeneratedCoi> generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());
        if (generatedCoi.isPresent()) {
            String partnerDetails = getHeaderTemplateUtil(request);
            String insuredDetails = createInsuredDetailsUtil(request);
            String coveragesDetails = getCoverageTemplateUtil(request);
            GeneratedCoi generatedCoiFromDB = generatedCoi.get();
            GeneratedCoi modifiedCoi = this.generatedCoiRepository.save(generatedCoiFromDB.toBuilder()
                    .partnerDetails(partnerDetails)
                    .insuredDetails(insuredDetails)
                    .coverageDetails(coveragesDetails)
                    .userId("123")
                    .lob(request.getLob())
                    .templateName(request.getTemplateName())
                    .build());
            return this.getHtml(modifiedCoi);
//            return staticOne+modifiedCoi.getPartnerDetails()+staticTwo+modifiedCoi.getInsuredDetails()+staticThree+("".equals(modifiedCoi.getCoverageDetails()) ? staticFour+staticFive : modifiedCoi.getCoverageDetails());
        } else {
            throw new IllegalArgumentException("No records found for this template name and lob");
        }
    }


    @Override
    public String saveNewGeneratedHtml(HeaderRequestDTO request) {
        Optional<GeneratedCoi> generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(request.getTemplateName(), request.getLob());
        if (!generatedCoi.isPresent()) {
            String partnerDetails = getHeaderTemplateUtil(request);
            String insuredDetails = createInsuredDetailsUtil(request);
            String coveragesDetails = getCoverageTemplateUtil(request);
            GeneratedCoi modifiedCoi =  this.generatedCoiRepository.save(GeneratedCoi.builder()
                    .partnerDetails(partnerDetails)
                    .insuredDetails(insuredDetails)
                    .coverageDetails(coveragesDetails)
                    .userId("123")
                    .lob(request.getLob())
                    .templateName(request.getTemplateName())
                    .build());
            return this.getHtml(modifiedCoi);
//            return staticOne+modifiedCoi.getPartnerDetails()+staticTwo+modifiedCoi.getInsuredDetails()+staticThree+("".equals(modifiedCoi.getCoverageDetails()) ? staticFour+staticFive : modifiedCoi.getCoverageDetails());
        } else {
            throw new IllegalArgumentException("Record already found for this template name and lob");
        }
    }

    @Override
    public List<GeneratedCoi> getTemplatesByLob(String lob) {
        return this.generatedCoiRepository.findAllByLob(lob);
    }

    @Override
    public TemplateHistoryWithHtmlContent getTemplateHistoryByTemplateNameAndLob(String templateName, String lob) {
        if (this.templateHistoryRepository.findByTemplateNameAndLob(templateName, lob).isPresent()){
            TemplateHistory templateHistory =  this.templateHistoryRepository.findByTemplateNameAndLob(templateName, lob).get();
            GeneratedCoi generatedCoi = this.generatedCoiRepository.findByTemplateNameAndLob(templateName, lob).get();
            String htmlContent = this.getHtml(generatedCoi);
            return new TemplateHistoryWithHtmlContent(templateHistory, htmlContent);
        } else {
            throw new IllegalArgumentException("No Records found for this template name and lob");
        }
    }

    private String getHtml(final GeneratedCoi generatedCoi) {
        return staticOne+generatedCoi.getPartnerDetails()+staticTwo+generatedCoi.getInsuredDetails()+staticThree+("".equals(generatedCoi.getCoverageDetails()) ? staticFour+staticFive : generatedCoi.getCoverageDetails());
    }
}
