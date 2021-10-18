package com.example.myevents;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Utils {

    public static final String TRAINING_KEY = "training";
    public static final String PLAN_KEY = "plan";

    // private static ArrayList<Training> trainings;
    // private static ArrayList<Plan> plans;

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getTrainings()) {
            initTrainings();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getPlans()){
            editor.putString(PLAN_KEY, gson.toJson(new ArrayList<Plan>()));
            editor.commit();
        }

    }

    public ArrayList<Training> getTrainings() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Training>>(){}.getType();
        ArrayList<Training> trainings = gson.fromJson(sharedPreferences.getString(TRAINING_KEY,null),type);

        return trainings;

    }

    public boolean addPlan(Plan plan){
        ArrayList<Plan> plans = getPlans();
        if (null != plans){
            if (plans.add(plan)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(PLAN_KEY);
                editor.putString(PLAN_KEY, gson.toJson(plans));
                editor.commit();
                return true;
            }
        }
        return false;

    }

    public ArrayList<Plan> getPlans() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Plan>>(){}.getType();
        ArrayList<Plan> plans = gson.fromJson(sharedPreferences.getString(PLAN_KEY,null),type);

        return plans;

    }


    public boolean removePlan(Plan plan){
        ArrayList<Plan> plans = getPlans();
        if (null != plans){
            for (Plan b: plans){
                if (b.getTraining().getId() == plan.getTraining().getId()){
                    if (plans.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(PLAN_KEY);
                        editor.putString(PLAN_KEY, gson.toJson(plans));
                        editor.commit();
                        return  true;
                    }
                }
            }
        }
        return false;


    }

    public boolean accomplishedPlan(Plan plan){
        ArrayList<Plan> plans = getPlans();
        if (null != plans){
            for (Plan b: plans){
                if (b.getTraining().getId() == plan.getTraining().getId()){
                    b.setAccomplished(true);
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(PLAN_KEY);
                    editor.putString(PLAN_KEY, gson.toJson(plans));
                    editor.commit();
                    return  true;
                }
            }
        }
        return false;


    }



    public void initTrainings(){
        ArrayList<Training> trainings = new ArrayList<>();

        Training manutd = new Training(1,"Fudbal", "Young Boys - Manchester United", "14.09.2021 Arena1 18:45 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Stadion Wankdorf (Bern)\nSudija: F.Letexier (Fra)",
                "https://cdn.insidesport.co/wp-content/uploads/2021/09/13002927/Man-United-v-Young-Boys.png");
        trainings.add(manutd);

        Training barca = new Training(2,"Fudbal", "Barcelona - Bayern Munich", "14.09.2021 Arena1 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Camp Nou (Barcelona)\nSudija: M.Oliver (Eng)",
                "https://sportshub.cbsistatic.com/i/r/2021/09/09/4258fdbc-6821-440c-ad34-8ee65fec43df/thumbnail/1200x675/de859ae537723fc0d03fc4ccd4bd9302/uefa-champions-thumb-barcelona-v-bayern.jpg");
        trainings.add(barca);


        Training srbija = new Training(3,"Odbojka", "Holandija - Srbija", "14.09.2021 Arena2 17:30 CET\nEvropsko Prvenstvo 2021 Cetvrtfinale\nSala: Ergo Arena (Gdansk-Pol)",
                "https://www.points-project.com/wp-content/uploads/2019/10/CEV_IL_Stack_RGB_Black.jpg");
        trainings.add(srbija);

        Training polska = new Training(4,"Odbojka", "Polska - Rusija", "14.09.2021 Arena2 20:30 CET\nEvropsko Prvenstvo 2021 Cetvrtfinale\nSala: Ergo Arena (Gdansk-Pol)",
                "https://ebilet-media.azureedge.net/media/41423/cev_2021_grafika_ogolna_czysta_900x507px-nowa700.jpg");
        trainings.add(polska);


        Training celzi = new Training(5,"Fudbal", "Chelsea - Zenit", "14.09.2021 Arena3 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Stamford Bridge (London)\nSudija: B.Frankowski (Pol)",
                "https://sportshub.cbsistatic.com/i/r/2021/09/09/8f64b733-f4df-4334-8dcc-d7cb56869672/thumbnail/1200x675/f2f1e06af1f8ad59220527947e509245/uefa-champions-thumb-chelsea-v-zenit.jpg");
        trainings.add(celzi);

        Training juve = new Training(6,"Fudbal", "Malmo FF - Juventus", "14.09.2021 Arena4 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Eleda Stadion (Malmo)\nSudija: A.Soares Dias (Por)",
                "https://footballwhispers.com/wp-content/uploads/2021/09/Malmo-vs-Juventus-Prediction.png");
        trainings.add(juve);

        Training villa = new Training(7,"Fudbal", "Villarreal - Atalanta", "14.09.2021 Arena6 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Estadio de la Ceramica (Villareal)\nSudija: C.Turpin (Fra)",
                "https://firstsportz.com/wp-content/uploads/2021/09/UCL-feature1-696x392.jpg");
        trainings.add(villa);

        Training italija = new Training(8,"Odbojka", "Italija - Germanija", "15.09.2021 Arena3 16:00 CET\nEvropsko Prvenstvo 2021 Cetvrtfinale\nSala: Ergo Arena (Gdansk-Pol)",
                "https://www.italy24news.com/temp/resized/medium_2021-09-14-942ed01a3e.jpg");
        trainings.add(italija);

        Training ceska = new Training(9,"Odbojka", "Ceska - Slovenija", "15.09.2021 19:00 CET\nEvropsko Prvenstvo 2021 Cetvrtfinale\nSala: Ergo Arena (Gdansk-Pol)",
                "https://i.ytimg.com/vi/AWJ46FsQNxU/maxresdefault.jpg");
        trainings.add(ceska);

        Training dortmund = new Training(10,"Fudbal", "Besiktas - Borusia Dortmund", "15.09.2021 Arena1 18:45 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Vodafone Park (Istanbul)\nSudija: A.Lahoz (Spa)",
                "https://www.apuestas-deportivas.com/wp-content/uploads/2021/09/Besiktas-vs-Borussia-Dortmund-700x0-c-default.jpg");
        trainings.add(dortmund);

        Training real = new Training(11,"Fudbal", "Inter - Real Madrid", "15.09.2021 Arena1 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: San Siro (Milano)\nSudija: D.Siebert (Ger)",
                "https://www.eurotips.co.uk/wp-content/uploads/2020/11/inter-madrid-eurotips-new.jpg");
        trainings.add(real);

        Training milan = new Training(12,"Fudbal", "Liverpool - Milan", "15.09.2021 Arena2 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Anfield (Liverpool)\nSudija: S.Marciniak (Pol)",
                "https://footballwhispers.com/wp-content/uploads/2021/09/liverpool-vs-ac-milan-prediction.png");
        trainings.add(milan);

        Training psg = new Training(13,"Fudbal", "Club Brugge - PSG", "15.09.2021 Arena3 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Jan Breydel Stadion (Brugge)\nSudija: S.Scharer (Sui)",
                "https://mrfixitstips.co.uk/wp-content/uploads/2021/09/MRF2021_ClubBruggevPSG-1038x519.jpg");
        trainings.add(psg);

        Training altetiko = new Training(14,"Fudbal", "Atletico Mardid - Porto", "15.09.2021 Arena4 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Wanda Metropolitano (Madrid)\nSudija: O.Hategan (Rou)",
                "https://news.22bet.com/wp-content/uploads/2021/09/Atletico-Madrid-vs-FC-Porto.jpg");
        trainings.add(altetiko);

        Training ajax = new Training(15,"Fudbal", "Sporting - Ajax", "15.09.2021 Arena5 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Estadio Jose Alvalade (Lisbon)\nSudija: J.M.Sanchez (Spa)",
                "https://i.ytimg.com/vi/mZ75mvCsqLg/maxresdefault.jpg");
        trainings.add(ajax);

        Training city = new Training(16,"Fudbal", "Manchester City - Leipzig", "15.09.2021 Arena6 21:00 CET\nLiga na Sampioni 2021/2022 kolo:1\nStadion: Etihad Stadion (Manchester)\nSudija: S.Gozubuyuk (Ned)",
                "https://i.ytimg.com/vi/S4y9eGVuobc/maxresdefault.jpg");
        trainings.add(city);

        Training spartak = new Training(17,"Fudbal", "Spartak Moscow - Legia", "15.09.2021 Arena2 16:30 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Otkrytiye Arena (Moscow)\nSudija: N.Dabanovic (Mne)",
                "https://i.ytimg.com/vi/G0gfC8fiojY/maxresdefault.jpg");
        trainings.add(spartak);

        Training zvezda = new Training(18,"Fudbal", "Crvena Zvezda - Braga", "16.09.2021 Arena1 18:45 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Stadion Rajko Mitic (Beograd)\nSudija: J.Beaton (Sco)",
                "https://www.vsstats.com/sportscache/soccer/preview_img_cache/2021/09/16/crvena-zvezda-vs-sporting-braga.png");
        trainings.add(zvezda);

        Training lazio = new Training(19,"Fudbal", "Galatasaray - Lazio", "16.09.2021 Arena2 18:45 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Turk Telekom Stadion (Istanbul)\nSudija: M.Jug (Slo)",
                "https://www.sofascore.com/images/share/16x9/galatasaray-lazio-9758015.png");
        trainings.add(lazio);

        Training dinamo = new Training(20,"Fudbal", "Dinamo Zagreb - West Ham United", "16.09.2021 Arena3 18:45 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Stadion Maksimir (Zagreb)\nSudija: R.Buquet (Fra)",
                "https://www.sofascore.com/images/share/4x3/west-ham-united-gnk-dinamo-zagreb-9758048.png");
        trainings.add(dinamo);

        Training napoli = new Training(21,"Fudbal", "Leicester - Napoli", "16.09.2021 Arena2 21:00 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: King Power Stadion (Leicester)\nSudija: T.Martins (Por)",
                "https://news.22bet.com/wp-content/uploads/2021/09/Leicester-City-v-Napoli.jpg");
        trainings.add(napoli);

        Training lion = new Training(22,"Fudbal", "Rangers - Lyon", "16.09.2021 Arena3 21:00 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Ibrox Stadion (Glasgow)\nSudija: A.Ekberg (Swe)",
                "https://keodanchoi.com/wp-content/uploads/2021/09/Soi-keo-cup-c2-Rangers-vs-Lyon.jpg");
        trainings.add(lion);

        Training fener = new Training(23,"Fudbal", "Eintracht Frankfurt - Fenerbahce", "16.09.2021 Arena4 21:00 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Deutsche Bank Park (Frankfurt)\nSudija: M.Mariani (Ita)",
                "https://www.liveonscore.com/wp-content/uploads/2021/09/Eintracht-Frankfurt-vs-Fenerbahce-Preview-and-Prediction-Live-stream-UEFA-Europa-League-20212022.jpg");
        trainings.add(fener);

        Training psv = new Training(24,"Fudbal", "PSV - Real Sociedad", "16.09.2021 Arena6 21:00 CET\nLiga Evropa 2021/2022 kolo:1\nStadion: Philips Stadion (Eindhoven)\nSudija: W.Collum (Sco)",
                "https://tribuna.com/imaginarium/images/tribuna-match/en/championsleague/match/102192422.jpeg");
        trainings.add(psv);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(TRAINING_KEY,gson.toJson(trainings));
        editor.commit();


    }

    public static Utils getInstance(Context context) {
        if (null != instance){
            return instance;
        }
        else {
            instance = new Utils(context);
            return instance;
        }

    }
}

