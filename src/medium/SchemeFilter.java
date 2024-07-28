package medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class SchemeFilter {
	public static void main(String[] args) {
		String filePath = "D://GitHub/JavaPractice/SchemeNames.txt";
		
        String content = readFile(filePath);
        List<JSONObject> schemeList = parseJson(content);
        Map<String, MutualFund> schemeMap = filterData(schemeList);
        
//        for (String key : schemeMap.keySet())
//            System.out.println(schemeMap.get(key).toString());
        
    }

    private static Map<String, MutualFund> filterData(List<JSONObject> schemeList) {
    	Map<String, Integer> specialNamesMap = new HashMap<>();
		Map<String, MutualFund> schemeMap = new HashMap<>();
		
		populateNameMap(specialNamesMap);
		populateSchemeMap(schemeMap, specialNamesMap, schemeList);
		
		return schemeMap;
	}

	private static void populateSchemeMap(Map<String, MutualFund> schemeMap, Map<String, Integer> specialNamesMap, List<JSONObject> schemeList) {
		MutualFund mfObject = new MutualFund();
		String schemeCode = "", schemeName = "", amcName = "";
		Set<String> set = new HashSet<>();
		
		for (JSONObject object : schemeList) {
			mfObject = new MutualFund();
			schemeCode = String.valueOf(object.getInt("schemeCode"));
			schemeName = object.getString("schemeName");
			nameErrorFix(schemeName);
			String[] nameArray = schemeName.split(" ");
//			for(int i=0; i<nameArray.length; i++)
//				nameArray[i] = StringUtils.capitalize(nameArray[i]);
			String amc = nameErrorFix(schemeName).trim();
			
			if(specialNamesMap.containsKey(amc))
				amcName = String.join(" ", Arrays.copyOfRange(nameArray, 0, specialNamesMap.get(amc)));
			else if(amc.contains("MOF"))
				continue;
			else
				amcName = amc;
			
			amcName = (amcName.toUpperCase());
			mfObject.setSchemeCode(schemeCode);
			mfObject.setSchemeName(schemeName);
			mfObject.setAmcName(amcName);
			mfObject.setInstrument((schemeName.contains("ETF") && !schemeName.contains("FOF")) ? "ETFs" : "Fund");
			set.add(amcName);
			System.out.println("amcName="+amcName);
			schemeMap.put(schemeCode, mfObject);
		}
		System.out.println("HashSet="+set);
		System.out.println("Length="+set.size());
	}

	private static String capitalize(String amcName) {
		String[] words = amcName.split("\\s"); 
		StringBuilder result = new StringBuilder(); 
        for (String word : words) { 
            result.append(Character.toTitleCase(word.charAt(0))) 
                  .append(word.substring(1)) 
                  .append(" "); 
        } 
  
        return result.toString().trim(); 
	}

	private static String nameErrorFix(String schemeName) {
//		schemeName = capitalize(schemeName);
		String[] nameArray = schemeName.split(" ");
		int len = nameArray.length;
		String name0 = nameArray[0];
		String name1 = (len > 1) ? nameArray[1] : "";
		if(name0.matches("\\w+-\\w+")) {
			nameArray[0] = name0.replaceAll("-", " ");
//			nameArray = String.join(" ", Arrays.copyOfRange(nameArray, 0, len)).split(" ");
			schemeName = String.join(" ", Arrays.copyOfRange(nameArray, 0, len));
//			System.out.println("schemeName="+schemeName);
			nameErrorFix(schemeName);
//			name0 = name0.split("-")[0];
//			name1 = String.join(" ", Arrays.copyOfRange(nameArray, 0, );
		}
		name0 = nameArray[0];
		name1 = (len > 1) ? nameArray[1] : "";
//		if(name0.equalsIgnoreCase("Birla"))
//			return "Aditya";
		if(name0.contains("Benchmark"))
			return "Benchmark";
		if(name0.equalsIgnoreCase("rincipal"))
			return "Principal";
		if(name0.equalsIgnoreCase("DSP  "))
			return "DSP";
		if(name0.equalsIgnoreCase("bsl"))
			return "Birla";
		if(name0.equalsIgnoreCase("dws"))
			return "DWS";
		if(name0.equalsIgnoreCase("NIPPON"))
			return "Nippon";
		if(name0.equalsIgnoreCase("REDEEMED"))
			return name1;
		if(name0.equalsIgnoreCase("Shariah"))
			return "Benchmark";
		if(name0.contains("UTI"))
			return "UTI";
		if(name0.contains("HDF"))
			return "HDFC";
		if(name0.contains("Adi"))
			return "Aditya";
		if(name0.contains("OLD"))
			return "SBI";
		if(name0.contains("DFC"))
			return "IDFC";
		if(name0.contains("MOF"))
			return "Motilal Oswal";
		if(name0.contains("TFMP"))
			return "Tata";
//		if(name0.contains("JM"))
//			name0 = name0.replace("-", "");
		
		if(name0.equalsIgnoreCase("Baroda") && name1.equalsIgnoreCase("Pioneer"))
			return "Baroda2";
		if(name0.equalsIgnoreCase("Baroda") && name1.equalsIgnoreCase("BNP"))
			return "Baroda3";
		if(name0.equalsIgnoreCase("Principal") && name1.equalsIgnoreCase("PNB"))
			return "Principal2";
		if(name0.equalsIgnoreCase("DSP") && name1.equalsIgnoreCase("BlackRock"))
			return "DSP2";
		if(name0.equalsIgnoreCase("Mahindra") && name1.equalsIgnoreCase("Manulife"))
			return "Mahindra2";
		if(name0.equalsIgnoreCase("Templeton") && name1.equalsIgnoreCase("Templeton"))
			return "Templeton2";
		if(name0.equalsIgnoreCase("Birla") && !name1.equalsIgnoreCase("Sun"))
			return "Birla Sun Life";
		if(name0.equalsIgnoreCase("Motilal") && name1.equals("OSwal"))
			name1 = "Oswal";
		
		return name0; // .replaceAll("\\W", "");
	}

	private static void populateNameMap(Map<String, Integer> specialNamesMap) {
		specialNamesMap.put("Canara", 	2);
		specialNamesMap.put("Bajaj", 	2);
		specialNamesMap.put("ICICI", 	2);
		specialNamesMap.put("Nippon", 	2);
		specialNamesMap.put("Mirae", 	2);
		specialNamesMap.put("Parag", 	2);
		specialNamesMap.put("PGIM", 	2);
		specialNamesMap.put("Motilal", 	2);
		specialNamesMap.put("WhiteOak", 2);
		specialNamesMap.put("PGIM", 	2);
		specialNamesMap.put("Invesco", 	2);
		specialNamesMap.put("Morgan", 	2);
		specialNamesMap.put("Franklin", 2);
		specialNamesMap.put("DBS", 		2);
		specialNamesMap.put("Old", 		2);
		specialNamesMap.put("DHFL",		2);
		specialNamesMap.put("BOI", 		2);
		specialNamesMap.put("ABN", 		2);
		
		specialNamesMap.put("DSP2",		 2);
		specialNamesMap.put("Principal2",2);
		specialNamesMap.put("Mahindra2", 2);
		specialNamesMap.put("Templeton2",2);
		specialNamesMap.put("Baroda2", 	 2);
		
		specialNamesMap.put("Baroda3", 	3);
		specialNamesMap.put("Bank", 	3);
		specialNamesMap.put("Birla3", 	3);
		
		specialNamesMap.put("Aditya", 	4);
	}

	private static List<JSONObject> parseJson(String content) {
        List<JSONObject> jsonList = new ArrayList<>();

        try {
        	if (content == null)
        		throw new Exception("Empty JSON");

        	JSONArray jsonArr = new JSONArray(content);
            for (int i = 0; i < jsonArr.length(); i++)
                jsonList.add(jsonArr.getJSONObject(i));
            
        } catch (Exception e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
        }
		return jsonList;
	}

	public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            System.err.println("An IOException occurred: " + e.getMessage());
            return null;
        }
        return content.toString();
    }
}

class MutualFund {
	private Integer id;
    private String amcName;
	private String schemeName;
    private String option;
    private String planType;
	private String schemeCode;
	private String instrument;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAmcName() {
		return amcName;
	}

	public void setAmcName(String amcName) {
		this.amcName = amcName;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}
	
	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
	public MutualFund() {
		super();
	}

	public MutualFund(String amcName, String schemeName, String schemeCode, String option, String planType) {
		super();
        this.amcName = amcName;
        this.schemeName = schemeName;
        this.schemeCode = schemeCode;
        this.option = option;
        this.planType = planType;
    }
	
	public String toString() {
		String print = this.schemeCode + " : " + this.instrument + " : " + this.getAmcName() + " : " + this.getSchemeName();
		return print;
	}
}