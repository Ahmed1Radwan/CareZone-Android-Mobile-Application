package com.ahmedhamdy.healthcare.Medical;

import com.ahmedhamdy.healthcare.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MedicinesController {
    public static byte index, index1 = -1;

    public List<List<Integer>> medicinesNames = new ArrayList<>();
    public List<List<Integer>> descriptions = new ArrayList<>();
    public List<List<Integer>> imageId = new ArrayList<>();
    public List<List<Integer>> websites = new ArrayList<>();

    public HashMap<String,List<String>> cloudMedicinesNames = new HashMap<>();
    public HashMap<String,List<String>> cloudDescriptions = new HashMap<>();
    public HashMap<String,List<String>> cloudImages = new HashMap<>();
    public HashMap<String,List<String>> clouldWebsites = new HashMap<>();

    private static MedicinesController medicinesController = new MedicinesController();
    private MedicinesController(){}

    public static MedicinesController getInstance(){
        return medicinesController;
    }

    public void initialize(){

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.coronaVitaminC,R.string.coronaZinc,R.string.coronaCetal,R.string.coronaPanadol)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.coronaVitaminCDescription, R.string.coronaZincDescription, R.string.coronaCetalDescription, R.string.coronaPanadolDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.corona_vitaminc, R.drawable.corona_zinc, R.drawable.corona_cetal, R.drawable.corona_panadol)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.coronaVitaminCWebsite, R.string.coronaZincWebsite, R.string.coronaCetalCWebsite, R.string.coronaPanadolCWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.feverNuberol, R.string.feverAspirin, R.string.feverActironCF, R.string.feverAcetaminophen, R.string.feverPanadol)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.feverNuberolDescription, R.string.feverAspirinDescription, R.string.feverActironCFDescription, R.string.feverAcetaminophenDescription, R.string.feverPanadolDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.fever_nuberol, R.drawable.fever_aspirin, R.drawable.fever_actiron, R.drawable.fever_acetaminophen, R.drawable.fever_panadol)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.feverNuberolWebsite, R.string.feverAspirinWebsite, R.string.feverActironCFWebsite, R.string.feverAcetaminophenWebsite, R.string.feverPanadolWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.headacheProthiaden, R.string.headacheDisprin, R.string.headacheParacetamol, R.string.headacheZolmitriptan, R.string.headacheNaproxen)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.headacheProthiadenDescription, R.string.headacheDisprinDescription, R.string.headacheParacetamolDescription, R.string.headacheZolmitriptanDescription, R.string.headacheNaproxenDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.headache_prothiaden, R.drawable.headache_disprin, R.drawable.headache_paracetamol, R.drawable.headache_zolmitriptan, R.drawable.headache_naproxen)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.headacheProthiadenWebsite, R.string.headacheDisprinWebsite, R.string.headacheParacetamolWebsite, R.string.headacheZolmitriptanWebsite, R.string.headacheNaproxenWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.coldAndCoughLeflox, R.string.coldAndCoughCodeine, R.string.coldAndCoughArinac, R.string.coldAndCoughDextromethorphan, R.string.coldAndCoughNoscapine)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.coldAndCoughLefloxDescription, R.string.coldAndCoughCodeineDescription, R.string.coldAndCoughArinacDescription, R.string.coldAndCoughDextromethorphanDescription, R.string.coldAndCoughNoscapineDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.cough_and_cold_leflox, R.drawable.cough_and_cold_codeine, R.drawable.cough_and_cold_arinac, R.drawable.cough_and_cold_dextromethrophan, R.drawable.cough_and_cold_noscapine)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.coldAndCoughLefloxWebsite, R.string.coldAndCoughCodeineWebsite, R.string.coldAndCoughArinacWebsite, R.string.coldAndCoughDextromethorphanWebsite, R.string.coldAndCoughNoscapineWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.vomitingDronabinol, R.string.vomitingAprepitant, R.string.vomitingDexamethasone, R.string.vomitingNabilone, R.string.vomitingDroperidol)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.vomitingDronabinolDescription, R.string.vomitingAprepitantDescription, R.string.vomitingDexamethasoneDescription, R.string.vomitingNabiloneDescription, R.string.vomitingDroperidolDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.vomiting_dronabinol, R.drawable.vomiting_aprepitant, R.drawable.vomiting_dexamethasone, R.drawable.vomiting_nabilone, R.drawable.vomiting_droperidol)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.vomitingDronabinolWebsite, R.string.vomitingAprepitantWebsite, R.string.vomitingDexamethasoneWebsite, R.string.vomitingNabiloneWebsite, R.string.vomitingDroperidolWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.constipationBuscopan, R.string.constipationLactulose, R.string.constipationGlucomannan, R.string.constipationBisacodyl, R.string.constipationLomotil)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.constipationBuscopanDescription, R.string.constipationLactuloseDescription, R.string.constipationGlucomannanDescription, R.string.constipationBisacodylDescription, R.string.constipationLomotilDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.constipation_buscopan, R.drawable.constipation_lactulose, R.drawable.constipation_glucomannan, R.drawable.constipation_bisacodyl, R.drawable.constipation_lomotil)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.constipationBuscopanWebsite, R.string.constipationLactuloseWebsite, R.string.constipationGlucomannanWebsite, R.string.constipationBisacodylWebsite, R.string.constipationLomotilWebsite)));

        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.bloodPressureLisinopril, R.string.bloodPressureAtenolol, R.string.bloodPressureActrapidPenfill, R.string.bloodPressureAmaryl, R.string.bloodPressureDiamicronMR)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.bloodPressureLisinoprilDescription, R.string.bloodPressureAtenololDescription, R.string.bloodPressureActrapidPenfillDescription, R.string.bloodPressureAmarylDescription, R.string.bloodPressureDiamicronMRDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.lisinopril, R.drawable.atenolol, R.drawable.actrapid_penfill, R.drawable.amaryl_2mg, R.drawable.diamicron_mp)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.bloodPressureLisinoprilWebsite, R.string.bloodPressureAtenololWebsite, R.string.bloodPressureActrapidPenfillWebsite, R.string.bloodPressureAmarylWebsite, R.string.bloodPressureDiamicronMRWebsite)));


        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.immuneColchicine, R.string.immuneHydroxychloroquine, R.string.immuneMethotrexate, R.string.immuneDapsone, R.string.immuneSulfasalazine)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.immuneColchicineDecription, R.string.immuneHydroxychloroquineDescription, R.string.immuneMethotrexateDecription, R.string.immuneDapsoneDescription, R.string.immuneSulfasalazineDecription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.immune_clochicine, R.drawable.immune_hydroquine, R.drawable.immune_methotrexate, R.drawable.immune_dapsone, R.drawable.immune_sulfa)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.immuneColchicineWebsite, R.string.immuneHydroxychloroquineWebsite, R.string.immuneMethotrexateWebsite, R.string.immuneDapsoneWebsite, R.string.immuneSulfasalazineWebsite)));


        medicinesNames.add(new ArrayList<>(Arrays.asList(R.string.dailyHealthCareGlucobay, R.string.dailyHealthCareGNCBurn60, R.string.dailyHealthCareCalciumPluswithMagnesium, R.string.dailyHealthCareBetaCarotene, R.string.dailyHealthCareADVANTEC, R.string.dailyHealthCareAscard,
                R.string.dailyHealthCareLoprin, R.string.dailyHealthCareCLENILNEBULISER, R.string.dailyHealthCareMontelukast)));
        descriptions.add(new ArrayList<>(Arrays.asList(R.string.dailyHealthCareGlucobayDescription, R.string.dailyHealthCareGNCBurn60Description, R.string.dailyHealthCareCalciumPluswithMagnesiumDescription, R.string.dailyHealthCareBetaCaroteneDescription, R.string.dailyHealthCareADVANTECDescription, R.string.dailyHealthCareAscardDescription,
                R.string.dailyHealthCareLoprinDescription, R.string.dailyHealthCareCLENILNEBULISERDescription, R.string.dailyHealthCareMontelukastDescription)));
        imageId.add(new ArrayList<>(Arrays.asList(R.drawable.glucobay, R.drawable.gnc_burn, R.drawable.calcium_plus_with_magnesium, R.drawable.beta_carotene, R.drawable.advantec, R.drawable.ascard,
                R.drawable.loprin, R.drawable.clenil_nebuliser, R.drawable.montelukast)));
        websites.add(new ArrayList<>(Arrays.asList(R.string.dailyHealthCareGlucobayWebsite, R.string.dailyHealthCareGNCBurn60Website, R.string.dailyHealthCareCalciumPluswithMagnesiumWebsite, R.string.dailyHealthCareBetaCaroteneWebsite, R.string.dailyHealthCareADVANTECWebsite, R.string.dailyHealthCareAscardWebsite,
                R.string.dailyHealthCareLoprinWebsite, R.string.dailyHealthCareCLENILNEBULISERWebsite, R.string.dailyHealthCareMontelukastWebsite)));

    }

    public List<Integer> getMedicines(){
        return medicinesNames.get(index);
    }

    public List<Integer> getDescription(){
        return descriptions.get(index);
    }
    public List<Integer> getImagesId(){
        return imageId.get(index);
    }
    public int getWebUrl(){
        return websites.get(index).get(index1);
    }
}
