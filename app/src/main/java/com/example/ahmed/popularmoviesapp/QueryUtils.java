package com.example.ahmed.popularmoviesapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class QueryUtils {
    /** Sample JSON response for a USGS query */
    public static final String SAMPLE_JSON_RESPONSE="{\"page\":1,\"results\":[{\"poster_path\":\"\\/5N20rQURev5CNDcMjHVUZhpoCNC.jpg\",\"adult\":false,\"overview\":\"Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.\",\"release_date\":\"2016-04-27\",\"genre_ids\":[28,53,878],\"id\":271110,\"original_title\":\"Captain America: Civil War\",\"original_language\":\"en\",\"title\":\"Captain America: Civil War\",\"backdrop_path\":\"\\/rqAHkvXldb9tHlnbQDwOzRi0yVD.jpg\",\"popularity\":47.898276,\"vote_count\":2642,\"video\":false,\"vote_average\":6.94},{\"poster_path\":\"\\/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg\",\"adult\":false,\"overview\":\"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\"release_date\":\"2016-08-03\",\"genre_ids\":[14,28,80],\"id\":297761,\"original_title\":\"Suicide Squad\",\"original_language\":\"en\",\"title\":\"Suicide Squad\",\"backdrop_path\":\"\\/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg\",\"popularity\":42.295711,\"vote_count\":1611,\"video\":false,\"vote_average\":5.91},{\"poster_path\":\"\\/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg\",\"adult\":false,\"overview\":\"The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about his past.\",\"release_date\":\"2016-07-27\",\"genre_ids\":[28,53],\"id\":324668,\"original_title\":\"Jason Bourne\",\"original_language\":\"en\",\"title\":\"Jason Bourne\",\"backdrop_path\":\"\\/AoT2YrJUJlg5vKE3iMOLvHlTd3m.jpg\",\"popularity\":30.784501,\"vote_count\":710,\"video\":false,\"vote_average\":5.28},{\"poster_path\":\"\\/h28t2JNNGrZx0fIuAw8aHQFhIxR.jpg\",\"adult\":false,\"overview\":\"A recently cheated on married woman falls for a younger man who has moved in next door, but their torrid affair soon takes a dangerous turn.\",\"release_date\":\"2015-01-23\",\"genre_ids\":[53],\"id\":241251,\"original_title\":\"The Boy Next Door\",\"original_language\":\"en\",\"title\":\"The Boy Next Door\",\"backdrop_path\":\"\\/vj4IhmH4HCMZYYjTMiYBybTWR5o.jpg\",\"popularity\":21.533723,\"vote_count\":654,\"video\":false,\"vote_average\":4.11},{\"poster_path\":\"\\/tgfRDJs5PFW20Aoh1orEzuxW8cN.jpg\",\"adult\":false,\"overview\":\"Arthur Bishop thought he had put his murderous past behind him when his most formidable foe kidnaps the love of his life. Now he is forced to travel the globe to complete three impossible assassinations, and do what he does best, make them look like accidents.\",\"release_date\":\"2016-08-25\",\"genre_ids\":[80,28,53],\"id\":278924,\"original_title\":\"Mechanic: Resurrection\",\"original_language\":\"en\",\"title\":\"Mechanic: Resurrection\",\"backdrop_path\":\"\\/3oRHlbxMLBXHfMqUsx1emwqiuQ3.jpg\",\"popularity\":20.558035,\"vote_count\":169,\"video\":false,\"vote_average\":4.61},{\"poster_path\":\"\\/vOipe2myi26UDwP978hsYOrnUWC.jpg\",\"adult\":false,\"overview\":\"After a threat from the tiger Shere Khan forces him to flee the jungle, a man-cub named Mowgli embarks on a journey of self discovery with the help of panther, Bagheera, and free spirited bear, Baloo.\",\"release_date\":\"2016-04-07\",\"genre_ids\":[12,18,14],\"id\":278927,\"original_title\":\"The Jungle Book\",\"original_language\":\"en\",\"title\":\"The Jungle Book\",\"backdrop_path\":\"\\/eIOTsGg9FCVrBc4r2nXaV61JF4F.jpg\",\"popularity\":19.476022,\"vote_count\":1131,\"video\":false,\"vote_average\":6.39},{\"poster_path\":\"\\/hU0E130tsGdsYa4K9lc3Xrn5Wyt.jpg\",\"adult\":false,\"overview\":\"One year after outwitting the FBI and winning the public’s adulation with their mind-bending spectacles, the Four Horsemen resurface only to find themselves face to face with a new enemy who enlists them to pull off their most dangerous heist yet.\",\"release_date\":\"2016-06-02\",\"genre_ids\":[28,12,35,80,9648,53],\"id\":291805,\"original_title\":\"Now You See Me 2\",\"original_language\":\"en\",\"title\":\"Now You See Me 2\",\"backdrop_path\":\"\\/zrAO2OOa6s6dQMQ7zsUbDyIBrAP.jpg\",\"popularity\":17.189648,\"vote_count\":735,\"video\":false,\"vote_average\":6.67},{\"poster_path\":\"\\/kqjL17yufvn9OVLyXYpvtyrFfak.jpg\",\"adult\":false,\"overview\":\"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\"release_date\":\"2015-05-13\",\"genre_ids\":[28,12,878,53],\"id\":76341,\"original_title\":\"Mad Max: Fury Road\",\"original_language\":\"en\",\"title\":\"Mad Max: Fury Road\",\"backdrop_path\":\"\\/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg\",\"popularity\":15.923122,\"vote_count\":5262,\"video\":false,\"vote_average\":7.26},{\"poster_path\":\"\\/e3lBJCedHnZPfNfmBArKHZXXNC0.jpg\",\"adult\":false,\"overview\":\"Lorraine and Ed Warren travel to north London to help a single mother raising four children alone in a house plagued by malicious spirits.\",\"release_date\":\"2016-06-08\",\"genre_ids\":[27],\"id\":259693,\"original_title\":\"The Conjuring 2\",\"original_language\":\"en\",\"title\":\"The Conjuring 2\",\"backdrop_path\":\"\\/w9ENTWwpVJYoUky0gg8szmiJuyx.jpg\",\"popularity\":15.565862,\"vote_count\":603,\"video\":false,\"vote_average\":6.45},{\"poster_path\":\"\\/cGOPbv9wA5gEejkUN892JrveARt.jpg\",\"adult\":false,\"overview\":\"Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.\",\"release_date\":\"2016-03-23\",\"genre_ids\":[28,12,14],\"id\":209112,\"original_title\":\"Batman v Superman: Dawn of Justice\",\"original_language\":\"en\",\"title\":\"Batman v Superman: Dawn of Justice\",\"backdrop_path\":\"\\/vsjBeMPZtyB7yNsYY56XYxifaQZ.jpg\",\"popularity\":14.55941,\"vote_count\":3529,\"video\":false,\"vote_average\":5.52},{\"poster_path\":\"\\/gj282Pniaa78ZJfbaixyLXnXEDI.jpg\",\"adult\":false,\"overview\":\"Katniss Everdeen reluctantly becomes the symbol of a mass rebellion against the autocratic Capitol.\",\"release_date\":\"2014-11-18\",\"genre_ids\":[878,12,53],\"id\":131631,\"original_title\":\"The Hunger Games: Mockingjay - Part 1\",\"original_language\":\"en\",\"title\":\"The Hunger Games: Mockingjay - Part 1\",\"backdrop_path\":\"\\/83nHcz2KcnEpPXY50Ky2VldewJJ.jpg\",\"popularity\":13.479147,\"vote_count\":3194,\"video\":false,\"vote_average\":6.69},{\"poster_path\":\"\\/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg\",\"adult\":false,\"overview\":\"Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.\",\"release_date\":\"2015-06-09\",\"genre_ids\":[28,12,878,53],\"id\":135397,\"original_title\":\"Jurassic World\",\"original_language\":\"en\",\"title\":\"Jurassic World\",\"backdrop_path\":\"\\/dkMD5qlogeRMiEixC4YNPUvax2T.jpg\",\"popularity\":13.288188,\"vote_count\":4952,\"video\":false,\"vote_average\":6.59},{\"poster_path\":\"\\/oDL2ryJ0sV2bmjgshVgJb3qzvwp.jpg\",\"adult\":false,\"overview\":\"The city needs heroes. Darkness has settled over New York City as Shredder and his evil Foot Clan have an iron grip on everything from the police to the politicians. The future is grim until four unlikely outcast brothers rise from the sewers and discover their destiny as Teenage Mutant Ninja Turtles. The Turtles must work with fearless reporter April and her wise-cracking cameraman Vern Fenwick to save the city and unravel Shredder's diabolical plan.\",\"release_date\":\"2014-08-07\",\"genre_ids\":[878,28,12,14,35],\"id\":98566,\"original_title\":\"Teenage Mutant Ninja Turtles\",\"original_language\":\"en\",\"title\":\"Teenage Mutant Ninja Turtles\",\"backdrop_path\":\"\\/OqCXGt5nl1cHPeotxCDvXLLe6p.jpg\",\"popularity\":12.26433,\"vote_count\":1647,\"video\":false,\"vote_average\":5.9},{\"poster_path\":\"\\/inVq3FRqcYIRl2la8iZikYYxFNR.jpg\",\"adult\":false,\"overview\":\"Based upon Marvel Comics’ most unconventional anti-hero, DEADPOOL tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.\",\"release_date\":\"2016-02-09\",\"genre_ids\":[28,12,35,10749],\"id\":293660,\"original_title\":\"Deadpool\",\"original_language\":\"en\",\"title\":\"Deadpool\",\"backdrop_path\":\"\\/nbIrDhOtUpdD9HKDBRy02a8VhpV.jpg\",\"popularity\":11.615764,\"vote_count\":4876,\"video\":false,\"vote_average\":7.16},{\"poster_path\":\"\\/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\"adult\":false,\"overview\":\"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.\",\"release_date\":\"2014-11-05\",\"genre_ids\":[12,18,878],\"id\":157336,\"original_title\":\"Interstellar\",\"original_language\":\"en\",\"title\":\"Interstellar\",\"backdrop_path\":\"\\/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\"popularity\":11.561073,\"vote_count\":5621,\"video\":false,\"vote_average\":8.11},{\"poster_path\":\"\\/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg\",\"adult\":false,\"overview\":\"Deckard Shaw seeks revenge against Dominic Toretto and his family for his comatose brother.\",\"release_date\":\"2015-04-01\",\"genre_ids\":[28,80,53],\"id\":168259,\"original_title\":\"Furious 7\",\"original_language\":\"en\",\"title\":\"Furious 7\",\"backdrop_path\":\"\\/ypyeMfKydpyuuTMdp36rMlkGDUL.jpg\",\"popularity\":11.491091,\"vote_count\":2725,\"video\":false,\"vote_average\":7.39},{\"poster_path\":\"\\/vNCeqxbKyDHL9LUza03V2Im16wB.jpg\",\"adult\":false,\"overview\":\"A private eye investigates the apparent suicide of a fading porn star in 1970s Los Angeles and uncovers a conspiracy.\",\"release_date\":\"2016-05-15\",\"genre_ids\":[28,35,80,9648,53],\"id\":290250,\"original_title\":\"The Nice Guys\",\"original_language\":\"en\",\"title\":\"The Nice Guys\",\"backdrop_path\":\"\\/8GwMVfq8Hsq1EFbw2MYJgSCAckb.jpg\",\"popularity\":11.162286,\"vote_count\":565,\"video\":false,\"vote_average\":6.8},{\"poster_path\":\"\\/il9XWx5CbNd2KdDUwrcClEZiLkv.jpg\",\"adult\":false,\"overview\":\"Last months of World War II in April 1945. As the Allies make their final push in the European Theater, a battle-hardened U.S. Army sergeant in the 2nd Armored Division named Wardaddy commands a Sherman tank called \\\"Fury\\\" and its five-man crew on a deadly mission behind enemy lines. Outnumbered and outgunned, Wardaddy and his men face overwhelming odds in their heroic attempts to strike at the heart of Nazi Germany.\",\"release_date\":\"2014-10-15\",\"genre_ids\":[10752,18,28],\"id\":228150,\"original_title\":\"Fury\",\"original_language\":\"en\",\"title\":\"Fury\",\"backdrop_path\":\"\\/pKawqrtCBMmxarft7o1LbEynys7.jpg\",\"popularity\":10.630648,\"vote_count\":2342,\"video\":false,\"vote_average\":7.43},{\"poster_path\":\"\\/9qv3D1danfYQLZj21EgWvjMHbz6.jpg\",\"adult\":false,\"overview\":\"The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.\",\"release_date\":\"2016-06-24\",\"genre_ids\":[12,16,35,10751],\"id\":328111,\"original_title\":\"The Secret Life of Pets\",\"original_language\":\"en\",\"title\":\"The Secret Life of Pets\",\"backdrop_path\":\"\\/3DrUqTAPjriEasoGrz5G8sPJtDU.jpg\",\"popularity\":10.185841,\"vote_count\":620,\"video\":false,\"vote_average\":5.66},{\"poster_path\":\"\\/kJ6eMKlY1I8vVUosWtfP7qbCugL.jpg\",\"adult\":false,\"overview\":\"A small town girl is caught between dead-end jobs. A high-profile, successful man becomes wheelchair bound following an accident. The man decides his life is not worth living until the girl is hired for six months to be his new caretaker. Worlds apart and trapped together by circumstance, the two get off to a rocky start. But the girl becomes determined to prove to the man that life is worth living and as they embark on a series of adventures together, each finds their world changing in ways neither of them could begin to imagine.\",\"release_date\":\"2016-03-03\",\"genre_ids\":[18,10749],\"id\":296096,\"original_title\":\"Me Before You\",\"original_language\":\"en\",\"title\":\"Me Before You\",\"backdrop_path\":\"\\/o4lxNwKJz8oq3R0kLOIsDlHbDhZ.jpg\",\"popularity\":10.087631,\"vote_count\":534,\"video\":false,\"vote_average\":7.42}],\"total_results\":19633,\"total_pages\":982}";

    private QueryUtils(){
    }
    public static ArrayList<Movie> extractMovies(){
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Movie>movies=new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try{
            JSONObject baseJsonResponse =new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray moviesJsonArray =(JSONArray) baseJsonResponse.get("results");
            for(int i=0;i<moviesJsonArray.length();i++){
                JSONObject currentMovie =moviesJsonArray.getJSONObject(i);
                String path=currentMovie.getString("poster_path");
                String overview=currentMovie.getString("overview");;
                String relase_date=currentMovie.getString("release_date");;
                String title=currentMovie.getString("original_title");;
                String rate=currentMovie.getString("vote_average");;
                Movie movie=new Movie(title,overview,path,relase_date,rate);
                movies.add(movie);
            }
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
        }catch (JSONException e){
            Log.e("QueryUtil","Problem Parcing Json",e);
        }
        return movies;
    }
}
