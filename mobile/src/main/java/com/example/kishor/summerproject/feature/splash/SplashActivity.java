package com.example.kishor.summerproject.feature.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kishor.summerproject.R;
import com.example.kishor.summerproject.feature.auth.LoginPage;
import com.example.kishor.summerproject.feature.category.Product;
import com.example.kishor.summerproject.feature.home.Category;
import com.example.kishor.summerproject.utils.GlobalUtils;

import java.util.ArrayList;
import java.util.Random;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    private ArrayList<Category> categoryList = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> productArrayLists = new ArrayList<>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        if (GlobalUtils.getFromPrefBoolean("IsFirstLaunch", this)) {
            GlobalUtils.savePrefBoolean("IsFirstLaunch", false, SplashActivity.this);
            setData();
        }
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, LoginPage.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void setData() {
        Realm realm = Realm.getDefaultInstance();
        list.clear();
        list.add("Cement");
        list.add("Pipes");
        list.add("Wire Mesh");
        list.add("Steel");
        list.add("Tank");
        list.add("Glavanized sheet");
        list.add("Iron Coil");
        list.add("Hardware");

        for (String item : list) {
            Category category = new Category();
            category.setTitle(item);
            switch (item) {
                case "Cement":
                    Category cementCategory = new Category();
                    category.setTitle(item);
                    category.setProductArrayList(setCementProduct());
                    category.setImage(R.drawable.cements);
                    break;
                case "Pipes":
                    category.setProductArrayList(setPipeProducts());
                    category.setImage(R.drawable.water_pipes);
                    break;
                case "Wire Mesh":
                    category.setProductArrayList(setNetProducts());
                    category.setImage(R.drawable.balcony_stainless_net_steel);
                    break;
                case "Steel":
                    category.setProductArrayList(setSteelProducts());
                    category.setImage(R.drawable.hama_steel);
                    break;
                case "Tank":
                    category.setProductArrayList(setTankProduct());
                    category.setImage(R.drawable.nepatop_tank);
                    break;
                case "Glavanized sheet":
                    category.setProductArrayList(setSheetsProducts());
                    category.setImage(R.drawable.galvanizes_sheet);
                    break;
                case "Iron Coil":
                    category.setProductArrayList(setIronCoilProducts());
                    category.setImage(R.drawable.coil_rolls);
                    break;
                case "Hardware":
                    category.setProductArrayList(setHardwareProducts());
                    category.setImage(R.drawable.hardware);
                    break;
            }
            categoryList.add(category);
        }
        for (final Category category : categoryList)
            realm.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
                @Override
                public void execute(Realm realm) {
                    // increment index
                    Number currentIdNum = realm.where(Category.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    category.setId(nextId);
                    //...
                    realm.insertOrUpdate(category); // using insert API
                }
            });
    }

    private ArrayList<Product> setTankProduct() {

        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("NEPATOP 500 Ltr Water Tanks");
        productArrayLists.add("NEPATOP 1000 Ltr Water Tanks");
        productArrayLists.add("NEPATOP 1500 Ltr Water Tanks");
        productArrayLists.add("NEPATOP 2000 Ltr Water Tanks");
        productArrayLists.add("NEPATOP 3000 Ltr Water Tanks");
        productArrayLists.add("NEPATOP 5000 Ltr Water Tanks");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getTankProductDescription(item));
            product.setImage(getTankProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setCementProduct() {
        ArrayList<Product> productList = new ArrayList<>();

        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Jagadamba Cement");
        productArrayLists.add("Sagarmatha Cement");
        productArrayLists.add("Agni cement");
        productArrayLists.add("Arghakanchi Cement");
        productArrayLists.add("Shivam Cement");
        productArrayLists.add("Goenka Gold Cement");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getCementProductDescription(item));
            product.setImage(getCementProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setHardwareProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Shovel");
        productArrayLists.add("Hacksaw Blade");
        productArrayLists.add("Hand Tools");
        productArrayLists.add("Pick Axe");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getHardwareProductDescription(item));
            product.setImage(getHardwareProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setIronCoilProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Binding Wire");
        productArrayLists.add("Black Wire");
        productArrayLists.add("Barbed Wire");
        productArrayLists.add("Concertina Wire");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getIronCoilProductDescription(item));
            product.setImage(getIronCoilProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setSteelProducts() {

        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Laxmi Steel");
        productArrayLists.add("Panchakanya Steel");
        productArrayLists.add("Ambe Steel");
        productArrayLists.add("Hulas Steel");
        productArrayLists.add("Hama Steel");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getsteelProductDescription(item));
            product.setImage(getsteelProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setNetProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Twill Stainless Steel Wire Mesh");
        productArrayLists.add("Square Stainless Steel Wire Mesh");
        productArrayLists.add("Rectangular Stainless Steel Wire Mesh");
        productArrayLists.add("Vibrating Screen Wire Mesh");
        productArrayLists.add("Grey Woven Wire Mesh");
        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getWireProductDescription(item));
            product.setImage(getWireProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setPipeProducts() {

        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("HDPE pipe");
        productArrayLists.add("PVC pipe");
        productArrayLists.add("PPR Pipe");

        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getpipeProductDescription(item));
            product.setImage(getpipeProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private ArrayList<Product> setSheetsProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        ArrayList<String> productArrayLists = new ArrayList<>();
        productArrayLists.add("Plain Plates");
        productArrayLists.add("PVC Sheets");
        productArrayLists.add("CR Sheet");
        productArrayLists.add("Chekker Plate");
        productArrayLists.add("HR Plate");

        for (String item : productArrayLists) {
            Random r = new Random();
            int id = r.nextInt(1000);
            final Product product = new Product();
            product.setDescription(getSheetProductDescription(item));
            product.setImage(getSheetProductImage(item));
            product.setTitle(item);
            product.setQuantity("0");
            product.setId(id);
            productList.add(product);

        }
        return productList;
    }

    private String getsteelProductDescription(String item) {
        String description = "";
        if (item == "Ambe Steel")
            description = "TMT is an acronym for the phrase \"Thermo-Mechanical Treatment\". During the last few years the civil engineering sector has increased its demand for low cost reinforcing steel bars with a guaranteed yield point of 500N/mm2. This demand was a result of standard specifications of all developed industrial countries. The use of such grade high strength rebar reduces civil construction cost because of:\n" +
                    "\n" +
                    "- Saving in reinforcement\n" +
                    "\n" +
                    "- Reduced labor cost\n" +
                    "\n" +
                    "- Lower transportation costs\n" +
                    "\n" +
                    "- Easy processing due to high unsure ability\n" +
                    "\n" +
                    "- The development of the patented thermex cooling system is an in-line process and the high strength Thermex rebars are ready for dispatch to the customer almost immediately after rolling is complete unlike the case of CTD rebar which takes a few days & results in large inventory levels in the plant.";
        else if (item == "Panchakanya Steel")
            description = "It stands for –TMT - Thermo - mechanical treatment and is a metallurgical process that integrates work hardening and heat -treatment into a single process.The quenching process produces a high strength - bar.The process quenches the surface layer of the bar, which pressurizes and deforms the crystal structure of intermediate layers, and simultaneously begins to temper the quenched layers using the heat from the bar 's core.";
        else if (item == "Laxmi Steel")
            description = "Manufactured by TMT technology, Laxmi Steels uses German Thermex technology which is considered to be the best in the world. The process of creating the steel involves on-line Thermo Mechanical Treatment in the three stages that are Quenching, Self tempering and Atmospheric cooling. The hot steel rod, after the final rolling process, is passed through a water cooling chamber which causes the formation of high strength tampered martensite structure on the surface and an austenitic structure at the core.";
        else if (item == "Hulas Steel")
            description = "Hulas Steel believes in creating sustainable growth while balancing utilization of natural resources and social development in its business decisions. It also believes in pursuing its business objectives ethically, transparently and with accountability to its stakeholders across the value chain. Hulas is committed to promote integrated responsible behavior and value for social and environmental well-being.\n" +
                    "\n" +
                    "Hulas commitment to do business responsibly is built into the core values of the Company to conduct every aspect of business responsibly and sustainably.\n" +
                    "\n" +
                    "It relies on:\n" +
                    "\n" +
                    "A dynamic leadership\n" +
                    "Adherences to core values\n" +
                    "A well-articulated Enterprise Risk Management framework.\n" +
                    "Practices that seek to sustain and enhance the long term competitive advantage of Hulas  with care for the society and environment.";
        else if (item == "Hama Steel")
            description = "Hama Iron and Steel has a strict policy of not using Low Quality Billets (Raw Material). We use only the highest quality Prime Quality Billets. Our products meet the highest quality standards and have been used by corporate houses, embassies, government agencies and the common man.";
        return description;
    }

    private int getsteelProductImage(String item) {
        int image = 0;
        if (item == "Ambe Steel")
            image = R.drawable.ambee_steel;
        else if (item == "Panchakanya Steel")
            image = R.drawable.panchakanya;
        else if (item == "Laxmi Steel")
            image = R.drawable.laxmi_steel;
        else if (item == "Hulas Steel")
            image = R.drawable.hulas;
        else if (item == "Hama Steel")
            image = R.drawable.hama_steel;
        return image;
    }

    private String getpipeProductDescription(String item)
    {
        String description= "";
        if(item=="HDPE pipe")
            description="PH Plastic Group HDPE pipe is a perfect solution for municipal engineering system and urban water supply and drainage system. HDPE pipe adopts high-quality original  PE100 raw material, with the size range of DN 16-1600mm, and the performance conforms to ISO4427 standard and other national certification standards. HDPE pipes for water supply can be manufactured with different rated pressures.";

       else if(item=="PVC pipe")
            description="PH Plastic Group PVC ventilation pipe is widely used in building engineering and tunnel construction projects for the air transmission and air conditioning gas transmission.\n" +
                    "PVC ventilation pipes are made of high quality raw PVC material, the size range of DN20 - 1200mm, and the performance conforms to ISO 1452 standard.\n";
        else if (item =="PPR Pipe" )
            description ="Repol PPR (Polypropylene Random Copolymer) is used to produce pipes and tubing for a variety of domestic and industrial hot and cold water applications. In addition, it is designed to resist temperatures and pressures encountered in hot water systems.";


    return description;
    }
    private int getpipeProductImage(String item) {
        int image = 0;
        if (item == "HDPE pipe")
            image = R.drawable.hdpe_pipe;
        else if (item == "PVC pipe")
            image = R.drawable.pvc_pipe;
        else if (item =="PPR Pipe"  )
            image = R.drawable.ppr_pipes;
        return image;
    }
    private String getCementProductDescription(String item)
    {
        String description= "";
        if(item=="Jagadamba Cement")
            description="Established in the year 2001, Jagdamba Cement is a distinct brand name in the Nepalese market. This company since then has been the market leader in cement manufacturing in Nepal. This company also holds the largest market share in the Nepalese market. As the top cement producer in Nepal, it is the only manufacturer of OPC, PPC and PSC cements.\n"+"\n"+"Price NRS 680";
       else if(item=="Sagarmatha Cement")
            description="Sagarmatha Cement was officially launched in June 2013, by Ghorahi Cement Industry Pvt. Ltd.. Sagarmatha OPC complies with Nepal Standards and it was at the time working for international recognition by obtaining the ISO 9001 certification.\n" +
                    "\n" +
                    "Price 640\n";
        else if (item =="Agni cement" )
            description ="The Agni Cement Industries Pvt. Ltd is located at Gurwaniya-7, Rupandehi District Lumbini Zone which towards west side on Bhairahawa – Lumbini road. Agni Cement Industries Pvt. Ltd is an ISO 9001:2008 certified company with Nepal Standards (NS) Certified products. \n" +
                    "\n" +
                    "Price 670\n";

        else if (item=="Arghakanchi Cement")
             description=" Arghakhanchi Cement Pvt. Ltd.,renamed from original establishment named as Dynesty Industries Nepal Private Limited in 2065/03/15 is a Private Limited Company, duly registered under the provisions of Companies act, 2053 in the year 2055, with its registered office at Thapathali, Kathmandu (earlier- Lalitpur Sub-metropolis-2, Sanepa). The company has been established with an objective of establishment and operation of a clinkerand cementmanufacturing unit.\n" +
                     "\n" +
                     "Price:775\n";
         else if (item=="Shivam Cement")
             description="Shivam cements was founded in the year 2003. It began commercial production from the year 2011 and is the largest manufacturing Greenfield project in Nepal. It is currently producing 3000 TPD cement and 1900 TPD clinker from the company’s self owned limestone quarries. With a broad vision of business, Shivam Cement Private Limited was converted into Public Limited Company in the year 2015 to provide its customers and the stakeholders to be a part of the company.\n" +
                     "\n" +
                     "Price:825\n";
         else if (item=="Goenka Gold Cement")
             description="Goenka Solid Cement is a brand of Portland Pozzolana Cement (OPC) produced by Goenka Cement (P) Ltd. Goenka Gold Cement is NS-49 certified and easily available across the dealer network in the country.\n" +
                     "\n" +
                     "Price:770\n";

        return description;
    }
    private int getCementProductImage(String item) {
        int image = 0;
        if (item == "Jagadamba Cement")
            image = R.drawable.jadgamba_cement;
        else if (item == "Sagarmatha Cement")
            image = R.drawable.sagarmatha_cement;
        else if (item == "Agni cement")
            image = R.drawable.agni;
        else if (item == "Arghakanchi Cement")
            image = R.drawable.arghakhanchi;
        else if (item == "Shivam Cement")
            image = R.drawable.shivam;
        else if (item == "Goenka Gold Cement")
            image = R.drawable.goenka;

        return image;
    }

    private String getWireProductDescription(String item) {
        String description = "";
        if (item == "Twill Stainless Steel Wire Mesh")
            description =
                    "Material\t:\tStainless Steel\n" +
                    "Weave Type\t:\tTwill\n" +
                    "Mesh Size\t:\t10-50 per inch, 50-100 per inch, 100-150 per inch, 150-200 per inch\n" +
                    "Material Grade\t:\tSS304, SS316, Fine SS\n" +
                    "Surface Finish Coating\t:\tMill Finish\n" +
                    "Usage\t:\tIndustrial, Agricultural, Domestic\n";
        else if (item == "Square Stainless Steel Wire Mesh")
            description =
                    "Type\t:\tWelded Wire Mesh\n" +
                    "Thickness\t:\t8 - 15 mm\n" +
                    "Mesh Size\t:\t10-50 per inch\n" +
                    "Wire Diameter\t:\t1.25-3.5 mm\n" +
                    "Hole Shape\t:\tSquare\n" +
                    "Surface Treatment\t:\tGalvanized\n" +
                    "Usage\t:\tDefence,Domestic,Agricultural\n" +
                    "\n";
        else if (item == "Rectangular Stainless Steel Wire Mesh")
            description =
                    "Material\t:\tSS304, SS316, Fine SS, Medium SS\n" +
                    "Mesh Size(per inch)\t:\t0-10 , 10-50, 50-100 , 100-150 , 150-200\n" +
                    "Weave Type\t:\tHexagonal, Twill\n" +
                    "Surface Finish Coating\t:\tMill Finish\n" +
                    "Condition\t:\tNew\n" +
                    "Type\t:\tElectric Wire Mesh, Expanded Wire Mesh, Welded Wire Mesh\n" +
                    "Minimum Order Quantity\t:\t1 Square Feet\n";
        else if (item == "Vibrating Screen Wire Mesh")
            description =
                    "Material\t:\tStainless Steel\n" +
                    "Wire Diameter\t:\t0.3mm to 16mm\n" +
                    "Hole Size\t:\t1.5mm - 50mm\n" +
                    "Minimum Order Quantity\t:\t500 Square Feet\n";

        else if (item == "Grey Woven Wire Mesh")
            description =
                    "Weave Type\t:\tHexagonal\n" +
                    "Type\t:\tWoven\n" +
                    "Usage\t:\tIndustrial, Agricultural, Domestic, Defence\n" +
                    "Brand\t:\tBanaraswala Metal Crafts\n" +
                    "Color\t:\tGrey\n" +
                    "Width\t:\t5mm\n";
        return description;
    }

    private int getWireProductImage(String item) {
        int image = 0;
        if (item == "Twill Stainless Steel Wire Mesh")
            image = R.drawable.twill_mesh;
        else if (item == "Square Stainless Steel Wire Mesh")
            image = R.drawable.square_mesh;
        else if (item == "Rectangular Stainless Steel Wire Mesh")
            image = R.drawable.rectangular_mesh;
        else if (item == "Vibrating Screen Wire Mesh")
            image = R.drawable.vibrating_mesh;
        else if (item == "Grey Woven Wire Mesh")
            image = R.drawable.grey_woven;
        return image;
    }

    private String getSheetProductDescription(String item)
    {
        String description= "";
        if(item=="Plain Plates")
            description="Name\tGalvanized Steel Coil/Plate\n" +
                    "Technical Standard: \tJIS 3302 /  ASTM A653  /    EN10143 \n" +
                    "Grade\tDX51D / DX52D/ DX53D/ S250, S280, 320GD\n" +
                    "Types:    \tCommercial / Drawing / Deep Drawing / Structural  quality \n" +
                    "Width\t650/726/820/914/1000/1200/1219/1220/1250mm \n" +
                    "Thickness\t0.12-1.0mm (0.12-0.5mm is the most advantage thickness)\n" +
                    "Type of  coating:    \tGalvanized   \n" +
                    "Zinc coating\tZ60-150g/m²\n" +
                    "Surface treatment\tchromed / skin pass/ oiled/slightly oiled/ dry/  anti-fingerprint\n" +
                    "Surface structure:    \tzero spangle / minimized spangle /  regular spangle/ big regular\n" +
                    "Package:      \tProperly packed for ocean freight exportation  in 20' ' containers\n" +
                    "Application:\tIndustrial panels, roofing and siding for painting\n";
        else if(item=="PVC Sheets")
            description="Material\tSteel / Stainless Steel\n" +
                    "Surface Treatment\tGalvanised\n" +
                    "Features\tWater Proof\n" +
                    "Manufacturing Technique\tCold Rolled\n" +
                    "Technique\tCold Rolled\n" +
                    "Material Grade\tSS316\n";
        else if (item =="CR Sheet" )
            description ="Thickness\tUpto 1.5mm\n" +
                    "Material\tCold Rolled\n" +
                    "Length\t2500mm\n" +
                    "Width\t1260mm\n" +
                    "Technique\tCold Rolled\n";

        else if (item=="Chekker Plate")
            description=" Min Thickness\t1.8 mm\n" +
                    "Max Thickness\t10 mm\n" +
                    "Min Length\t2000 mm\n" +
                    "Max Length\t12000 mm\n" +
                    "Min Width\t1000 mm\n";
        else if (item=="HR Plate")
            description="Minimum Order Quantity\t500 Kilogram\n" +
                    "Thickness\t1.2 - 25.4mm\n" +
                    "Width\t900-2100 mm\n" +
                    "Grades\tRe-rolling/ Drawing Grades,Tube and Pipe/ Forming Grades,Structural/ Medium Tensile Tube/ Forming Grades,LPG/ Low Pressure Vessel Grades,HSLA Grades,Medium Carbon Grades,Weather Resistance Grades,Line Pipe Grades,Chequered Plates\n";
        return description;
    }
    private int getSheetProductImage(String item) {
        int image = 0;
        if (item == "Plain Plates")
            image = R.drawable.galvanized;
        else if (item == "PVC Sheets")
            image = R.drawable.plaingisheet;
        else if (item == "CR Sheet")
            image = R.drawable.crsheet;
        else if (item == "Chekker Plate")
            image = R.drawable.chekkerplate;
        else if (item == "HR Plate")
            image = R.drawable.hrsheet;

        return image;
    }

    private String getTankProductDescription(String item) {
        String description = "";
        if (item == "NEPATOP 500 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 36.5\"\n" +
                    "•\tDiameter: 35\"\n" +
                    "•\tWanty:20 Years\n" +
                    "NPR 5,500\n";
        else if (item == "NEPATOP 1000 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 47\"\n" +
                    "•\tDiameter: 42.5\"\n" +
                    "•\tWarranty:20 Years\n" +
                    "\n" +
                    "NPR 11400\n";
        else if (item == "NEPATOP 1500 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 49\"\n" +
                    "•\tDiameter: 51\"\n" +
                    "•\tWarranty:20 Years\n" +
                    "NPR 16350\n";
        else if (item == "NEPATOP 2000 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 63\"\n" +
                    "•\tDiameter:51\"\n" +
                    "•\tWarranty:20 Years\n" +
                    "NPR 21700\n";


        else if (item == "NEPATOP 3000 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 79\"\n" +
                    "•\tDiameter: 57\"\n" +
                    "•\tWarranty:20 Years\n" +
                    "NPR 32550\n";

        else if (item == "NEPATOP 5000 Ltr Water Tanks")
            description ="•\tBrand: Nepatop\n" +
                    "•\tManufacture under Rotational Moulding Technology\n" +
                    "•\tMade by 100% pure imported food grade materials\n" +
                    "•\tStore water hygenic and safe under all circumstances and climate\n" +
                    "•\tDurable and portable\n" +
                    "•\tFree from leakage, rust and corrosion\n" +
                    "•\tMulti purpose usages, such as households, hospitals, hotels, offices, industries, educational institution\n" +
                    "•\tMade of High quality polymers & can be use in construction sites for storing Fuel, Lubricants and Chemicals\n" +
                    "•\tColor: Black\n" +
                    "•\tHeight: 73\"\n" +
                    "•\tDiameter: 76\"\n" +
                    "•\tWarranty:20 Years\n" +
                    "NPR 53500\n";

        return description;
    }

    private int getTankProductImage(String item) {
        int image = 0;
        if (item == "NEPATOP 500 Ltr Water Tanks")
            image = R.drawable.nepatop_tank;
        else if (item == "NEPATOP 1000 Ltr Water Tanks")
            image = R.drawable.water_tank;
        else if (item == "NEPATOP 1500 Ltr Water Tanks")
            image = R.drawable.nepatop_tank1000;
        else if (item == "NEPATOP 2000 Ltr Water Tanks")
            image = R.drawable.nepatop_tank;
        else if (item == "NEPATOP 3000 Ltr Water Tanks")
            image = R.drawable.nepatop_tank;
        else if (item == "NEPATOP 5000 Ltr Water Tanks")
            image = R.drawable.nepatop_tank;
        return image;
    }
    private String getIronCoilProductDescription(String item)
    {
        String description= "";
        if(item=="Binding Wire")
            description="Surface Treatment\t:\tGalvanized\n" +
                    "Galvanized Technique\t:\tElectro Galvanized\n" +
                    "Type\t:\tLoop Tie Wire\n" +
                    "Function\t:\tBinding Wire\n" +
                    "Feature\t:\tCorrosion Resistance\n" +
                    "Packing\t:\tPlastic Inside\n" +
                    "Tensile strength\t:\t350-550N/mm2\n";
        else if(item=="Black Wire")
            description="Surface Treatment\t:\tBlack\n" +
                    "Type\t:\tFlat Wire\n" +
                    "Function\t:\tBinding Wire\n" +
                    "Material\t:\tLow Carbon Steel Q195\n" +
                    "Product name\t:\tbinding used black annealed wire from China\n" +
                    "Application\t:\tBuilding\n" +
                    "Surface\t:\tblack\n" +
                    "Color\t:\tblack\n" +
                    "Packing\t:\tCarton\n" +
                    "Tensile strength\t:\t350-550N/mm2\n" +
                    "Coil weight\t:\t5kg --- 500 Kg\n" +
                    "Certification\t:\tISO9001\n" +
                    "Feature\t:\tFlexible\n" +
                    "\n";
        else if (item =="Barbed Wire" )
            description ="Material\t:\tIron Wire\n" +
                    "Surface Treatment\t:\tGalvanized\n" +
                    "Type\t:\tBarbed Wire Coil\n" +
                    "Name\t:\tHeavy Galvanised Barbed Wire\n" +
                    "Wire Gauge\t:\t12.5Gauge\n" +
                    "Barbed length\t:\t1.5-3cm\n" +
                    "Barbed Space\t:\t7.5-15cm\n" +
                    "Weight\t:\t10-50 kg/Coil\n" +
                    "Surface Finished\t:\tGalvanized,PVC Coated..\n" +
                    "Application\t:\tProtection,\n" +
                    "Packing\t:\tWoven bags and pallets\n" +
                    "Certificate\t:\tISO9001\n";

        else if (item=="Concertina Wire")
            description=" Material\t:\tStainless Steel Wire\n" +
                    "Surface Treatment\t:\tGalvanized\n" +
                    "Type\t:\tBarbed Wire Coil\n" +
                    "Razor Type\t:\tCross Razor, Electric galvanized, hot-dipped zinc plating, PVC coated\n" +
                    "Wire diameter\t:\t2.5±0.1mm\n" +
                    "Barb spacing\t:\t34±1mm\n" +
                    "Barb length\t:\t22±1mm\n" +
                    "Barb width\t:\t15±1mm\n" +
                    "Thickness\t:\t0.5mm\n" +
                    "Zinc coating\t:\t40-250g\n" +
                    "Application\t:\tProtecting grass boundary, railway and high ways\n";

        return description;
    }
    private int getIronCoilProductImage(String item) {
        int image = 0;
        if (item == "Binding Wire")
            image = R.drawable.silverbindingwire;
        else if (item == "Black Wire")
            image = R.drawable.bindingwire;
        else if (item == "Barbed Wire")
            image = R.drawable.barbedwire;
        else if (item == "Concertina Wire")
            image = R.drawable.concertinawire;


        return image;
    }

    private String getHardwareProductDescription(String item)
    {
        String description= "";
        if(item=="Shovel")
            description="Material\t:\tCarbon Steel\n" +
                    "Application\t:\tGarden Shovel\n" +
                    "Product\t:\tShovel\n" +
                    "Head\t:\tSteel\n" +
                    "Handle\t:\tHardwood\n" +
                    "Grip\t:\tD type\n" +
                    "Length\t:\tabout 1 meter\n" +
                    "Color\t:\tCustomised\n" +
                    "Appling\t:\tGardening\n";
        else if(item=="Hacksaw Blade")
            description="Material:Steel\n" +
                    "Type\t:\tHacksaw\n" +
                    "Blade Length\t:\t12\",300mm,310mm\n" +
                    "Dimensions\t:\t300x12mm,20mm,24mm,\n" +
                    "Weight\t:\t0.016-0.03kgs\n" +
                    "Product name\t:\tsandflex hss power bimetal hacksaw blade\n" +
                    "Keyword\t:\tFlexible Hand Hacksaw Blades\n" +
                    "Application\t:\tDifferent metal,iron,steel,wood\n" +
                    "Blade material\t:\tCarbon Steel\n" +
                    "Teeth type\t:\tUniversal Cut\n" +
                    "Color\t:\tCustomized Color\n" +
                    "Feature\t:\tFlexible\n" +
                    "Packing\t:\tPlastic Box,Blister Card and other\n";
        else if (item =="Pick Axe" )
            description ="Head Material : Steel\n" +
                    "Handle Material : Wood\n" +
                    "Overall Length : 200 to 600mm\n" +
                    "Market: widely used\n" +
                    "Application : garden pickaxe, digging pickaxe\n" +
                    "Hardness : hardened and tempered\n" +
                    "Length : 200 to 600mm\n" +
                    "Shape : oval eye\n" +
                    "Painted : bright and black painted\n" +
                    "Color : black\n" +
                    "fitting handle : socket\n" +
                    "\n";

        else if (item=="Hand Tools")
            description=" Place of Origin : China\n" +
                    "Package : sliding card, PP card, color box, display box\n" +
                    "quality : tested and guaranteed\n" +
                    "Type : Hand Tools\n";

        return description;
    }
    private int getHardwareProductImage(String item) {
        int image = 0;
        if (item == "Shovel")
            image = R.drawable.shovel;
        else if (item == "Hacksaw Blade")
            image = R.drawable.hacksawblade;
        else if (item == "Pick Axe")
            image = R.drawable.pick_axe;
        else if (item == "Hand Tools")
            image = R.drawable.hand_tools;


        return image;
    }



}
