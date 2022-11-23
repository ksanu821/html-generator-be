package com.acko.htmlgenerator.icons;

import java.util.HashMap;
import java.util.Map;

public class IconsMapping {

     private Map<String, String> iconsMap  = new HashMap<String, String>() {{
        put("hospital_daily_allowance", "https://www.acko.com/static/images/rapido/hospitalization_daily_allowance_1x.png");
        put("personal_accident", "https://www.acko.com/static/images/abhibus/personal_accident_1x.png");
        put("emi_protection", "https://www.acko.com/static/images/aubank/emi_protection_1x.png");
        put("critical_illness", "https://www.acko.com/static/images/aubank/critical_illness_1x.png");
    }};

    public String getIconForCoverId(String coverId) {
        return this.iconsMap.get(coverId);
    }
}
